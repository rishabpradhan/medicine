# 💊 Medicine Reminder & Health Monitoring System

A full-stack web application that helps users manage their medications, receive timely reminders, and track health metrics, while providing administrators with insights into patient medication usage and side effects.

Built using **Java Spring Boot**, **Angular**, and **PostgreSQL**, following **Domain-Driven Design (DDD)** and **Clean Architecture** principles with **JWT-based authentication**.

---

## 🚀 Features

### 👤 User Features
- Secure user registration and login using **JWT authentication**
- Create, update, and manage **medicine reminders**
- Receive **email notifications** for scheduled medicine intake
- Add and track **body metrics** such as:
  - Weight
  - Height
  - Body temperature
  - Other health indicators
- Manage **multiple medicines**, each with:
  - Individual reminder schedules
  - Associated **side effects**
- View personal medication history and health data in a structured dashboard

---

### 🛠️ Admin Features
- Secure admin dashboard
- View all registered patients
- Monitor:
  - Medicines taken by each patient
  - Reported side effects per medicine
  - Associated body metrics
- Gain insights into patient health trends and medication impact

---

## 🏗️ System Architecture

The application follows **Clean Architecture** with **Domain-Driven Design**, ensuring high maintainability, testability, and scalability.

**Architecture Layers:**
- **Domain Layer** – Core business logic and domain models
- **Application Layer** – Use cases and business workflows
- **Infrastructure Layer** – Database, email service, and external integrations
- **Presentation Layer** – REST APIs and Angular frontend

---

## 🧰 Tech Stack

### Backend
- **Java 25**
- **Spring Boot**
- **Spring Security (JWT)**
- **Hibernate / JPA**
- **PostgreSQL**
- **Java Mail Sender with mailtrap smtp**

### Frontend
- **Angular**
- **TypeScript**
- **HTML / CSS**

### Architecture & Design
- Domain-Driven Design (DDD)
- Clean Architecture
- RESTful API Design
- Role-Based Access Control (User / Admin)

---

## 🔐 Security
- Stateless authentication using **JWT**
- Role-based authorization for **Users** and **Admins**
- Secure API access and data isolation per user

---

## 📊 Key Highlights
- Designed to support **multiple medicines per user** with individual side effects mapping
- Scalable data model to handle long-term health tracking
- Clean separation of concerns for easier maintenance and future expansion
- Real-world healthcare-inspired domain modeling

---

## 🗄️ Database
- **PostgreSQL** used for reliable and structured data storage
- Proper relational mapping between:
  - Users
  - Medicines
  - Side Effects
  - Body Metrics
  - Reminders

---

## 🧪 Future Enhancements
- Push notifications (mobile support)
- Data visualization for health trends
- Doctor access module
- Integration with wearable health devices

---

## 🤝 Contribution
Contributions, suggestions, and feedback are welcome.  
Feel free to fork the repository and submit a pull request.

---

## 📄 License
This project is for educational and demonstration purposes.
