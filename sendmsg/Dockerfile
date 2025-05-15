FROM  eclipse-temurin:21-jdk-alpine


WORKDIR /app

COPY target/sendmsg-0.0.1-SNAPSHOT.jar sms.jar

EXPOSE 6005

ENTRYPOINT ["java", "-jar", "sms.jar"]