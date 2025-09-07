# Proyecto Demo con Spring Boot

Este es un proyecto **mínimo en Java con Spring Boot** para responder a peticiones HTTP.

## 🚀 Cómo ejecutar

1. Instala **Java JDK 17** o superior.
2. Instala **Maven**.
3. Abre esta carpeta en **Visual Studio Code** (con las extensiones de Java y Spring Boot instaladas).
4. En la terminal ejecuta:

```bash
mvn spring-boot:run
```

5. Abre en el navegador:

```
http://localhost:8080/hello
http://localhost:8080/bye
```

Deberías ver:

```
¡Hola desde Spring Boot en Java!
¡Adiós desde Spring Boot en Java!
```

## 📂 Estructura del proyecto

```
demo/
 ├── src/
 │   └── main/
 │       ├── java/
 │       │   └── com/example/demo/
 │       │        ├── DemoApplication.java   # Punto de entrada
 │       │        └── HelloController.java   # Controlador REST con 2 endpoints
 │       └── resources/
 │            └── application.properties     # Configuración
 └── pom.xml                                 # Dependencias y build (Maven)
```

## ⚙️ Personalización

- Cambia los mensajes editando `HelloController.java`.
- Si quieres usar otro puerto, edita `src/main/resources/application.properties`:

```properties
server.port=8081
```

## 📌 Notas

- Este proyecto es ideal como base para **proyectos web más complejos**.
- Spring Boot levanta un servidor embebido, no necesitas Tomcat aparte.

## Cloud-native

- Health: expone `/actuator/health`, `/actuator/health/liveness` y `/actuator/health/readiness` (Actuator).
- Config externa: variable `APP_MESSAGE` usada por el endpoint `GET /saludo?nombre=...`.

### Ejecutar con config por variable

```bash
APP_MESSAGE="Hola desde env" mvn spring-boot:run
# Luego visita: http://localhost:8080/saludo?nombre=Ana
```

### Docker

```bash
# Construir imagen
docker build -t demo-app:local .

# Ejecutar contenedor
docker run --rm -p 8080:8080 -e APP_MESSAGE="Hola desde Docker" demo-app:local
```

### GitHub Actions (CI)

- Workflow en `.github/workflows/ci.yml` compila con Java 21 y construye la imagen.
- Para publicar a GHCR usa la etiqueta `ghcr.io/<owner>/<repo>:latest` (ya configurado) y asegúrate de que el repo tiene `packages: write`.

### Kubernetes (k8s)

Manifiestos en `k8s/`:
- `configmap.yaml` con `APP_MESSAGE`.
- `deployment.yaml` con probes de liveness/readiness.
- `service.yaml` tipo `ClusterIP`.

Despliegue básico (reemplaza la imagen en el deployment):

```bash
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl get pods,svc
```

Acceso dentro del cluster:

```bash
kubectl port-forward svc/demo-app 8080:80
# http://localhost:8080/hello
```

### Acceso a registry privado (GHCR)

Si la imagen en GHCR es privada, crea un `imagePullSecret` y úsalo (ya referenciado como `ghcr-secret` en `k8s/deployment.yaml`):

```bash
# Crea un PAT en GitHub con scope: read:packages
kubectl create secret docker-registry ghcr-secret \
  --docker-server=ghcr.io \
  --docker-username=<tu-usuario-github> \
  --docker-password=<tu-PAT> \
  --namespace default

# Verifica
kubectl get secret ghcr-secret -n default
```

Si usas otro namespace, crea el secret en ese namespace o ajusta el `Deployment`.

### Kubernetes (namespace `demo`)

Para desplegar usando el namespace dedicado `demo` incluido en `k8s/`:

```bash
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl get pods,svc -n demo

# Port-forward para probar
kubectl port-forward -n demo svc/demo-app 8080:80
# http://localhost:8080/hello
```

#### Ingress (exponer por hostname)

Requisitos: tener instalado un Ingress Controller (por ejemplo, NGINX Ingress Controller).

```bash
# Aplica el Ingress (edita el host en k8s/ingress.yaml)
kubectl apply -f k8s/ingress.yaml

# Comprueba
kubectl get ingress -n demo
```

- Si usas Minikube:
  - minikube addons enable ingress
  - Obtén la IP con `minikube ip` y añade en tu `/etc/hosts` (Windows: C:\Windows\System32\drivers\etc\hosts):
    - <IP_MINIKUBE> demo.example.com
  - Abre http://demo.example.com

- Si usas cloud (AKS/EKS/GKE): apunta tu DNS público al LB del controlador Ingress.

Si usas GHCR privado, crea el secreto en el namespace `demo` (ya referenciado en el Deployment como `ghcr-secret`):

```bash
kubectl create secret docker-registry ghcr-secret \
  --docker-server=ghcr.io \
  --docker-username=<tu-usuario-github> \
  --docker-password=<tu-PAT> \
  --namespace demo

kubectl get secret ghcr-secret -n demo
```
