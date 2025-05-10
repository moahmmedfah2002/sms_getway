FROM eclipse-temurin:21-jdk-alpine




WORKDIR /app

COPY target/conf-server-0.0.1-SNAPSHOT.jar configMicroService.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "configMicroService.jar"]