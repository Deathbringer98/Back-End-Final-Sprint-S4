# Use an official OpenJDK runtime, matching the Java version used in the project
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/Sprint1-API-1.0-SNAPSHOT.jar /app/Sprint1-API.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "Sprint1-API.jar"]
