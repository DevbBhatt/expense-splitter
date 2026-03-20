# 💸 Expense Splitter Backend

A Spring Boot based backend application that allows users to split expenses within groups, similar to Splitwise.

---

## 🚀 Features

### 👤 User Management

* Create, update, delete users
* Unique email validation

### 👥 Group Management

* Create groups
* Add/remove members

### 💰 Expense Management

* Add expenses in a group
* Track who paid

### 🔀 Expense Splitting

* Equal Split
* Exact Split

### 📊 Balance Calculation

* Group-wise balance tracking
* User-wise balance

### ⚖️ Settlement Feature

* Debt minimization using greedy algorithm
* Generates optimized transactions

---

## 🧠 Core Logic

### Debt Minimization Algorithm

The system minimizes the number of transactions using a greedy approach:

* Identify creditors (users with positive balance)
* Identify debtors (users with negative balance)
* Sort creditors in descending order
* Sort debtors in ascending order
* Match highest debtor with highest creditor
* Settle the maximum possible amount
* Repeat until all balances become zero

---

## 🏗️ Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL
* Lombok
* Maven

---

## 📂 Project Structure

```
controller/
service/
repository/
entity/
dto/
config/
exception/
```

---

## 🔌 API Endpoints

### 👤 User

* POST `/users`
* GET `/users`
* GET `/users/{id}`
* PUT `/users/{id}`
* DELETE `/users/{id}`

---

### 👥 Group

* POST `/groups`
* GET `/groups/{id}`
* DELETE `/groups/{id}`

---

### 👥 Group Members

* POST `/groups/{groupId}/members/{userId}`
* DELETE `/groups/{groupId}/members/{userId}`

---

### 💰 Expense

* POST `/groups/{groupId}/expenses/{userId}`
* GET `/groups/{groupId}/expenses`

---

### 📊 Balance

* GET `/groups/{groupId}/balances`
* GET `/groups/{groupId}/balances/{userId}`

---

### ⚖️ Settlement

* GET `/groups/{groupId}/settlements`

---

## 📌 Example Settlement Output

```json
[
  {
    "fromUserName": "Rahul",
    "toUserName": "Dev",
    "amount": 500
  }
]
```

---

## ▶️ How to Run

1. Clone the repository
2. Configure MySQL in `application.properties`
3. Run the application

```bash
mvn spring-boot:run
```

---

## 🔮 Future Improvements

* Add authentication (JWT)
* Add expense categories
* Frontend integration
* Real-time notifications

---
