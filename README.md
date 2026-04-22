# 🧠 Task Manager API (Spring Backend Learning Project)
🚧 Status: **In Progress**

## 📌 Overview

**Task Manager API** is a backend-focused project built with **Spring Boot** whose primary goal is **learning and applying clean backend architecture and API design principles**.

The project is intentionally focused on **backend engineering**, not UI development.
Its purpose is to demonstrate understanding of:

* REST API design
* Spring dependency injection
* Authentication flow
* Service-layer architecture
* Testable and maintainable backend structure

This project represents my transition from basic Java development into modern Spring-based backend development.

---

## 🎯 Project Goals

The main objective of this project is learning how to design a **healthy, scalable backend**.

Key learning goals:

* Understanding how Spring handles web requests
* Designing clean service & controller layers
* Separating responsibilities (Controller → Service → Repository)
* Implementing authentication logic
* Building a stable API before introducing a real database
* Writing testable business logic

---

## 🚧 Current Status

✅ Authentication system completed
✅ Token-based authorization
✅ User service
✅ Spring security filter chain
✅ Task management features in progress
🚧 Controller layer & API endpoints (integration with security filter)
🚧 Exception handling
🚧 Database migration planned

---

## 🏗️ Architecture

The project follows a simplified layered architecture:

### Layers

**Controllers**

* Handle HTTP requests
* Map endpoints
* Return API responses

**Services**

* Contain business logic
* Validate data
* Coordinate application flow

**Repositories**

* Abstract data access
* Provide persistence interface

**Mappers / DTOs**

* Separate API models from domain models
* Prevent entity exposure

---

## 🔐 Authentication System

Implemented features:

* User registration
* Login flow
* Token generation
* JWT-based authentication filter
* Protected endpoints

Authentication logic is fully separated from business logic to keep the backend modular and maintainable.

---

## 💾 Database Approach (JSON Storage)

Instead of using a traditional SQL database, the project currently uses a **JSON file as a storage layer**.

### Why JSON?

The foc us of this phase is:

* learning **Spring Web**
* designing **clean APIs**
* building **correct service architecture**

rather than configuring infrastructure.

The storage layer behaves like a repository abstraction, meaning:

✅ business logic does **not depend** on storage implementation
✅ database can later be replaced with PostgreSQL/MySQL without changing services

Future plan:

JSON Storage → JPA Repository → SQL Database

---

## ⚙️ Technologies Used

* Java 21
* Spring Boot
* Spring Web
* Spring Security (custom auth flow)
* JWT (simplified implementation)
* JUnit & Mockito
* Maven

---

## 🧪 Testing

The project includes unit tests for:

* Service layer logic
* Authentication scenarios
* Error handling

Testing focuses on verifying **behavior**, not framework internals.

---
## 🚀 Future Improvements

* Replace JSON storage with SQL database
* Role-based authorization system (Admin / Client roles)
* User permissions & access control
* Exception handling standardization

---

## 👨‍💻 Author

Backend learning project built as part of my journey toward a **Junior Backend Developer** role.

This project is actively developed while expanding knowledge of Spring ecosystem and backend engineering best practices.
