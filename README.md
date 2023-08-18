# Assignment_8
## Database Operations in Java

This Java class, `DBOperations`, demonstrates various database operations using JDBC (Java Database Connectivity). The class connects to a MySQL database, performs queries, and retrieves data related to movies and genres.

## Prerequisites

Before using this code, ensure that you have the following:

- MySQL database server installed and running.
- Appropriate JDBC driver for MySQL (like `mysql-connector-java`) in your classpath.
- The necessary utility classes (`DirectorUtils`, `GenreUtils`, and `MovieUtils`) available.

## Class Structure

The `DBOperations` class contains methods for fetching movie and genre data from the database based on different criteria.

### `doConnectDB()`

This method establishes a connection to the MySQL database using the provided credentials.

### `fetchMovieData()`

Retrieves and displays information about movies along with their associated genres from the database.

### `fetchGenreData()`

Retrieves and displays information about genres from the database.

### `fetchMovieDataBasedOnImdb()`

Fetches movies with IMDb ratings greater than 7.2 and displays their titles and IMDb ratings.

### `fetchMovieBasedOnGenre()`

Retrieves and displays movies with a specific genre (e.g., 'action').

### `fetchMovieBasedOnDirectorName()`

Retrieves and displays movies directed by a specific director (e.g., Christopher Nolan), grouped by IMDb rating.

## Usage

1. Make sure you have the MySQL server up and running.

2. Update the `connectionStr`, `userName`, and `password` fields in the `doConnectDB()` method with your MySQL server credentials.

3. Ensure that the necessary utility classes (`DirectorUtils`, `GenreUtils`, and `MovieUtils`) are correctly implemented and available in the same package.

4. Execute the desired methods from the `DBOperations` class to perform specific database operations.
