# Use an official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

COPY seeder.sql ./seeder.sql

# Copy the built jar from the build stage
COPY --from=build /app/target/eCommerceBackend-0.0.1.jar app.jar

# Expose the port your application runs on
EXPOSE 8087

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
