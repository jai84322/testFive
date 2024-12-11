# Use a base Java image
FROM openjdk:11-jdk-slim

# Install curl
RUN apt-get update && apt-get install -y curl

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY ./target/testFive-1.0-SNAPSHOT.jar /app/testFive.jar

# Copy the dump file into the container for database initialization
COPY ./dump-postgres-202412110612.sql /docker-entrypoint-initdb.d/

# Expose the port your app will run on
EXPOSE 8096

# Command to run your application
CMD ["java", "-jar", "/app/testFive.jar"]
