FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/group-0.0.1-SNAPSHOT.jar groupMS.jar

EXPOSE 6001

ENTRYPOINT ["java", "-jar", "groupMS.jar"]