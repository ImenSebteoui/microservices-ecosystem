# Stage 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR with lightweight Alpine JDK
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# can be overridden in docker-compose
EXPOSE 8080  

ENTRYPOINT ["java", "-jar", "app.jar"]