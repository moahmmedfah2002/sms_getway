FROM  eclipse-temurin:21-jdk-alpine


WORKDIR /app

COPY target/group-0.0.1-SNAPSHOT.jar group.jar

EXPOSE 6001

ENTRYPOINT ["java", "-jar", "group.jar"]
