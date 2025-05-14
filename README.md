# Blockbuster API - Endpoint Documentation


## Movies

### Get All Movies
- **Endpoint:** `GET /api/movies`
- **Description:** Retrieve a list of all movies.
- **Sample Request:**
  ```http
  GET /api/movies
  ```
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "title": "The Shawshank Redemption",
      "releaseYear": 1994,
      "genre": "DRAMA",
      "rented": false
    },
    {
      "id": 2,
      "title": "The Godfather",
      "releaseYear": 1972,
      "genre": "DRAMA",
      "rented": false
    }
  ]
  ```

---

### Get Movie by ID
- **Endpoint:** `GET /api/movies/{id}`
- **Description:** Retrieve a movie by its ID.
- **Path Parameter:**
  - `id` (Long): Movie ID
- **Sample Request:**
  ```http
  GET /api/movies/1
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "title": "The Shawshank Redemption",
    "releaseYear": 1994,
    "genre": "DRAMA",
    "rented": false
  }
  ```

---

### Search Movies by Title
- **Endpoint:** `GET /api/movies/search`
- **Query Parameter:**
  - `title` (String): Movie title (partial or full, case-insensitive)
- **Description:** Search for movies by title.
- **Sample Request:**
  ```http
  GET /api/movies/search?title=shawshank
  ```
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "title": "The Shawshank Redemption",
      "releaseYear": 1994,
      "genre": "DRAMA",
      "rented": false
    }
  ]
  ```

---

### Find Movies by Release Year Range
- **Endpoint:** `GET /api/movies/range`
- **Query Parameters:**
  - `startYear` (int): Start year
  - `endYear` (int): End year
- **Description:** Find movies released between two years.
- **Sample Request:**
  ```http
  GET /api/movies/range?startYear=1990&endYear=2000
  ```
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "title": "The Shawshank Redemption",
      "releaseYear": 1994,
      "genre": "DRAMA",
      "rented": false
    },
    {
      "id": 4,
      "title": "Pulp Fiction",
      "releaseYear": 1994,
      "genre": "ACTION",
      "rented": false
    }
  ]
  ```

---

### Add a New Movie
- **Endpoint:** `POST /api/movies`
- **Body:**
  - JSON object representing a movie.
- **Description:** Add a new movie.
- **Sample Request:**
  ```http
  POST /api/movies
  Content-Type: application/json

  {
    "title": "Inception",
    "releaseYear": 2010,
    "genre": "ACTION"
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 11,
    "title": "Inception",
    "releaseYear": 2010,
    "genre": "ACTION",
    "rented": false
  }
  ```

---

### Update Movie
- **Endpoint:** `PUT /api/movies/{id}`
- **Path Parameter:**
  - `id` (Long): Movie ID
- **Body:**
  - JSON object representing updated movie data.
- **Description:** Update an existing movie.
- **Sample Request:**
  ```http
  PUT /api/movies/1
  Content-Type: application/json

  {
    "title": "The Shawshank Redemption (Remastered)",
    "releaseYear": 1994,
    "genre": "DRAMA"
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "title": "The Shawshank Redemption (Remastered)",
    "releaseYear": 1994,
    "genre": "DRAMA",
    "rented": false
  }
  ```

---

### Delete Movie
- **Endpoint:** `DELETE /api/movies/{id}`
- **Path Parameter:**
  - `id` (Long): Movie ID
- **Description:** Delete a movie by ID.
- **Sample Request:**
  ```http
  DELETE /api/movies/1
  ```
- **Sample Response:**
  - HTTP 204 No Content

---

## Customers

### Create Customer
- **Endpoint:** `POST /api/customers`
- **Body:**
  - JSON object representing a customer.
- **Description:** Create a new customer.
- **Sample Request:**
  ```http
  POST /api/customers
  Content-Type: application/json

  {
    "first_name": "Alice",
    "last_name": "Johnson",
    "email": "alice.johnson@example.com"
  }
  ```
- **Sample Response:**
  ```json
  {
    "id": 5,
    "first_name": "Alice",
    "last_name": "Johnson",
    "email": "alice.johnson@example.com",
    "registrationDate": "2025-04-22"
  }
  ```

---

### Get Customer by ID
- **Endpoint:** `GET /api/customers/{id}`
- **Path Parameter:**
  - `id` (Long): Customer ID
- **Description:** Retrieve a customer by ID.
- **Sample Request:**
  ```http
  GET /api/customers/1
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "first_name": "John",
    "last_name": "Doe",
    "email": "john.doe@example.com",
    "registrationDate": "2021-01-15"
  }
  ```

---

### Get All Customers
- **Endpoint:** `GET /api/customers`
- **Description:** Retrieve all customers.
- **Sample Request:**
  ```http
  GET /api/customers
  ```
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "first_name": "John",
      "last_name": "Doe",
      "email": "john.doe@example.com",
      "registrationDate": "2021-01-15"
    },
    {
      "id": 2,
      "first_name": "Jane",
      "last_name": "Smith",
      "email": "jane.smith@example.com",
      "registrationDate": "2020-05-20"
    }
  ]
  ```

