# 🔐 JWT Authentication & Authorization

A production-inspired **Spring Boot REST API** demonstrating secure authentication, role-based access control (RBAC), and permission-based authorization using **JWT (JSON Web Token)**. This project follows a clean layered architecture and is designed to showcase backend development best practices with Spring Security.

---

## 🚀 Features

### 🔑 Authentication
- User Registration
- User Login
- BCrypt Password Encryption
- JWT Token Generation
- Stateless Authentication
- Custom JWT Authentication Filter

### 👤 Profile
- View Logged-in User Profile

### 👥 User Management
- Admin Create User
- Admin Update User
- Get All Users
- Assign Role to User
- Remove Role from User

### 📦 Product Management
- Add Product
- Get All Products
- Get Product By ID
- Search Product By Name (Supports Partial Search)
- Update Product
- Delete Product

### 🛡 Authorization
- Role-Based Access Control (RBAC)
- Permission-Based Authorization
- Method Level Security using `@PreAuthorize`
- Multiple Roles Per User
- Multiple Permissions Per Role

### ⚙ System
- Global Exception Handling
- Standard API Response Structure
- Data Initialization (Roles, Permissions & Admin)
- Validation using Jakarta Validation
- Clean Layered Architecture

---

# 🛠 Tech Stack

| Technology | Description |
|------------|-------------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Stateless Authentication |
| Spring Data JPA | Database ORM |
| Hibernate | ORM Implementation |
| MySQL | Database |
| Maven | Dependency Management |
| Lombok | Boilerplate Reduction |
| Jakarta Validation | Request Validation |

---

# 📂 Project Structure

```
src
└── main
    └── java
        └── com.jwt_auth
            ├── bootstrap
            ├── constants
            ├── controller
            ├── dto
            │   ├── request
            │   └── response
            ├── entity
            ├── exception
            ├── repository
            ├── security
            ├── service
            │   └── impl
            └── JwtAuthApplication
```

---

# 🏗 Architecture

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

Authentication Flow

```
Client
   │
   ▼
JWT Filter
   │
   ▼
JWT Validation
   │
   ▼
Security Context
   │
   ▼
Controller
```

Authorization Flow

```
User
 │
 ▼
Roles
 │
 ▼
Permissions
 │
 ▼
@PreAuthorize
```

---

# 🔐 Role & Permission Model

A user can have multiple roles.

A role can contain multiple permissions.

Permissions determine which operations a user is allowed to perform.

Example:

```
USER
├── PROFILE_READ
├── GET_ALL_PRODUCT
├── GET_PRODUCT_BY_ID
└── GET_PRODUCT_BY_NAME

MANAGER
├── PROFILE_READ
├── ADD_PRODUCT
├── UPDATE_PRODUCT
├── GET_ALL_USERS
├── GET_USER_BY_ID
├── GET_USER_BY_USERNAME

ADMIN
├── PROFILE_READ
├── ADD_PRODUCT
├── UPDATE_PRODUCT
├── DELETE_PRODUCT
├── GET_ALL_USERS
├── ADD_USER
├── UPDATE_USER
├── DELETE_USER
├── ADD_ROLE
└── DELETE_ROLE
```

---

# 📋 API Modules

## Authentication

```
POST    /api/v1/auth/register
POST    /api/v1/auth/login
```

---

## Profile

```
GET     /api/v1/profile
```

---

## Products

```
POST    /api/v1/products
GET     /api/v1/products
GET     /api/v1/products/{id}
GET     /api/v1/products/search
PUT     /api/v1/products/{id}
DELETE  /api/v1/products/{id}
```

---

## Users

```
POST    /api/v1/users
GET     /api/v1/users
PUT     /api/v1/users/{id}
PATCH   /api/v1/users/{id}/roles
DELETE  /api/v1/users/{id}/roles
```

---

# 📦 API Response Format

Successful Response

```json
{
  "data": {},
  "message": "Operation completed successfully"
}
```

Error Response

```json
{
  "message": "Resource not found"
}
```

---

# 🔒 Security Highlights

- JWT Based Authentication
- Stateless Session Management
- BCrypt Password Hashing
- Role-Based Access Control (RBAC)
- Permission-Based Authorization
- Method Level Security (`@PreAuthorize`)
- Custom UserDetailsService
- Custom JWT Authentication Filter
- Global Exception Handling

---

# ⚙ Getting Started

## Clone Repository

```bash
git clone https://github.com/your-username/jwt-auth.git
```

---

## Configure Database

Update your `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/jwt_auth
    username: root
    password: your_password
```

---

## Run

```bash
mvn spring-boot:run
```

or

Run `JwtAuthApplication.java`

---

# 📚 Learning Outcomes

This project helped me gain hands-on experience with:

- Spring Security
- JWT Authentication
- Role-Based Access Control (RBAC)
- Permission-Based Authorization
- Spring Data JPA
- Layered Architecture
- Custom Exception Handling
- REST API Design
- Secure Password Storage
- API Validation
- Clean Code Practices

---

# 🚀 Future Improvements

- Refresh Token Support
- Email Verification
- Forgot Password
- Account Activation
- Pagination & Sorting
- Product Categories
- Product Images
- Order Management
- Shopping Cart
- Wishlist
- Review & Rating System
- Docker Support
- Redis Caching
- Flyway Database Migration
- Unit & Integration Testing
- OpenAPI / Swagger Documentation
- Audit Logging

---

# 👨‍💻 Author

**Sakibul Hasan Sakib**

Backend Developer | Java | Spring Boot | Spring Security

If you found this project helpful, consider giving it a ⭐ on GitHub!
