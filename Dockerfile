# Use the official OpenJDK 21 image as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build context to the working directory
COPY  build/libs/mysqlrestapi-1.0.0.jar /app/

# Expose port 8080 to the outside world
EXPOSE 8080

# Set the entry point to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/mysqlRestApi-1.0.0.jar"]
