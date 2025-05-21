FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY libs/auth-lib /app/libs/auth-lib
COPY ecommerce-user-service /app/ecommerce-user-service

RUN cd libs/auth-lib && mvn install -DskipTests

RUN cd /app/ecommerce-user-service && mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/ecommerce-user-service/target/*.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "app.jar"]
