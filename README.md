![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-green)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.4.5-green)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.4.1-blue)
![JWT](https://img.shields.io/badge/JWT-RSA-blue)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-orange)


# Ecommerce - Microservices

## 📖 Description

This project implements a microservice structure. The main objective is to create an API that manages an ecommerce.

## 🚀 **Technologies**

The main technologies used in this project are:

- ⚙️ Java 21  
- 🌱 Spring Boot  
- 🛠️ Hibernate  
- 🔁 WebFlux  
- 📬 RabbitMQ  
- 🗄️ H2 DataBase  
- 🔐 Spring Security  
- 🔑 JWT - RSA  
- 🐳 Docker & Docker Compose

## 🎯 **Features**

- 👤 User Registration and Login
- 🔐 Authentication and Authorization (JWT)
- 📦 Product Management
- 🧮 Inventory System
- 💳 Order and Payment Processing
- 📡 Service Discovery (Eureka)
- ⚙ Config Server (Centralized Configuration)
- 📫 Async messaging with RabbitMQ

## ⚙ Prerequisites

Install these programs:

- 🐳 Docker & Docker Compose
- 📫 **Postman** (or similar.)

## ⚡ Steps to Run the Project

### 1. Clone the repository

Clone the project to your local environment:

```bash
git clone https://github.com/Dionclei-Pereira/ecommerce.git
```

### 2. Build the application

```bash
docker-compose build
```


### 3. Run the Project

To run the project, you must start first the config-server then discovery server and finaly the orders services.

```bash
docker-compose up -d ecommerce-config-server

docker-compose up -d ecommerce-discovery-server

docker-compose up -d
```

### 4. Testing the API

Some services are configured to allow login. You can use **Postman** to test the routes and to generate a JWT token you must run user-service.

- **POST** `/api/auth/login`: Send an email password to receive a JWT token.
- **GET** `/api/orders`: This route is protected and requires a valid JWT token in the Authorization header.

Example request for login:

POST /auth/login
```json
{
  "email": "email@gmail.com",
  "password": "password"
}
```

If the login is successful, a JWT token will be returned.

Example request to access a protected route:

- **GET** `/api/orders` <br>
Authorization: _your-jwt-token_

## 📑 API Endpoints

| Method | Endpoint | Description | Request Body | Service |
|--------|----------|-------------|------------|----------|
| `POST` | `/api/auth/login` | Authenticates a user and returns a JWT token | `{ "email": "email@gmail", "password": "password" }` | `User` |
| `POST` | `/api/auth/register` | Authenticates a new user | `{"email": "email@gmail.com", "name": "username", "password": "password"}` | `User` |
| `GET` | `/api/users` | Retrieves all users | - | `User` |
| `GET` | `/api/users/{id}` | Retrieves a specific user by ID |  -| `User` |
| `GET`  | `/api/products` | Retrieves all products | - | `Product` |
| `GET`  | `/api/products/{id}` | Retrieves a specific product by ID | - | `Product`|
| `POST (ADMIN)`  | `/api/products` | Creates a new product | `{"name": "Smartphone X", "description": "Latest generation.", "price": 899.99}` | `Product` |
| `GET`  | `/api/inventory?productCode=1&quantity=1` | Retrieves if the product is in stock | - | `Inventory` |
| `GET`  | `/api/inventory/{id}` | Retrieves a specific inventory by ID | - | `Inventory`|
| `POST (ADMIN)`  | `/api/inventory` | Creates a new inventory | `{ "productCode": "4", "quantity": 10 }` | `Inventory` |
| `PATCH (ADMIN)`  | `/api/inventory/{id}` | Updates a new inventory | `{ "quantity": -10 }` | `Inventory` |
| `GET`  | `/api/orders` | Retrieves all orders of the connected user | - | `Order` |
| `POST`  | `/api/orders` | Creates a new order | `{"items": [{"productId": 1, "quantity": 1}]}` | `Order` |
| `GET`  | `/api/payment/orders/{id}` | Retrieves a specific order payment by ID | - | `Payment` |
| `POST`  | `/api/payment/orders/{id}` | Pays a order | - | `Payment` |


## 📜Author

**Dionclei de Souza Pereira**

[Linkedin](https://www.linkedin.com/in/dionclei-de-souza-pereira-07287726b/)

⭐️ If you like this project, give it a star!  
