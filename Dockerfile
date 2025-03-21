# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-22 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the JAR file
RUN mvn clean package -DskipTests

# List contents of the /app/target to check the JAR existence
RUN ls -l /app/target/

# Stage 2: Run the Application
FROM openjdk:22-slim

# Label metadata
LABEL maintainer="Krittamet Tanboontor <your-email@example.com>"

# Set the working directory inside the container
WORKDIR /app

# Copy only the built JAR file from the previous stage
COPY --from=builder /app/target/*.jar book-service.jar

# Expose the application's port (default Spring Boot port)
EXPOSE 8080

# Use environment variables for flexibility
ENV JAVA_OPTS=""

# Command to run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar book-service.jar"]
