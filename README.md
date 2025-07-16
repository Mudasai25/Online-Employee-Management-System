# Online Employee Management System

[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen.svg)](https://example.com/build)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Code Style](https://img.shields.io/badge/Code%20Style-Google-blueviolet)](https://google.github.io/styleguide/)

## Project Description and Overview

The Online Employee Management System is a comprehensive web application designed to streamline and automate various HR processes within an organization. Built using Spring Boot, this system provides features for managing employee records, tracking attendance, managing leave requests, and generating reports. It aims to improve efficiency, reduce paperwork, and provide a centralized platform for all employee-related information.

## Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

*   **Java Development Kit (JDK):** Version 17 or higher.
*   **Maven:** Version 3.6.0 or higher.
*   **MySQL:** Version 8.0 or higher (or any compatible version).
*   **Docker:** If you intend to use Testcontainers for development.
*   **IntelliJ IDEA/Eclipse:** Recommended IDE for development

### Setup Instructions

1.  **Clone the repository:**

        *   Create a MySQL database named `employee_management`.
    *   Update the `src/main/resources/application.properties` file with your database credentials:

properties
        spring.datasource.url=jdbc:mysql://localhost:3306/employee_management?createDatabaseIfNotExist=true&serverTimezone=UTC
        spring.datasource.username=<YOUR_MYSQL_USERNAME>
        spring.datasource.password=<YOUR_MYSQL_PASSWORD>
        spring.jpa.hibernate.ddl-auto=update
            Open your web browser and navigate to `http://localhost:8080`.

## Core Functionalities

*   **Employee Management:** Add, update, and delete employee records.
*   **Attendance Tracking:** Record employee attendance and generate reports.
*   **Leave Management:** Submit, approve/reject leave requests.
*   **Reporting:** Generate various reports related to employees, attendance, and leave.
*   **User Authentication and Authorization:** Secure access to different parts of the application based on user roles.

## Technologies Used

*   **Spring Boot:** Framework for building the application.
*   **Maven:** Dependency management and build tool.
*   **JPA (Java Persistence API):** Data persistence layer.
*   **Spring Security:** Authentication and authorization.
*   **Spring Boot Actuator:** Monitoring and management endpoints.
*   **Testcontainers:** For integration testing with Docker.
*   **MySQL:** Relational database for storing data.

## API Documentation

json
>     [
>         {
>             "id": 1,
>             "firstName": "John",
>             "lastName": "Doe",
>             "email": "john.doe@example.com"
>         },
>         {
>             "id": 2,
>             "firstName": "Jane",
>             "lastName": "Smith",
>             "email": "jane.smith@example.com"
>         }
>     ]
>     *   `spring.datasource.url`: JDBC URL for the MySQL database.
*   `spring.datasource.username`: MySQL username.
*   `spring.datasource.password`: MySQL password.
*   `spring.jpa.hibernate.ddl-auto`: Hibernate DDL (Data Definition Language) auto-generation strategy (e.g., `update`, `create`, `create-drop`, `none`).  Use `update` for development, but consider using Flyway or Liquibase for production database migrations.
*   `server.port`: Port on which the application runs (default: 8080).
*   `spring.security.user.name`: Default username for accessing the application (if using Spring Security's default configuration).
*   `spring.security.user.password`: Default password for accessing the application.

## Usage Examples

> Example: Adding a new employee via the API using `curl`:
>
> 1.  **Fork the repository.**
2.  **Create a new branch** for your feature or bug fix.
3.  **Make your changes** and ensure they are well-tested.
4.  **Submit a pull request** with a clear description of your changes.

> Please ensure your code adheres to the project's coding style. We use the Google Java Style Guide.

## Testcontainers Support

This project uses [Testcontainers](https://www.testcontainers.org/) for integration testing during development. Testcontainers allows you to run Docker containers as part of your tests, providing a realistic environment for testing your application's interactions with external dependencies such as databases.

### Configuring Testcontainers with MySQL

1.  **Add Testcontainers dependencies:** Ensure the following dependencies are in your `pom.xml`:

xml
    <dependency>
        <groupId>org.testcontainers</groupId>
        <artifactId>mysql</artifactId>
        <version>1.19.6</version> <!-- Use the latest version -->
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-testcontainers</artifactId>
        <scope>test</scope>
    </dependency>
            @Container
        private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:latest");

3.  **Verify the Docker image tag:** The `MySQLContainer<>("mysql:latest")` uses the `latest` tag.  For production, it is highly recommended to use a specific, stable version tag (e.g., `mysql:8.0.36`).

### Maven Parent Overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM. While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent. To prevent this, the project POM contains empty overrides for these elements. If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
