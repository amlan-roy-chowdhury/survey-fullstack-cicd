# Use a lightweight OpenJDK image
FROM openjdk:21-jdk-slim

# Set app directory inside container
WORKDIR /app

# Copy the built jar into the container
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

