FROM  eclipse-temurin:21-jdk-alpine


WORKDIR /app

COPY target/gatway-0.0.1-SNAPSHOT.jar gateway.jar

EXPOSE 7001

ENTRYPOINT ["java", "-jar", "gateway.jar"]