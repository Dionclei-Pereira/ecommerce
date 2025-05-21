FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY libs/auth-lib /app/libs/auth-lib
COPY ecommerce-payment-service /app/ecommerce-payment-service

RUN cd libs/auth-lib && mvn install -DskipTests

RUN cd /app/ecommerce-payment-service && mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/ecommerce-payment-service/target/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]
