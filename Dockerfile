# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV ALLURE_VERSION=2.17.2
ENV GRADLE_USER_HOME /app/.gradle

# Install dependencies
RUN apt-get update && apt-get install -y \
    wget \
    unzip \
    curl \
    nodejs \
    npm \
    && apt-get clean

# Install Allure
RUN wget https://github.com/allure-framework/allure2/releases/download/${ALLURE_VERSION}/allure-${ALLURE_VERSION}.zip \
    && unzip allure-${ALLURE_VERSION}.zip -d /opt/ \
    && rm allure-${ALLURE_VERSION}.zip \
    && ln -s /opt/allure-${ALLURE_VERSION}/bin/allure /usr/bin/allure

# Install http-server globally
RUN npm install -g http-server

# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle file and the gradle wrapper
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Download and install any dependencies
RUN ./gradlew build --no-daemon || return 0

# Copy the rest of the application code
COPY src /app/src

# Build the application and run tests
RUN ./gradlew test --no-daemon

# Generate Allure report
RUN allure generate /app/build/allure-results -o /app/build/allure-report

# Expose the port to access the Allure report
EXPOSE 8083

# Serve the Allure report
CMD ["sh", "-c", "./gradlew test --no-daemon && allure generate /app/build/allure-results -o /app/build/allure-report && http-server /app/build/allure-report -p 8083"]
