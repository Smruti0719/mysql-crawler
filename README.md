# 📘 MySQL Database Crawler – Spring Boot Project

This project is a **MySQL Database Crawler** built using **Java**, **Spring Boot**, and **JDBC**.  
It automatically connects to a MySQL database, scans all tables, reads metadata such as columns, primary keys, and foreign keys, and returns the information through REST APIs.

It also supports generating Java model classes dynamically from database tables.

---

## 🚀 Features

### ✅ Automatic Database Metadata Extraction
The crawler reads:
- Table names  
- Columns (name, type, size, nullable)  
- Primary keys  
- Foreign keys  

---

### ✅ REST API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/crawler/tables` | Returns all tables in the database |
| GET | `/api/crawler/table/{name}` | Returns metadata for a single table |
| GET | `/api/crawler/models` | Generates Java model classes for all tables |

---

### ✅ Java Model Generator
For each table, a Java class is generated with:
- Fields mapped from SQL types  
- Basic POJO structure  
- Optional Lombok support  

---

## 🛠 Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java 17** | Backend logic |
| **Spring Boot** | REST API framework |
| **MySQL** | Database |
| **JDBC** | Database connection |
| **org.json** | Parse config.json |
| **Maven** | Build automation |

---

## 📦 Project Structure

src/main/java/com/crawler/
├── config/ # Loads database config.json
├── controller/ # REST API endpoints
├── model/ # Metadata model classes
├── service/ # Core crawler + generator logic
└── JavaDbCrawlerApplication.java


---

## 🔧 Configuration File (config.json)

Create this file in:


Content:

```json
{
  "url": "jdbc:mysql://localhost:3306/javaCrawlerDb",
  "username": "root",
  "password": "your_password"
}
## ▶️ How to Run This Project

Follow these steps to download and run the MySQL Database Crawler on your system.

---

### 📥 1️⃣ Clone the Repository
Open a terminal and run:

```bash
git clone https://github.com/Smruti0719/mysql-crawler.git

🛠 2️⃣ Configure the Database

Update the config.json file with your MySQL username, password, and database name.

▶️ 3️⃣ Run the Application
Option A — Run using Maven
mvn spring-boot:run

Option B — Run from IDE

Open the project and run:

JavaDbCrawlerApplication.java

🌐 4️⃣ Test API Endpoints

✔ Get all tables

GET http://localhost:8080/api/crawler/tables


✔ Get metadata for one table

GET http://localhost:8080/api/crawler/table/student


✔ Generate Java models

GET http://localhost:8080/api/crawler/models

👩‍💻 Author

Smruti Sagarika
GitHub: https://github.com/Smruti0719
