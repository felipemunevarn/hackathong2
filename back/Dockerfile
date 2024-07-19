FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/back-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "back-0.0.1-SNAPSHOT.war"]