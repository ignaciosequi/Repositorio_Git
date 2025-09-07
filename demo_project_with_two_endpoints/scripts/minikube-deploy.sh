#!/usr/bin/env bash
set -euo pipefail

USE_LOCAL_IMAGE=${USE_LOCAL_IMAGE:-0}
NAMESPACE=${NAMESPACE:-demo}
HOST=${HOST:-demo.local}
GHCR_USER=${GHCR_USER:-}
GHCR_PAT=${GHCR_PAT:-}

need() { command -v "$1" >/dev/null 2>&1 || { echo "Falta comando: $1" >&2; exit 1; }; }
need kubectl
need minikube

echo "== Iniciando Minikube (si no está iniciado) =="
if ! minikube status >/dev/null 2>&1; then
  minikube start --cpus=2 --memory=4g
fi

echo "== Habilitando addon ingress =="
minikube addons enable ingress >/dev/null

echo "== Aplicando manifiestos base =="
kubectl apply -f k8s/namespace.yaml >/dev/null
kubectl apply -f k8s/configmap.yaml >/dev/null
kubectl apply -f k8s/service.yaml >/dev/null
kubectl apply -f k8s/deployment.yaml >/dev/null

if [ "$USE_LOCAL_IMAGE" = "0" ]; then
  if [ -n "$GHCR_USER" ] && [ -n "$GHCR_PAT" ]; then
    echo "== Creando imagePullSecret (si no existe) =="
    if ! kubectl get secret ghcr-secret -n "$NAMESPACE" >/dev/null 2>&1; then
      kubectl create secret docker-registry ghcr-secret \
        --docker-server=ghcr.io \
        --docker-username="$GHCR_USER" \
        --docker-password="$GHCR_PAT" \
        -n "$NAMESPACE" >/dev/null
    fi
  else
    echo "WARN: GHCR_USER/GHCR_PAT no definidos; si la imagen es privada crea el secret manualmente" >&2
  fi
else
  echo "== Construyendo imagen local en Minikube =="
  need docker
  minikube image build -t demo-app:local .
  kubectl -n "$NAMESPACE" set image deploy/demo-app demo=demo-app:local
fi

echo "== Desplegando Ingress =="
kubectl apply -f k8s/ingress.yaml >/dev/null

echo "== Esperando rollout del Deployment =="
kubectl -n "$NAMESPACE" rollout status deploy/demo-app

IP=$(minikube ip)
echo
echo "Añade al archivo hosts esta línea (sudo/administrador):"
echo "  $IP $HOST"
echo "Luego abre: http://$HOST/hello"

