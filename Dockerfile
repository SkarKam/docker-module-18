# Build and test
FROM maven:3.9-eclipse-temurin-22 AS build

# Set the working directory
WORKDIR /app

# Copy POM and download dependency
COPY ./pom.xml .
COPY ./.env .
RUN mvn dependency:go-offline

# Copy the rest of source code
COPY src ./src

#Run the main class
CMD ["mvn","clean","test"]