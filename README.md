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
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991"
}
```
- **Success Response**:
  - `201 Created`
  - Body: created book object
  - Example: 
{
    "id": 1,
    "isbn": "9781234567890",
    "title": "Test 1",
    "author": "Robert C. Martin",
    "borrowed": false
}

---

### Get All Books

- **URL**: `/books`
- **Method**: `GET`
- **Success Response**:
  - `200 OK`
  - Body: list of books
  - Example: 

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
{
    "id": 3,
    "name": "Alice Johnson",
    "email": "alice@example.com"
}

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

### 🔁 Return a Book

- **URL**: `/borrowers/{borrowerId}/return/{bookId}`
- **Method**: `POST`
- **Success Response**:
  - `200 OK`
  - Message: `"Book returned successfully"`

---

## 🧪 Testing

Use Postman or cURL to test endpoints:

**Example**:

```bash
curl -X POST http://localhost:8080/api/books \
-H "Content-Type: application/json" \
-d '{"title":"Test 1","author":"Robert C. Martin","isbn":"978-0132350884"}'
```

---

## 💬 Error Codes

- `400 Bad Request` – Invalid input
- `404 Not Found` – Resource not found
- `500 Internal Server Error` – Server-side error
