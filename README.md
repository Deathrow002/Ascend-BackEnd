# Ascend Book Service API

This is a simple CRUD (Create, Read, Update, Delete) API for managing books, built with Spring Boot. It allows you to manage books by storing and retrieving them from a database. The API allows you to create, read, update, and delete books.

## Features

- Add a new book
- Get all books
- Get books by author
- Get book by ID
- Update a book
- Delete a book

## Tech Stack

- **Java 22**
- **Spring Boot**
- **MySQL**
- **Maven**
- **Docker**
- **Postman**

## Getting Started

### 1. Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/book-service.git
cd book-service
```

### 2. Start App by Docker-Compose

To run the application and database in Docker containers, you can use the provided **Dockerfile** and **docker-compose.yml** files. Here’s how to set it up:

1. Build the Docker images:
   ```bash
   docker-compose build
   ```

2. Run the application and database containers:
   ```bash
   docker-compose up
   ```

This will start the Spring Boot application and the database in Docker containers. The application will be available at `http://localhost:8080`.

### 3. Testing with Postman

You can import the provided **Postman Collection** to easily test the API endpoints.

#### Steps to import the collection into Postman:

1. Open **Postman**.
2. Click **Import** in the top-left corner.
3. Choose **Raw Text** and paste the **Postman JSON Collection** provided.
4. Click **Import**.

The collection includes the following API operations:
- **Create Book**: POST `/books`
- **Get All Books**: GET `/books`
- **Get Books by Author**: GET `/books/author`
- **Get Book by ID**: GET `/books/{id}`
- **Update Book**: PUT `/books/{id}`
- **Delete Book**: DELETE `/books/{id}`

## API Endpoints

Here are the available API endpoints:

### `POST /books`
Create a new book.
- **Request body**:
  ```json
  {
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "publishedDate": "1925-04-10"
  }
  ```

### `GET /books`
Get all books.

### `GET /books/author?author=F. Scott Fitzgerald`
Get books by a specific author.

### `GET /books/{id}`
Get a book by its ID.

### `PUT /books/{id}`
Update an existing book.
- **Request body**:
  ```json
  {
    "title": "The Great Gatsby (Updated)",
    "author": "F. Scott Fitzgerald",
    "publishedDate": "1925-04-10"
  }
  ```

### `DELETE /books/{id}`
Delete a book by its ID.

## For Postman Collection

```json 
{
  "info": {
    "_postman_id": "abcd1234-efgh5678-ijkl9012-mnop3456",
    "name": "Book Service API",
    "description": "CRUD operations for managing books",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Create Book",
      "request": {
        "method": "POST",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"title\": \"The Great Gatsby\", \"author\": \"F. Scott Fitzgerald\", \"publishedDate\": \"1925-04-10\"}"
        },
        "url": {
          "raw": "http://localhost:8080/books",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "books"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get All Books",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8080/books",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "books"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get Books by Author",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8080/books/author?author=F. Scott Fitzgerald",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "books",
            "author"
          ],
          "query": [
            {
              "key": "author",
              "value": "F. Scott Fitzgerald"
            }
          ]
        }
      },
      "response": []
    },
    {
      "name": "Get Book by ID",
      "request": {
        "method": "GET",
        "url": {
          "raw": "http://localhost:8080/books/1",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "books",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Update Book",
      "request": {
        "method": "PUT",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\"title\": \"The Great Gatsby (Updated)\", \"author\": \"F. Scott Fitzgerald\", \"publishedDate\": \"1925-04-10\"}"
        },
        "url": {
          "raw": "http://localhost:8080/books/1",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "books",
            "1"
          ]
        }
      },
      "response": []
    },
    {
      "name": "Delete Book",
      "request": {
        "method": "DELETE",
        "url": {
          "raw": "http://localhost:8080/books/1",
          "host": [
            "localhost"
          ],
          "port": "8080",
          "path": [
            "books",
            "1"
          ]
        }
      },
      "response": []
    }
  ]
}

```