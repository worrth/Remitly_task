FROM eclipse-temurin:23-jdk-alpine AS build

WORKDIR /app

RUN apk update && apk add maven

COPY pom.xml .
COPY src ./src

RUN mvn clean test package

FROM openjdk:23

WORKDIR /app

COPY --from=build /app/target/remilty-0.0.1-SNAPSHOT.jar /app/remitly.jar

COPY ["Interns_2025_SWIFT_CODES - Sheet1.csv", "/app/Interns_2025_SWIFT_CODES - Sheet1.csv"]

EXPOSE 8080

CMD ["java", "-jar", "/app/remitly.jar"]