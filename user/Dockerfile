FROM maven:3.8.4-jdk-11-slim AS builder
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn install -DskipTests

FROM openjdk:11-jre-slim-buster
COPY --from=builder /app/target/*.jar /app/my_app.jar
CMD java -jar /app/my_app.jar