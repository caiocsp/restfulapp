# Build
FROM maven:3.9.7-eclipse-temurin-21 AS build

COPY src /app/src

COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

# Run
FROM eclipse-temurin:21-jre

COPY --from=build /target/restfulapp.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
