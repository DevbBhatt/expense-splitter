# 💸 Expense Splitter Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-brightgreen)
![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-success)
![Database](https://img.shields.io/badge/Database-TiDB_Cloud-blue)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED)
![Render](https://img.shields.io/badge/Deploy-Render-46E3B7)
![License](https://img.shields.io/badge/Status-Active-success)

Backend REST API for **Expense Splitter**, a full-stack application inspired by Splitwise that helps users manage shared expenses, calculate balances, and simplify settlements within groups.

---

# 🚀 Live API

**Backend**

https://expense-splitter-backend-eb7u.onrender.com/

**Swagger API Documentation**

https://expense-splitter-backend-eb7u.onrender.com/swagger-ui/index.html

---

# ✨ Features

### 🔐 Authentication

- User Registration
- User Login
- JWT Authentication
- Password Encryption using BCrypt
- Protected APIs with Spring Security

### 👥 Group Management

- Create Group
- View Groups
- Delete Group
- Soft Delete Support

### 👤 Member Management

- Add Members
- Remove Members
- Group Membership Validation

### 💸 Expense Management

- Add Expense
- Delete Expense
- Equal Split
- Exact Split
- Input Validation

### 💰 Balance Management

- Calculate Group Balances
- Calculate User Balances
- Settlement Suggestions
- Optimized Debt Simplification

### 🛡 Exception Handling

- Global Exception Handler
- Consistent API Response Structure
- Validation Error Handling

---

# 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT
- Maven
- TiDB Cloud (MySQL Compatible)
- Docker
- Render

---

# 🏗 Project Architecture

```
Controller
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
Database (TiDB Cloud)
```

Project follows a layered architecture for better maintainability and separation of concerns.

---

# 📂 Project Structure

```
src
└── main
    ├── controller
    ├── service
    ├── repository
    ├── entity
    ├── dto
    ├── security
    ├── config
    ├── exception
    └── ExpenseSplitterApplication
```

---

# 🔐 Authentication APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/auth/signup` | Register User |
| POST | `/auth/login` | Login User |

---

# 📁 Group APIs

| Method | Endpoint |
|---------|----------|
| GET | `/groups` |
| POST | `/groups` |
| GET | `/groups/{id}` |
| DELETE | `/groups/{id}` |

---

# 👥 Member APIs

| Method | Endpoint |
|---------|----------|
| POST | `/groups/{groupId}/members/{userId}` |
| DELETE | `/groups/{groupId}/members/{userId}` |
| GET | `/groups/members/{groupId}` |

---

# 💸 Expense APIs

| Method | Endpoint |
|---------|----------|
| POST | `/expenses/groups/{groupId}/expenses` |
| GET | `/expenses/groups/{groupId}/expenses` |
| DELETE | `/expenses/{expenseId}` |

---

# 💰 Balance APIs

| Method | Endpoint |
|---------|----------|
| GET | `/balances/groups/{groupId}/balances` |
| GET | `/balances/groups/{groupId}/settlements` |
| GET | `/balances/me` |

---

# 🔑 Environment Variables

Create an `.env` (or configure environment variables in your deployment platform):

```properties
DB_URL=
DB_USERNAME=
DB_PASSWORD=
JWT_SECRET=
```

---

# ⚙ Running Locally

## Clone Repository

```bash
git clone https://github.com/DevbBhatt/expense-splitter-backend.git
```

Move into project

```bash
cd expense-splitter-backend
```

Run

```bash
./mvnw spring-boot:run
```

---

# 🐳 Docker

Build

```bash
docker build -t expense-splitter-backend .
```

Run

```bash
docker run -p 8080:8080 expense-splitter-backend
```

---

# 🌐 Deployment

| Service | Platform |
|----------|----------|
| Backend | Render |
| Database | TiDB Cloud |

---

# 🚀 Future Improvements

- Email Verification
- Password Reset
- Recurring Expenses
- Expense Categories
- Notifications
- File Attachments
- Group Invitations
- Audit Logs

---

# 👨‍💻 Author

**Dev Bhatt**

GitHub

https://github.com/DevbBhatt

LinkedIn

https://www.linkedin.com/in/dev-bhatt-9825b12bb/

---

## ⭐ If you like this project, consider giving it a star!