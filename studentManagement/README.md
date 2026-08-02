# Student Management System

## Overview

Spring Boot REST API for managing students and courses with JWT
authentication.

## Tech Stack

-   Java 17
-   Spring Boot
-   Spring Security
-   JWT
-   Spring Data JPA / Hibernate
-   H2 Database
-   Maven
-   Swagger
-   JUnit 5 & Mockito

## Features

-   Admin Login (Generate Token)
-   Student CRUD
-   Course CRUD
-   Assign/Remove Course
-   Search Student by Name
-   Student Validation (studentCode + dateOfBirth)

## Run

``` bash
mvn clean install
mvn spring-boot:run
```

## URLs

-   Swagger: http://localhost:8080/swagger-ui/index.html
-   H2 Console: http://localhost:8080/h2-console

## Default Admin

Username: admin Password: admin123

## Authentication

POST /admin/login (Generate Token)

Use: Authorization: Bearer `<JWT_TOKEN>`{=html}

## APIs

-   POST /students
-   GET /students
-   GET /students/{id}
-   PUT /students/{id}
-   DELETE /students/{id}
-   GET /students/search?name=John
-   POST /students/validate
-   POST /courses
-   GET /courses
-   GET /courses/{id}
-   DELETE /courses/{id}
-   POST /students/{studentId}/courses/{courseId}
-   DELETE /students/{studentId}/courses/{courseId}

## Tests

mvn test
