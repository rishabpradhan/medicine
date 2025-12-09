#
## Build stage
#FROM eclipse-temurin:17-jdk-alpine AS build
#WORKDIR /app
#
## Copy Maven wrapper and pom.xml
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#
## Download dependencies
#RUN ./mvnw dependency:go-offline -B
#
## Copy source code
#COPY src src
#
## Build the application
#RUN mvn clean package -DskipTests
#
## Runtime stage
#FROM eclipse-temurin:17-jre-alpine
#WORKDIR /app
#
## Copy the jar from build stage
#COPY --from=build /app/target/*.jar app.jar
#
## Expose port
#EXPOSE 8080

#Build argument for Java version
#ARG JAVA_VERSION=17

#Stage 1: Build the application
FROM maven:3.9.4 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

#Stage 2: Run the application
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]