# Ticket Master Application

## Description

Ticket Master is a backend application designed to manage and book event tickets. Built using the Spring Boot framework, the application integrates PostgreSQL for data storage and enables caching using Redis.

## Technologies Used

- **Framework:** Spring Boot
- **Database:** PostgreSQL
- **Caching:** Redis
- **Containerization:** Docker

## Features

- Event management
- Ticket booking
- User authentication and authorization
- Data caching for improved performance

## Getting Started

### Prerequisites

- Java 21
- Docker (for running PostgreSQL and Redis containers)
- Maven

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/KiranSequeira14/ticket-master.git
   cd ticket-master
   ```

2. **Build the project:**
    ```bash
   mvn clean install
   ```

3. **Set up PostgreSQL and Redis containers using Docker:**
    ```
    #Run PostgreSQL container
    docker run --name postgres-db -e POSTGRES_DB=ticketmaster -e POSTGRES_USER=youruser -e POSTGRES_PASSWORD=yourpassword -p 5432:5432 -d postgres
   
   # Run Redis container
    docker run --name redis-cache -p 6379:6379 -d redis
   ```

4. **Configure application properties:**
    ```
   # PostgreSQL Configuration
    spring.datasource.url=jdbc:postgresql://localhost:5432/ticketmaster
    spring.datasource.username=youruser
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    
    # Redis Configuration
    spring.redis.host=localhost
    spring.redis.port=6379
    spring.redis.username=youruser
    spring.redis.password=yourpassword
   ```
   
5. **Running the Application:**
    ```bash
    mvn spring-boot:run
   ```

