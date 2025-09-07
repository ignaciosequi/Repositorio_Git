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
