# DTS Backend - Team Management API

A modern Spring Boot REST API for managing team member information, built with Java 17+ and Spring Boot 3.1.2.

## 🚀 Features

- **REST API** for team member CRUD operations
- **H2 In-Memory Database** for development
- **Spring Data JPA** for data persistence
- **CORS Support** for frontend integration
- **Comprehensive API Documentation**
- **Pre-loaded Sample Data** for testing

## 🛠️ Technology Stack

- **Java 17+**
- **Spring Boot 3.1.2**
- **Spring Data JPA**
- **H2 Database**
- **Maven** for build management
- **JUnit 5** for testing

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use included Maven wrapper)

## 🏃‍♂️ Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/Gana498/dts-backend.git
cd dts-backend
```

### 2. Build the Project
```bash
# Using Maven wrapper (recommended)
./mvnw.cmd clean compile

# Or using installed Maven
mvn clean compile
```

### 3. Run Tests
```bash
./mvnw.cmd test
```

### 4. Start the Application
```bash
./mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/team` | Get all team members |
| GET | `/api/team/{id}` | Get team member by ID |
| POST | `/api/team` | Create new team member |
| PUT | `/api/team/{id}` | Update team member |
| DELETE | `/api/team/{id}` | Delete team member |
| GET | `/api/team/search/position?position={position}` | Search by position |
| GET | `/api/team/search/name?name={name}` | Search by name |

### Team Member JSON Format

```json
{
    "id": 1,
    "name": "DHARMA TEJA NERALLA",
    "position": "CEO & Founder",
    "description": "Experienced full-stack developer with expertise in Python, Django, and React. Passionate about clean code and modern development practices.",
    "avatar": "https://via.placeholder.com/150/0066CC/FFFFFF?text=DT",
    "email": "dharmateja@dts.com",
    "linkedin": "https://www.linkedin.com/in/dharma-teja-neralla-490072255"
}
```

### Field Descriptions

- `id`: Unique identifier (auto-generated)
- `name`: Team member's full name (required, max 100 chars)
- `position`: Job title/position (required, max 100 chars)
- `description`: Detailed description (optional, text)
- `avatar`: Profile image URL (optional, max 500 chars)
- `email`: Email address (optional, max 100 chars)
- `linkedin`: LinkedIn profile URL (optional, max 200 chars)

## 🗄️ Database

### H2 Console Access
- **URL**: `http://localhost:8080/h2-console`
- **Database URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (empty)

### Sample Data
The application comes pre-loaded with sample team member data for testing.

## 🧪 Testing the API

### Using curl
```bash
# Get all team members
curl http://localhost:8080/api/team

# Get specific team member
curl http://localhost:8080/api/team/1

# Create new team member
curl -X POST http://localhost:8080/api/team \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "position": "Developer",
    "description": "Backend developer"
  }'
```

### Using Browser
Simply visit: `http://localhost:8080/api/team`

## ⚙️ Configuration

### Application Properties
Key configuration options in `src/main/resources/application.properties`:

```properties
# Server configuration
server.port=8080

# Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true

# JPA configuration
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# CORS configuration
spring.web.cors.allowed-origins=http://localhost:3000,http://localhost:3001
```

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/dts/dtsbackend/
│   │       ├── DtsBackendApplication.java
│   │       ├── config/
│   │       │   └── DataInitializer.java
│   │       ├── controller/
│   │       │   └── TeamController.java
│   │       ├── entity/
│   │       │   └── Team.java
│   │       ├── repository/
│   │       │   └── TeamRepository.java
│   │       └── service/
│   │           └── TeamService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/dts/dtsbackend/
            └── AppTest.java
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👤 Author

**Dharma Teja Neralla**
- LinkedIn: [dharma-teja-neralla](https://www.linkedin.com/in/dharma-teja-neralla-490072255)
- GitHub: [Gana498](https://github.com/Gana498)

## 🐛 Issues

If you encounter any issues, please file them on the [GitHub Issues](https://github.com/Gana498/dts-backend/issues) page.

---

⭐ **Star this repository if you find it helpful!**
