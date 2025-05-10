FROM ubuntu:latest

RUN  apt-get update
RUN  apt-get -y install default-jre

WORKDIR /var

COPY target/conf-server-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "demo.jar"]