---

### Search Customer by Email or Name
- **Endpoint:** `GET /api/customers/search`
- **Query Parameters:**
  - `email` (String, optional): Customer email
  - `name` (String, optional): Customer name (partial or full, case-insensitive)
- **Description:** Search for a customer by email or name.
- **Sample Request (by email):**
  ```http
  GET /api/customers/search?email=john.doe@example.com
  ```
- **Sample Response:**
  ```json
  {
    "id": 1,
    "first_name": "John",
    "last_name": "Doe",
    "email": "john.doe@example.com",
    "registrationDate": "2021-01-15"
  }
  ```
- **Sample Request (by name):**
  ```http
  GET /api/customers/search?name=Jane
  ```
- **Sample Response:**
  ```json
  [
    {
      "id": 2,
      "first_name": "Jane",
      "last_name": "Smith",
      "email": "jane.smith@example.com",
      "registrationDate": "2020-05-20"
    }
  ]
  ```

---

### Get Customers by Registration Date Range
- **Endpoint:** `GET /api/customers/search/registration-date`
- **Query Parameters:**
  - `start` (ISO date): Start date
  - `end` (ISO date): End date
- **Description:** Get customers registered between two dates.
- **Sample Request:**
  ```http
  GET /api/customers/search/registration-date?start=2020-01-01&end=2022-01-01
  ```
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "first_name": "John",
      "last_name": "Doe",
      "email": "john.doe@example.com",
      "registrationDate": "2021-01-15"
    },
    {
      "id": 2,
      "first_name": "Jane",
      "last_name": "Smith",
      "email": "jane.smith@example.com",
      "registrationDate": "2020-05-20"
    }
  ]
  ```

---

### Get Customers Registered After a Date
- **Endpoint:** `GET /api/customers/search/registered-after`
- **Query Parameter:**
  - `date` (ISO date): Date
- **Description:** Get customers registered after a specific date.
- **Sample Request:**
  ```http
  GET /api/customers/search/registered-after?date=2021-01-01
  ```
- **Sample Response:**
  ```json
  [
    {
      "id": 1,
      "first_name": "John",
      "last_name": "Doe",
      "email": "john.doe@example.com",
      "registrationDate": "2021-01-15"
    },
    {
      "id": 4,
      "first_name": "Bob",
      "last_name": "Brown",
      "email": "bob.brown@example.com",
      "registrationDate": "2022-07-25"
    }
  ]
  ```

---

### Delete Customer
- **Endpoint:** `DELETE /api/customers/{id}`
- **Path Parameter:**
  - `id` (Long): Customer ID
- **Description:** Delete a customer by ID.
- **Sample Request:**
  ```http
  DELETE /api/customers/1
  ```
- **Sample Response:**
  - HTTP 204 No Content

---

**Note:**
- All endpoints expect and return JSON unless otherwise specified.
- Error responses follow standard HTTP status codes.
