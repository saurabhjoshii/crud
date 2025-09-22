# Spring Boot CRUD Operation API

A comprehensive REST API for managing user details with full CRUD operations, built with Spring Boot 3.3.1 and MySQL.

## 🚀 Features

- **Complete CRUD Operations**: Create, Read, Update, Delete users
- **Advanced Search**: Search users by firstName, emailId, or phoneNo
- **Data Validation**: Comprehensive validation with meaningful error messages
- **Exception Handling**: Custom exceptions with proper HTTP status codes
- **Database Integration**: MySQL with JPA/Hibernate
- **RESTful Design**: Follows REST API best practices
- **Transaction Management**: Ensures data consistency

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.3.1**
- **Spring Data JPA**
- **MySQL 8.0**
- **Lombok**
- **Hibernate Validator**
- **Maven**

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd Project
```

### 2. Database Setup
Create a MySQL database and update the configuration in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crudapplication
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:7777/app`

## 📚 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/users` | Create a new user |
| GET | `/api/v1/users` | Get all users |
| GET | `/api/v1/users/{id}` | Get user by ID |
| GET | `/api/v1/users/search` | Search users |
| PUT | `/api/v1/users/{id}` | Update user |
| DELETE | `/api/v1/users/{id}` | Delete user |

### Example API Usage

#### Create User
```bash
curl -X POST http://localhost:7777/app/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "emailId": "john.doe@example.com",
    "phoneNo": "1234567890",
    "age": 25
  }'
```

#### Get All Users
```bash
curl -X GET http://localhost:7777/app/api/v1/users
```

#### Search Users
```bash
curl -X GET "http://localhost:7777/app/api/v1/users/search?firstName=John"
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/crud/operation/
│   │   ├── controller/
│   │   │   ├── IRestController.java
│   │   │   └── impl/
│   │   │       └── RestControllerImpl.java
│   │   ├── entity/
│   │   │   └── UserDetail.java
│   │   ├── model/
│   │   │   └── UserDetailModel.java
│   │   ├── repository/
│   │   │   └── UserDetailRepository.java
│   │   ├── service/
│   │   │   ├── IService.java
│   │   │   └── impl/
│   │   │       └── ServiceImpl.java
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── UserNotFoundException.java
│   │   │   └── UserAlreadyExistsException.java
│   │   └── CrudOperationApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/crud/operation/
        └── CrudOperationApplicationTests.java
```

## 🔧 Configuration

### Database Configuration
The application uses MySQL with the following default configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/crudapplication
spring.datasource.username=windows2024
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### JPA Configuration
```properties
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.javax.persistence.validation.mode=auto
```

## 📝 Data Model

### UserDetail Entity
- `id`: Primary key (auto-generated)
- `firstName`: Required, 2-50 characters
- `lastName`: Optional, max 50 characters
- `emailId`: Required, valid email format, unique
- `phoneNo`: Required, exactly 10 digits, unique
- `age`: Required, between 18-58
- `createdAt`: Auto-generated timestamp
- `updatedAt`: Auto-updated timestamp

## ✅ Validation Rules

### Create User (UserDetail)
- All fields are required and validated
- Email and phone number must be unique
- Age must be between 18-58

### Update User (UserDetailModel)
- All fields are optional
- Same validation rules apply when provided
- Phone number cannot be updated (immutable)

## 🚨 Error Handling

The API provides comprehensive error handling with appropriate HTTP status codes:

- **400 Bad Request**: Validation errors, malformed requests
- **404 Not Found**: User not found
- **409 Conflict**: Duplicate email or phone number
- **500 Internal Server Error**: Unexpected server errors

### Error Response Format
```json
{
    "error": "Error Type",
    "message": "Detailed error message",
    "status": 400
}
```

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Test with Postman/curl
Import the API collection or use the provided curl commands in the API documentation.

## 📖 Documentation

For detailed API documentation, see [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

## 🔍 Key Learning Areas

This project demonstrates:

1. **Spring Boot Fundamentals**
   - Auto-configuration
   - Starter dependencies
   - Application properties

2. **Spring Data JPA**
   - Entity mapping
   - Repository pattern
   - Query methods

3. **REST API Design**
   - RESTful endpoints
   - HTTP methods
   - Status codes

4. **Validation**
   - Bean validation
   - Custom validators
   - Error handling

5. **Exception Handling**
   - Global exception handler
   - Custom exceptions
   - Error responses

6. **Database Integration**
   - MySQL configuration
   - JPA annotations
   - Transaction management

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

Created as a learning project for Spring Boot CRUD operations.

---

**Note**: This project is designed for educational purposes and demonstrates best practices for building REST APIs with Spring Boot.
