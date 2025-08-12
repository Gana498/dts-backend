# DTS Backend - Team API Documentation

## Overview
The DTS Backend application provides a REST API for managing team member information. The application is built using Spring Boot 3.1.2 with Java 17+ and uses an H2 in-memory database for development.

## API Endpoints

### Get All Team Members
- **URL**: `GET /api/team`
- **Description**: Retrieves a list of all team members
- **Response Format**: JSON array of team objects

### Get Team Member by ID
- **URL**: `GET /api/team/{id}`
- **Description**: Retrieves a specific team member by their ID
- **Response Format**: JSON object for the team member

### Create Team Member
- **URL**: `POST /api/team`
- **Description**: Creates a new team member
- **Request Body**: JSON object with team member data

### Update Team Member
- **URL**: `PUT /api/team/{id}`
- **Description**: Updates an existing team member
- **Request Body**: JSON object with updated team member data

### Delete Team Member
- **URL**: `DELETE /api/team/{id}`
- **Description**: Deletes a team member by ID

### Search Team Members
- **URL**: `GET /api/team/search/position?position={position}`
- **Description**: Search team members by position
- **URL**: `GET /api/team/search/name?name={name}`
- **Description**: Search team members by name

## JSON Response Format
Each team member object contains the following fields:

```json
{
    "id": 1,
    "name": "John Smith",
    "position": "Senior Software Engineer",
    "description": "Experienced full-stack developer with expertise in Java, Spring Boot, and React. Passionate about clean code and modern development practices.",
    "avatar": "https://via.placeholder.com/150/0066CC/FFFFFF?text=JS",
    "email": "john.smith@dts.com",
    "linkedin": "https://linkedin.com/in/johnsmith"
}
```

## Field Descriptions
- `id`: Unique identifier (auto-generated)
- `name`: Team member's full name (required, max 100 chars)
- `position`: Job title/position (required, max 100 chars)
- `description`: Detailed description of the team member (optional, text)
- `avatar`: URL to profile image (optional, max 500 chars)
- `email`: Email address (optional, max 100 chars)
- `linkedin`: LinkedIn profile URL (optional, max 200 chars)

## Sample Data
The application comes pre-loaded with 5 sample team members for testing purposes.

## Database Access
- **H2 Console**: Available at `http://localhost:8080/h2-console`
- **Database URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (empty)

## Server Configuration
- **Port**: 8080
- **CORS**: Enabled for localhost:3000 and localhost:3001
- **Database**: H2 in-memory (data resets on restart)

## Testing the API
You can test the API using:
1. Browser: `http://localhost:8080/api/team`
2. curl: `curl http://localhost:8080/api/team`
3. Postman or similar REST client

## Build and Run
```bash
# Compile the project
mvnw.cmd compile

# Run tests
mvnw.cmd test

# Start the application
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`.
