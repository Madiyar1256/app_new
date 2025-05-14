FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY ./dc_app_new.jar /app/dc_app_new.jar

# Expose application portdocker-compose up
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "dc_app_new.jar"]
