# Usa una imagen base con OpenJDK 17 y Alpine
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo
WORKDIR /app

# Instala Maven
RUN apk add --no-cache maven

# Copia el código fuente del proyecto al contenedor
COPY . /app/

# Ejecuta Maven para construir el JAR
RUN mvn clean package -DskipTests

# Expone el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Ejecuta la aplicación con el JAR generado
CMD ["java", "-jar", "target/back-0.0.1-SNAPSHOT.jar"]
