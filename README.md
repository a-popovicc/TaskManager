# 🧠 Task Manager API

Backend REST API built with **Spring Boot** as a learning project focused on designing a clean, maintainable backend architecture.

The goal of this project was to transition from core Java development to real-world **Spring backend engineering** by implementing authentication, layered architecture, and production-like API design.

---
## 🧩 Key Learning Outcomes

Through this project I worked with several concepts for the first time:

- building and exposing REST endpoints and understanding backend–client communication
- implementing JWT security in Spring
- designing a global exception handling system
  
---

## 🏗️ Architecture

The application follows a **layered backend architecture**:

- **Controller Layer** — handles HTTP requests & responses  
- **Service Layer** — business logic and validation  
- **Repository Layer** — data abstraction  
- **DTO & Mapper Layer** — separates API models from domain models  

This structure keeps the project scalable, testable, and easy to extend.


---

## 💾 Storage Strategy

The application uses JSON-based storage to prioritize learning backend design, Spring request handling, and API communication before introducing a relational database.


---

## ⚙️ Tech Stack

- Java 21  
- Spring Boot  
- Spring Web  
- Spring Security  
- JWT  
- Maven  
- JUnit & Mockito  

---

## 🚀 Features

- User registration & login  
- JWT authentication & authorization  
- Protected API endpoints  
- Task management system (CRUD)  
- Global exception handling  
- Layered architecture (Controller → Service → Repository)  
- DTO mapping & entity isolation  
- Unit testing with JUnit & Mockito  

---

## 🧪 Testing

Unit tests cover:

- service-layer business logic
- authentication scenarios
- exception handling behavior

---

## 🖥️ Frontend Client

This project also includes a simple frontend application used for testing and demonstrating the API.

Frontend repository:
👉 https://github.com/a-popovicc/TaskManager-front

### Running the full application

Run both projects simultaneously:

- **Backend (Task Manager API)** → http://localhost:8080  
- **Frontend Client** → http://localhost:5173  

The frontend communicates with the backend API to demonstrate authentication, protected routes, and task management features.
