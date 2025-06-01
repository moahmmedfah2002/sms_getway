FROM  eclipse-temurin:21-jdk-alpine


WORKDIR /app

COPY target/schedule-0.0.1-SNAPSHOT.jar schedule.jar

EXPOSE 6004

ENTRYPOINT ["java", "-jar", "schedule.jar"]
