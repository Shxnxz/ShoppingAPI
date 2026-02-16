# ShopClone API

A robust RESTful API simulating a convenience store backend. Built with **Spring Boot** and **PostgreSQL**, this system manages product inventory and handles transactional purchase logic with stock validation.

## Features
- **Product Management:** Full CRUD (Create, Read, Update, Delete) for inventory items.
- **Categorization:** Organize products by category (e.g., Electronics, Snacks).
- **Transactional "Buy" System:** Dedicated endpoint to purchase items that automatically decrements stock and prevents overselling.
- **Error Handling:** Returns clear HTTP 400/404 status codes for invalid operations.

## Tech Stack
- **Language:** Java 17
- **Framework:** Spring Boot 3.x
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Containerization:** Docker & Docker Compose

## API
1. Get All Products
- GET http://localhost:8080/api/products
2. Get Single Product
- GET http://localhost:8080/api/products/{id}
3. Create New Product
- POST http://localhost:8080/api/products
```
{
  "name": "Gaming Mouse",
  "description": "High precision wireless mouse",
  "price": 49.99,
  "stockQuantity": 15,
  "category": "Electronics"
}
```
4. Update Product
- PUT http://localhost:8080/api/products/{id}
```
{
  "name": "HB Pencil (Bulk Pack)",
  "description": "Box of 50 wooden pencils",
  "price": 12.50,
  "stockQuantity": 200,
  "category": "Stationery"
}
```
5. Delete Product
- DELETE http://localhost:8080/api/products/{id}
6. Buy Product
- POST http://localhost:8080/api/products/{id}/buy?amount=5


##  How to Run


1. Clone the repository
2. Create Database called "shop" SQL Command is provided in setup.sql
3. Run Database
4. bash docker compose up --build or bash ./mvnw spring-boot:run
5. test API in Postman

** Please noticed that my DB username is "postgres" and password is "password". 
Feel free to adjust it if you are using different setup in compose.yaml and application.properties **