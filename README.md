# SmartExpenseTracker
Developed a secure Smart Expense Tracker using Java, Spring Boot, Spring Security, JWT, Hibernate, and MySQL. Implemented user authentication, expense management (CRUD), RESTful APIs, exception handling, and database integration following a layered architecture to ensure scalability, maintainability, and secure access.

# 💰 Smart Expense Tracker

A secure and scalable **Expense Management REST API** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, **Hibernate**, and **MySQL**.

The application enables users to securely manage their daily expenses through REST APIs by providing authentication, expense management, and monthly expense tracking features.

---

# 📌 Project Overview

Managing daily expenses manually can be difficult and time-consuming. Smart Expense Tracker provides a secure backend system that allows users to record, update, delete, and analyze their expenses efficiently.

The application follows industry-standard architecture and security practices, making it suitable for enterprise-level backend development.

---

# 🚀 Features

### User Management

- User Registration
- User Login
- JWT Authentication
- Password Encryption
- Secure API Access

### Expense Management

- Add Expense
- Update Expense
- Delete Expense
- View Single Expense
- View All Expenses
- Monthly Expense Summary

### Security

- Spring Security
- JWT Token Authentication
- BCrypt Password Encoding
- Stateless Authentication
- Role-Based API Protection

### Exception Handling

- Global Exception Handler
- Resource Not Found Exception
- Invalid Request Handling
- Unauthorized Access Handling

---

# 🛠 Technology Stack

| Technology | Description |
|------------|-------------|
| Java 21 | Programming Language |
| Spring Boot | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Token-Based Security |
| Spring Data JPA | Database Layer |
| Hibernate | ORM Framework |
| Maven | Dependency Management |
| MySQL | Database |
| Lombok | Boilerplate Code Reduction |

---

# 📂 Project Structure

```
SmartExpenseTracker
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── config
│   │   │   ├── controller
│   │   │   ├── dto
│   │   │   ├── entity
│   │   │   ├── exception
│   │   │   ├── repository
│   │   │   ├── security
│   │   │   ├── service
│   │   │   └── SmartExpenseTrackerApplication
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
├── README.md
└── .gitignore
```

---

# 🔐 Authentication Flow

```
Register User
      │
      ▼
Login
      │
      ▼
Generate JWT Token
      │
      ▼
Authorization: Bearer <JWT_TOKEN>
      │
      ▼
Access Protected APIs
```

---

# 📡 REST API Endpoints

## Authentication APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /register | Register a new user |
| POST | /login | Authenticate user |

---

## Expense APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /expenses | Add Expense |
| GET | /expenses | Get All Expenses |
| GET | /expenses/{id} | Get Expense by ID |
| PUT | /expenses/{id} | Update Expense |
| DELETE | /expenses/{id} | Delete Expense |

---

# ⚙ Database Configuration

Configure your database in

```
application.properties
```

Example

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶ How to Run the Project

### Clone the repository

```bash
git clone https://github.com/yourusername/SmartExpenseTracker.git
```

### Navigate to the project

```bash
cd SmartExpenseTracker
```

### Build the project

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

or run

```
SmartExpenseTrackerApplication.java
```

---

# 📈 Future Enhancements

- Expense Categories
- Budget Planning
- Email Notifications
- Monthly Reports
- PDF & Excel Export
- Dashboard with Charts
- Docker Deployment
- Cloud Deployment (AWS/Azure)
- Unit Testing with JUnit & Mockito
- Swagger/OpenAPI Documentation

---

# 👨‍💻 Author

**Tarun Choudhary**

Backend Developer | Java | Spring Boot | MySQL

---

# ⭐ If you found this project useful

Please consider giving it a **Star ⭐** on GitHub.
