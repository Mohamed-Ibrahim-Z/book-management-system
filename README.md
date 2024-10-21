# Book Management System

## Overview

This Book Management System is a comprehensive library management platform built using Spring Boot. It allows users to browse, borrow, and return books, as well as manage their library accounts. Administrators can manage the library's inventory, users, and handle overdue fines. The system uses JWT authentication for security, and provides full exception handling to ensure the API handles invalid inputs and errors gracefully.

## Features

- User Management: User registration, login, profile management.
- Book Management: Adding, updating, browsing, and deleting books.
- Borrowing & Returning: Borrow, return, and renew borrowed books.
- Reservation Management: Reserve books and manage reservations.
- Viewing History and Fines: View borrowing history and outstanding fines.
- Administrator Management: Manage users, books, and fines.
- JWT Authentication: Secure login and access with JSON Web Token (JWT).

## Requirements

### Prerequisites

- Java 17 or higher
- Maven for dependency management
- Docker for setting up the MySQL database
- Postman or cURL for testing the API

### Tools Used

- Spring Boot: Backend framework
- Spring Security: For JWT-based authentication
- JPA/Hibernate: ORM for database interactions
- Docker: Containerization of the MySQL database
- Lombok: Reduces boilerplate code
- JWT: For securing API routes

## Installation and Setup

1- Clone the Repository

```bash
git clone https://github.com/Mohamed-Ibrahim-Z/book-management-system.git
```

2- Set Up MySQL Using Docker
To simplify the setup process, the project uses a Docker container for the MySQL database. You can set up MySQL in a Docker container by running the following command:

```bash
docker run --name book_management_db -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=book_management -p 3306:3306 -d
```

This command will:

- Pull the MySQL image (version 8) from Docker Hub.
- Create a MySQL database named book_management.
- Map MySQL's default port 3306 to your local machine.
- Set the root password as `rootpassword`.

3- Configure the Application
Once the MySQL container is running, update the `application.properties `(or `application.yml`) file in the `src/main/resources/` folder with the following configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/book_management
spring.datasource.username=root
spring.datasource.password=rootpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

4- Run the Application

Use Maven to build and run the project:

```bash
mvn spring-boot:run
```

5- Access the Application
API URL: http://localhost:8080

## API Endpoints

### User Management

`POST /signup`: Register a new user.  
`POST /login`: Authenticate and retrieve JWT token.  
`GET /profile`: Get user profile details.  
`PUT /profile`: Update user profile.

### Book Management

`GET /books`: Retrieve all books.  
`GET /books/{id}`: Get a specific book by ID.  
`POST /books`: Add a new book (admin only).  
`PUT /books/{id}`: Update a book by ID (admin only).  
`DELETE /books/{id}`: Delete a book by ID (admin only).

### Borrowing & Returning

`POST /borrow`: Borrow a book.  
`POST /return`: Return a borrowed book.  
`POST /renew`: Renew a borrowed book.

### Reservation Management

`POST /reserve`: Reserve a book.  
`DELETE /reserve`: Cancel a reservation.

### Viewing History and Fines

`GET /history`: Retrieve borrowing history.  
`GET /fines`: Retrieve and pay fines.

### Administrator Management

`GET /users`: Retrieve all users (admin only).  
`PUT /users/{id}`: Update user details (admin only).  
`DELETE /users/{id}`: Delete a user (admin only).
