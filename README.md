# Project Management System API

A REST API for managing projects, employees, and tasks — built to practice a
clean layered Spring Boot architecture with DTOs and mappers.

## Entities

- **Project** — name, start/end dates, status; has many tasks, many-to-many with employees.
- **Employee** — name, email, department; assigned to projects and tasks.
- **Task** — title, description, priority, status, deadline; belongs to one project and one employee.

## Architecture

```
Controller → Service (interface + Impl) → Repository → Entity
                     ↕
                   Mapper ↔ DTO
```

Each entity has its own DTO, mapper (MapStruct), repository, and a
service interface with an implementation, so controllers never expose JPA
entities directly.

## API

| Resource | Endpoints |
|---|---|
| `/project` | `GET`, `GET /{id}`, `POST /create`, `PUT /{id}/update`, `DELETE /{id}/delete`, `PUT /{projectId}/addTask/{taskId}` |
| `/employee` | `GET`, `GET /{id}`, `POST /create`, `PUT /{id}/update`, `DELETE /{id}/delete`, `PUT /{employeeId}/assignProject/{projectId}` |
| `/task` | `GET`, `GET /{id}`, `POST /create`, `PUT /{id}/update`, `DELETE /{id}/delete`, `PUT /{taskId}/assign/{employeeId}` |

## Stack

Java 21, Spring Boot 3.5, Spring Data JPA, MapStruct, Lombok, PostgreSQL, Gradle.

## Running locally

1. Create a PostgreSQL database named `project` (or set `DB_NAME`).
2. Set `DB_USERNAME` / `DB_PASSWORD` if they differ from the `postgres` default.
3. Run:
   ```bash
   ./gradlew bootRun
   ```
   The API starts on `http://localhost:8080`.
