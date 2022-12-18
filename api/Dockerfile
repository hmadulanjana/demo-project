# FROM openjdk:8-jdk-alpine
# FROM openjdk:20-ea-16-jdk-slim-buster
FROM openjdk:16-jdk-alpine3.13
COPY target/docker-api-0.1.0.jar api-0.1.0.jar
ENTRYPOINT ["java","-jar","/api-0.1.0.jar"]
