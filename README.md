
# Library Management System - Spring Boot REST API

A simple yet powerful **Spring Boot-based RESTful API** that manages library resources including books and borrowers. Designed with clean architecture, REST standards, and test coverage in mind, this project serves as a solid foundation for real-world Java backend systems.

---

## Features

- RESTful API following standard HTTP methods and status codes
- Register, retrieve, and manage books and borrowers
- Borrow and return books with validation and business rules
- Custom exception handling with meaningful HTTP responses
- Repository pattern using Spring Data JPA
- Unit testing with JUnit 5 and Mockito
- Code coverage analysis using **JaCoCo**

---

## Architecture

- **Controller Layer** – Handles HTTP requests and maps them to service logic.
- **Service Layer** – Contains business logic and handles validations.
- **Repository Layer** – Abstracts database operations using Spring Data JPA.
- **Model Layer** – Contains the core domain entities (Book, Borrower, BorrowingRecord).
- **Exception Layer** – Provides centralized error handling using `@RestControllerAdvice`.

---

## Tech Stack

| Layer        | Technology                  |
|--------------|-----------------------------|
| Language     | Java 17                     |
| Framework    | Spring Boot                 |
| Database     | H2 (in-memory)              |
| Persistence  | Spring Data JPA             |
| Testing      | JUnit 5, Mockito            |
| Build Tool   | Maven                       |
| Coverage     | JaCoCo                      |

---

## Project Structure

\`\`\`
src
 ├── main
 │   ├── java/com/example/library
 │   │   ├── controller         # REST controllers
 │   │   ├── service            # Business logic
 │   │   ├── repository         # Data access layer
 │   │   ├── model              # Entity classes
 │   │   └── exception          # Custom exceptions & handler
 │   └── resources
 │       └── application.properties
 └── test
     └── java/com/example/library
         ├── controller         # Controller layer tests
         ├── service            # Service layer tests
         └── repository         # Repository tests (if any)
\`\`\`

---

## API Endpoints

| Method | Endpoint             | Description                    |
|--------|----------------------|--------------------------------|
| GET    | `/books`             | Get all books                  |
| POST   | `/books`             | Register a new book            |
| GET    | `/borrowers`         | Get all borrowers              |
| POST   | `/borrowers`         | Register a new borrower        |
| POST   | `/borrow`            | Borrow a book                  |
| POST   | `/return`            | Return a book                  |

---

## Running Tests

\`\`\`bash
# Clean build and run all tests with coverage
mvn clean verify
\`\`\`

### View test coverage report

After running the above command, open:

\`\`\`
target/site/jacoco/index.html
\`\`\`

in your browser to view code coverage.

---

# Library API Documentation

This RESTful API allows you to manage books and borrowers in a library system. It's built with Java 17 and Spring Boot, using MySQL as the database.

---

## Base URL

```
http://localhost:8080/api
```

---

## Book Endpoints

### Add a Book

- **URL**: `/books`
- **Method**: `POST`
- **Request Body**:
```json
{
  "title": "Test 1",
  "author": "Robert C. Martin",
  "isbn": "9781234567890"
}
```
- **Success Response**:
  - `201 Created`
  - Body: created book object
  - Example:
  ```json 
  {
      "id": 1,
      "isbn": "9781234567890",
      "title": "Test 1",
      "author": "Robert C. Martin",
      "borrowed": false
  }
  ```

---

### Get All Books

- **URL**: `/books`
- **Method**: `GET`
- **Success Response**:
  - `200 OK`
  - Body: list of books
  - Example: 
  ```json
  [
      {
          "id": 1,
          "isbn": "9781234567890",
          "title": "Clean Code",
          "author": "Robert C. Martin",
          "borrowed": false
      },
      {
          "id": 2,
          "isbn": "9781234567890",
          "title": "Test 1",
          "author": "Robert C. Martin",
          "borrowed": false
      }
  ]
  ```

## Borrower Endpoints

### Register a Borrower

- **URL**: `/borrowers`
- **Method**: `POST`
- **Request Body**:
```json
{
  "name": "Alice Johnson",
  "email": "alice@example.com"
}
```
- **Success Response**:
  - `201 Created`
  - Body: created borrower object
  - Example:
  ```json
  {
      "id": 3,
      "name": "Alice Johnson",
      "email": "alice@example.com"
  }
  ```

---

### Get All Borrowers

- **URL**: `/borrowers`
- **Method**: `GET`
- **Success Response**:
  - `200 OK`

---

### Borrow a Book

- **URL**: `/borrowers/{borrowerId}/borrow/{bookId}`
- **Method**: `POST`
- **Success Response**:
  - `200 OK`
  - Message: `"Book borrowed successfully"`

---

### Return a Book

- **URL**: `/borrowers/{borrowerId}/return/{bookId}`
- **Method**: `POST`
- **Success Response**:
  - `200 OK`
  - Message: `"Book returned successfully"`

---

## Testing

Use Postman or cURL to test endpoints:

**Example**:

```bash
curl -X POST http://localhost:8080/api/books \
-H "Content-Type: application/json" \
-d '{"title":"Test 1","author":"Robert C. Martin","isbn":"978-0132350884"}'
```

---

## Error Codes

- `400 Bad Request` – Invalid input
- `404 Not Found` – Resource not found
- `500 Internal Server Error` – Server-side error

---

## Author

**Amandi Gamage**  
Associate Technical Lead – Fullstack Developer  
