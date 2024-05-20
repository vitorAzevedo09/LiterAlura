# Literalura Application

## Overview
The Literalura Application is a Spring Boot console application designed to interact with a database of books and authors. It allows users to perform various operations, such as searching for books by title, listing all searched books and authors, and filtering books and authors based on specific criteria.

## Features
- **Search by Title:** Allows the user to search for a book by its title.
- **List All Searched Books:** Displays all books that have been searched for.
- **List All Authors of Searched Books:** Displays all authors whose books have been searched for.
- **List Living Authors in a Given Year:** Lists all authors who were alive in a specified year.
- **List Books by Language:** Displays the number of books available in a specified language.

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- Docker
- An IDE or text editor (e.g., IntelliJ IDEA, Eclipse)

### Installation
1. Clone the repository:
   ```bash
   git clone git@github.com:vitorAzevedo09/LiterAlura.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Literalura
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running PostgreSQL with Docker
1. Pull the PostgreSQL Docker image:
   ```bash
   docker pull postgres
   ```
2. Run the PostgreSQL container:
   ```bash
   docker run --name literalura-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=books -p 5432:5432 -d postgres
   ```

### Configuring the Application
Update the `application.properties` file with the following configuration:
```properties
spring.application.name=literalura

# JPA hibernate
spring.datasource.url=jdbc:postgresql://localhost:5432/books
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.show-sql=true

## Hibernate Properties
# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update
```

### Running the Application
Run the application using Maven:
```bash
mvn spring-boot:run
```

## Usage
When you run the application, a menu will be displayed with several options:

```
__________BANCO DE LIVROS GUTENBERG__________
Select an option: 
1 - Search by Title
2 - List all Searched Books
3 - List all Authors of Searched Books
4 - List all Living Authors in a Given Year
5 - List Books by Language
0 - Exit

Enter your choice: 
```

### Menu Options

1. **Search by Title**
   - Prompts the user to enter a book title.
   - Displays details of the book if found, otherwise indicates that the book was not found.

2. **List All Searched Books**
   - Displays details of all books that have been searched for.

3. **List All Authors of Searched Books**
   - Displays details of all authors whose books have been searched for.

4. **List Living Authors in a Given Year**
   - Prompts the user to enter a year.
   - Displays details of all authors who were alive in the specified year.

5. **List Books by Language**
   - Prompts the user to choose between English and French.
   - Displays the number of books available in the selected language.

0. **Exit**
   - Exits the application.

## Example Usage

### Searching for a Book by Title
1. Select option `1`.
2. Enter the title of the book.
3. The application displays the details of the book if found.

### Listing All Authors in a Given Year
1. Select option `4`.
2. Enter the year.
3. The application displays the authors who were alive in the specified year.

## Code Structure

### Main Application
The main application class is `LiteraluraApplication`, which implements `CommandLineRunner` to execute the `displayMenu` method on startup.

### Services
The application uses `BookService` to interact with the database and perform operations such as searching for books and retrieving author information.

### Entities
The application uses the following entities:
- `Book`: Represents a book with attributes such as title, author name, language, and download count.
- `Author`: Represents an author with attributes such as name, birth year, and death year.

### Dependency Injection
The `BookService` is injected into the main application class using Spring's `@Autowired` annotation.

## Error Handling
The application handles exceptions that might occur during the execution of menu options and provides appropriate error messages to the user.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## License
This project is licensed under the MIT License.
