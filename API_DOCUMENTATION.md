# CRUD Operation API Documentation

## Overview
This is a Spring Boot REST API for managing user details with full CRUD operations.

## Base URL
```
http://localhost:7777/app/api/v1
```

## Endpoints

### 1. Create User
**POST** `/users`

Creates a new user in the system.

**Request Body:**
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "emailId": "john.doe@example.com",
    "phoneNo": "1234567890",
    "age": 25
}
```

**Response:** `201 Created`
```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "emailId": "john.doe@example.com",
    "phoneNo": "1234567890",
    "age": 25
}
```

### 2. Get User by ID
**GET** `/users/{id}`

Retrieves a specific user by their ID.

**Response:** `200 OK`
```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "emailId": "john.doe@example.com",
    "phoneNo": "1234567890",
    "age": 25
}
```

### 3. Get All Users
**GET** `/users`

Retrieves all users in the system.

**Response:** `200 OK`
```json
[
    {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "emailId": "john.doe@example.com",
        "phoneNo": "1234567890",
        "age": 25
    }
]
```

### 4. Search Users
**GET** `/users/search`

Search users by firstName, emailId, or phoneNo.

**Query Parameters:**
- `firstName` (optional): Search by first name
- `emailId` (optional): Search by email
- `phoneNo` (optional): Search by phone number

**Example:** `GET /users/search?firstName=John`

**Response:** `200 OK`
```json
[
    {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "emailId": "john.doe@example.com",
        "phoneNo": "1234567890",
        "age": 25
    }
]
```

### 5. Update User
**PUT** `/users/{id}`

Updates an existing user. All fields are optional in the request body.

**Request Body:**
```json
{
    "firstName": "Jane",
    "lastName": "Smith",
    "emailId": "jane.smith@example.com",
    "age": 30
}
```

**Response:** `200 OK`
```json
{
    "id": 1,
    "firstName": "Jane",
    "lastName": "Smith",
    "emailId": "jane.smith@example.com",
    "phoneNo": "1234567890",
    "age": 30
}
```

### 6. Delete User
**DELETE** `/users/{id}`

Deletes a user by their ID.

**Response:** `200 OK`
```json
{
    "message": "User deleted successfully",
    "id": "1"
}
```

## Error Responses

### 400 Bad Request
```json
{
    "error": "Validation Failed",
    "message": "Please check the following fields",
    "fieldErrors": {
        "firstName": "First Name is mandatory",
        "emailId": "Enter valid email address"
    },
    "status": 400
}
```

### 404 Not Found
```json
{
    "error": "User Not Found",
    "message": "User with id 999 not found",
    "status": 404
}
```

### 409 Conflict
```json
{
    "error": "User Already Exists",
    "message": "User with email john.doe@example.com already exists",
    "status": 409
}
```

### 500 Internal Server Error
```json
{
    "error": "Internal Server Error",
    "message": "An unexpected error occurred. Please try again later.",
    "status": 500
}
```

## Validation Rules

### UserDetail Entity (for creation)
- `firstName`: Required, 2-50 characters
- `lastName`: Optional, max 50 characters
- `emailId`: Required, valid email format, max 100 characters, unique
- `phoneNo`: Required, exactly 10 digits, unique
- `age`: Required, between 18-58

### UserDetailModel (for updates)
- All fields are optional
- Same validation rules apply when provided
- `phoneNo` cannot be updated (immutable)

## Database Configuration
- **Database:** MySQL
- **Host:** localhost:3306
- **Database Name:** crudapplication
- **Username:** windows2024
- **Password:** 1234

## Testing the API

You can test the API using tools like:
- Postman
- curl
- Insomnia
- Any REST client

### Example curl commands:

```bash
# Create a user
curl -X POST http://localhost:7777/app/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "emailId": "john.doe@example.com",
    "phoneNo": "1234567890",
    "age": 25
  }'

# Get all users
curl -X GET http://localhost:7777/app/api/v1/users

# Get user by ID
curl -X GET http://localhost:7777/app/api/v1/users/1

# Update user
curl -X PUT http://localhost:7777/app/api/v1/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Jane",
    "age": 30
  }'

# Delete user
curl -X DELETE http://localhost:7777/app/api/v1/users/1
```
