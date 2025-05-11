FROM  eclipse-temurin:21-jdk-alpine


WORKDIR /app

COPY target/receiver-0.0.1-SNAPSHOT.jar receiver.jar

EXPOSE 6003

ENTRYPOINT ["java", "-jar", "receiver.jar"]