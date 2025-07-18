FROM maven:3.9.6-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/demo.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
