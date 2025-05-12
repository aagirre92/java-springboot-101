# Spring Boot Demo REST API

A simple REST API built with Spring Boot that manages person records with basic CRUD operations.

## Technologies Used

- Java 21
- Spring Boot
- Gradle
- Jackson (JSON processing)
- OpenAPI 3.1.0


## Features

- RESTful API endpoints for managing person records
- Persistent storage using JSON file
- OpenAPI documentation
- CRUD operations for person entities

## API Endpoints

### Get All People
- **GET** `/people`
- Returns a list of all people
- Response: Array of Person objects

### Get Person by ID
- **GET** `/people/{id}`
- Returns a specific person by their ID
- Response: Person object or 404 if not found

### Add New Person
- **POST** `/people`
- Creates a new person record
- Request Body: Person object
- Response: Success message or conflict error if ID exists

### Delete Person
- **DELETE** `/people/{id}`
- Deletes a person by their ID
- Response: Success message or 404 if not found

## Data Model

### Person
```

{ "id": integer,
  "name": string }

```

## Setup and Running

1. Ensure you have Java 21 installed
2. Clone the repository
3. Run the application using Gradle:
   ```bash
   ./gradlew bootRun
   ```
4. The API will be available at `http://localhost:8080`

## Development

The project uses Gradle as the build tool. Common commands:


## Data Storage

The application stores data in a JSON file (`people.json`) located in the `src/main/resources` directory. The file is automatically created if it doesn't exist.

## API Documentation

The API is documented using OpenAPI 3.1.0 specification. You can find the API specification in the `demo-openapi.yaml` file.

## License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.