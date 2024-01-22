FROM maven:3.8.5-openjdk-17-slim as build
COPY . .
RUN mvn clean package

FROM openjdk:17-alpine

COPY --from=build rest/target/*.jar app.jar
COPY --from=build db/init-data.sql init-data.sql

EXPOSE 8081

ENV MYSQL_ROOT_PASSWORD=root

ENTRYPOINT ["java","-jar","/app.jar"]
