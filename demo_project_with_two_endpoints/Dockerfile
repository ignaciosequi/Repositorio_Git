# Multi-stage build for Spring Boot (Java 21)
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom and resolve deps first (better layer cache)
COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

# Copy sources and build
COPY src ./src
RUN mvn -B -DskipTests package

# Runtime image
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy fat jar from builder
COPY --from=build /app/target/*.jar app.jar

# Environment-driven config (12-factor)
ENV APP_MESSAGE="Hola desde variable de entorno"

EXPOSE 8080

# Healthcheck using actuator (optional; Kubernetes uses its own probes)
# HEALTHCHECK --interval=30s --timeout=3s --retries=3 CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java","-jar","/app/app.jar"]

