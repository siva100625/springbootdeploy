FROM maven:3.9.9-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk
COPY --from=build /target/demo.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
