# 🚀 Distributed Task Queue System (Phase 1)

## 📌 Overview

This project implements a **Distributed Task Queue System** using **Java (Spring Boot)**.
The system allows users to submit tasks asynchronously, processes them using background workers, and tracks their execution status.

It follows a **producer-consumer architecture**, where:

* API acts as **producer**
* Worker acts as **consumer**
* Database acts as a **queue**

---

## 🎯 Features (Phase 1)

### ✅ Task Submission API

* Accepts task type and JSON payload
* Generates unique task ID (UUID)
* Stores task with `PENDING` status
* Returns task ID immediately (non-blocking)

### ✅ Background Worker

* Runs continuously
* Polls database for `PENDING` tasks
* Updates status:

  ```
  PENDING → RUNNING → COMPLETED / FAILED
  ```
* Handles exceptions gracefully

### ✅ Status Tracking

* Get task by ID
* View result or error
* List all tasks
* Filter by status

### ✅ Task Types Implemented

1. **email_send**

   * Simulates email sending
   * Payload: `to`, `subject`, `body`

2. **csv_process**

   * Processes CSV data (basic simulation)

3. **report_generation**

   * Simulates report creation

---

## 🧠 System Architecture

```
Frontend (UI)
      ↓
TaskController (API Layer)
      ↓
TaskService (Business Logic)
      ↓
TaskRepository (JPA)
      ↓
PostgreSQL (Database Queue)
      ↑
TaskWorker (Background Processor)
      ↓
TaskHandler (Email / CSV / Report)
```

---

## 🔁 Workflow

1. User submits task via API/UI
2. Task stored in DB with `PENDING` status
3. Worker picks task and sets `RUNNING`
4. TaskHandler executes logic
5. Final status updated:

   * `COMPLETED`
   * `FAILED` (with error)

---

## 🛠️ Tech Stack

| Layer      | Technology        |
| ---------- | ----------------- |
| Backend    | Spring Boot       |
| Database   | PostgreSQL (Neon) |
| ORM        | Spring Data JPA   |
| Testing    | JUnit             |
| UI         | HTML + JS         |
| Build Tool | Maven             |

---

## 📁 Project Structure

```
src/
 ├── main/
 │   ├── java/com/aditya/distributed_task_queue/
 │   │   ├── controller/
 │   │   ├── service/
 │   │   ├── repository/
 │   │   ├── model/
 │   │   ├── handler/
 │   │   ├── worker/
 │   │   └── config/
 │   └── resources/
 │       ├── application.properties
 │       ├── static/
 │       └── templates/
 └── test/
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone Repository

```bash
git clone <your-repo-url>
cd distributed-task-queue
```

---

### 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://<your-neon-url>
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Run Application

```bash
mvn spring-boot:run
```

---

## 🧪 API Usage

### 🔹 Create Task

**POST /tasks**

```json
{
  "taskType": "email_send",
  "payload": {
    "to": "demo@gmail.com",
    "subject": "Hello",
    "body": "Test email"
  }
}
```

---

### 🔹 Get Task Status

**GET /tasks/{id}**

---

### 🔹 List Tasks

**GET /tasks?status=COMPLETED**

---

## 🧪 Testing

Run tests using:

```bash
mvn test
```

Includes:

* Controller tests
* Service tests
* Handler tests
* Worker tests

---

## 🎨 UI Dashboard

* Submit tasks
* View real-time status
* Supports multiple task execution
* Visual status flow:

  * Pending → Running → Completed

---

## ⚠️ Error Handling

* API validation for invalid payloads
* Worker catches execution errors
* Failed tasks stored with error message

---

## 📊 Database Schema (Tasks Table)

| Column       | Description     |
| ------------ | --------------- |
| id           | UUID            |
| task_type    | Type of task    |
| payload      | JSON data       |
| status       | Task status     |
| result       | Output          |
| error        | Error message   |
| created_at   | Created time    |
| started_at   | Execution start |
| completed_at | Execution end   |

---

## 🚧 Limitations (Phase 1)

* CSV processing is simulated (not fully parsed)
* Single worker (no horizontal scaling)
* No retry mechanism
* No priority queue

---

## 🚀 Future Enhancements

* Priority queue
* Retry logic with backoff
* Distributed workers
* Dead Letter Queue (DLQ)
* Monitoring dashboard

---

## 🧠 Design Decisions

* Used **database as queue** for simplicity
* Chose **UUID** for distributed uniqueness
* Implemented **handler pattern** for extensibility
* Separation of concerns:

  * Controller → Service → Repository

---

## 🎯 Key Learnings

* Asynchronous processing
* Producer-consumer pattern
* Background job execution
* Error handling in distributed systems

---

## 👨‍💻 Author

**Aditya Halder**

---

## 📌 Submission Notes

* Phase 1 requirements fully implemented
* System is end-to-end functional
* Includes API, worker, UI, and tests
* Ready for review and demonstration

---
