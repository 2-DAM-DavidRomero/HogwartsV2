# Usamos una imagen base ligera con Java 25
FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

# Opción más compatible: Copia el JAR que NO es el 'plain'
# El asterisco ayuda si el nombre tiene versiones (0.0.1-SNAPSHOT)
COPY build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]