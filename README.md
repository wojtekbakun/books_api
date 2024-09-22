# Book Management API

This project is a **Spring Boot REST API** that manages information about books. The API provides functionality to store, filter, and rate books. 

## Features

1. **Book Information**: The API manages the following attributes of books:
   - Title
   - Author
   - Year of Publication
   - Rating (1 to 5 stars)
   - Is PDF

2. **Filters**: Users can filter books based on:
   - Title
   - Author
   - Year of Publication
   - Rating
   - IsPdf

3. **Rating System**: Users can rate books on a scale of 1 to 5 stars.

4. **Unit & Integration Tests**: The application includes unit and integration tests to ensure correctness and stability.

5. **Containerized Application**: The API can be containerized using Docker for easy deployment.

## Technology Stack

- **Backend**: Java with Spring Boot
- **Database**: PostgreSQL
- **Testing**: Mockito
- **Containerization**: Docker

## Getting Started

### Prerequisites

To run this application, ensure that you have the following tools installed:
- Java 17+
- Docker
- PostgreSQL

### Running the Application

1. **Clone the Repository**:
    ```bash
    git clone https...
    cd books
    ```

2. **Build the Application**:
    ```bash
    ./mvnw clean package
    ```

3. **Set up PostgreSQL**:
    - Install PostgreSQL and create a new database.
    - Update the `application.properties` file with your PostgreSQL credentials.
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    ```

4. **Run the Application**:
    ```bash
    ./mvnw spring-boot:run
    ```

5. **Run in Docker**:
   - Build the Docker image:
     ```bash
     docker build -t book-api .
     ```
   - Run the container:
     ```bash
     docker run -d -p 8080:8080 --name book-api book-api
     ```

### API Endpoints

- **GET** `/`: Hello message
- **GET** `/books`: Retrieve all books.
- **GET** `/books/{title}`: Retrieve specific book details.
- **GET** `/books/author/{author}`: Filter by author.
- **GET** `/books/ispdf/{ispdf}`: Filter by isPdf
- **GET** `/books/title/{title}`: Filter by title
- **GET** `/books/year/{year}`: Filter by year
  
- **POST** `/books`: Add a new book to the system.
  
- **PUT** `/books/{title}/rating`: Rate a book.

### Sample Mock Data

Mock data for books is currently populated in database.
