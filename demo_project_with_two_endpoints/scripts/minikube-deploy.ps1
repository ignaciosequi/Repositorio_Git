Param(
  [switch]$UseLocalImage,
  [string]$Namespace = "demo",
  [string]$IngressHost = "demo.local",
  [string]$GhcrUser = $env:GHCR_USER,
  [string]$GhcrPat = $env:GHCR_PAT
)

$ErrorActionPreference = 'Stop'

function Ensure-Command($name) {
  if (-not (Get-Command $name -ErrorAction SilentlyContinue)) {
    throw "No se encontró el comando: $name"
  }
}

Ensure-Command kubectl
Ensure-Command minikube

Write-Host "== Iniciando Minikube (si no está iniciado) =="
try { minikube status | Out-Null } catch { }
if ($LASTEXITCODE -ne 0) { minikube start --cpus=2 --memory=4g }

Write-Host "== Habilitando addon ingress =="
minikube addons enable ingress | Out-Null

Write-Host "== Aplicando manifiestos base =="
kubectl apply -f k8s/namespace.yaml | Out-Null
kubectl apply -f k8s/configmap.yaml | Out-Null
kubectl apply -f k8s/service.yaml | Out-Null
kubectl apply -f k8s/deployment.yaml | Out-Null

if (-not $UseLocalImage) {
  if ($GhcrUser -and $GhcrPat) {
    Write-Host "== Creando imagePullSecret (si no existe) =="
    $exists = kubectl get secret ghcr-secret -n $Namespace --ignore-not-found
    if (-not $exists) {
      kubectl create secret docker-registry ghcr-secret `
        --docker-server=ghcr.io `
        --docker-username=$GhcrUser `
        --docker-password=$GhcrPat `
        -n $Namespace | Out-Null
    }
  } else {
    Write-Warning "GHCR_USER/GHCR_PAT no definidos. Si la imagen es privada, crea el secret manualmente."
  }
} else {
  Write-Host "== Construyendo imagen local en Minikube =="
  Ensure-Command docker
  minikube image build -t demo-app:local .
  kubectl -n $Namespace set image deploy/demo-app demo=demo-app:local
}

Write-Host "== Desplegando Ingress =="
kubectl apply -f k8s/ingress.yaml | Out-Null

Write-Host "== Esperando rollout del Deployment =="
kubectl -n $Namespace rollout status deploy/demo-app

$ip = (minikube ip)
Write-Host ""
Write-Host "Añade al archivo hosts la siguiente línea (como administrador):"
Write-Host ("  {0} {1}" -f $ip, $IngressHost)
Write-Host "Luego abre: http://$IngressHost/hello"
