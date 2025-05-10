FROM eclipse-temurin:21-jdk-alpine



WORKDIR /app

COPY target/discovery-0.0.1-SNAPSHOT.jar discovery.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "discovery.jar"]