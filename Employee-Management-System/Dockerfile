FROM openjdk:17-jdk-slim

ENV APP_NAME=Employee-Management-System

WORKDIR /app

COPY target/employee-management-system-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
