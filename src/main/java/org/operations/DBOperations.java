package org.operations;

import org.utils.DirectorUtils;
import org.utils.GenreUtils;
import org.utils.MovieUtils;

import java.sql.*;

public class DBOperations {
    Connection conn;
    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/" + MovieUtils.DB_NAME;
        String userName = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is seccussful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchMovieData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + MovieUtils.TABLE_NAME
                    + " NATURAL JOIN " + GenreUtils.TABLE_NAME);
            System.out.println("\nMovie Table Info:");
            while (rs.next()) {
                String title = rs.getString(MovieUtils.COLUMN_TITLE);
                String genreName = rs.getString(GenreUtils.COLUMN_GENRE_NAME);
                float imdb = rs.getFloat(MovieUtils.COLUMN_IMDB);
                System.out.println("Title: " + title + ", GenreName: " + genreName + ", imdb: " + imdb);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchGenreData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + GenreUtils.TABLE_NAME);
            System.out.println("\nGenre Table Info:");
            while (rs.next()) {
                int genreId = rs.getInt(GenreUtils.COLUMN_GENRE_ID);
                String genreName = rs.getString(GenreUtils.COLUMN_GENRE_NAME);
                System.out.println("GenreId: " + genreId + ", Name: " + genreName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchMovieDataBasedOnImdb() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + MovieUtils.TABLE_NAME
                    + " NATURAL JOIN " + GenreUtils.TABLE_NAME + " where "
                    + MovieUtils.COLUMN_IMDB + ">" + 7.2
                    + "GROUP BY " + MovieUtils.COLUMN_IMDB);
            System.out.println("\nMovie Lists rating greater than 7.2:");
            while (rs.next()) {
                String title = rs.getString(MovieUtils.COLUMN_TITLE);
                float imdb = rs.getFloat(MovieUtils.COLUMN_IMDB);
                System.out.println("Title: " + title + ", imdb: " + imdb);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchMovieBasedOnGenre() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + MovieUtils.TABLE_NAME + " WHERE "
                    + MovieUtils.COLUMN_GENRE_ID + " IN (SELECT " + GenreUtils.COLUMN_GENRE_ID
                    + " FROM " + GenreUtils.TABLE_NAME + " WHERE " + GenreUtils.COLUMN_GENRE_NAME
                    + " = 'action')");
            System.out.println("\nMovies with Genre 'action':");
            while (rs.next()) {
                String title = rs.getString(MovieUtils.COLUMN_TITLE);
                System.out.println("Title: " + title);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchMovieBasedOnDirectorName() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + MovieUtils.COLUMN_TITLE
                    + " FROM " + MovieUtils.TABLE_NAME
                    + " INNER JOIN " + DirectorUtils.TABLE_NAME
                    + " ON " + MovieUtils.TABLE_NAME + "." + MovieUtils.COLUMN_DIRECTOR_ID
                    + " = " + DirectorUtils.TABLE_NAME + "." + DirectorUtils.COLUMN_DIRECTOR_ID
                    + " WHERE " + DirectorUtils.COLUMN_DIRECTOR_NAME + " = 'Christopher Nolan'"
                    + " GROUP BY " + MovieUtils.COLUMN_IMDB);
            System.out.println("\nMovies of Christopher Nolan grouped by IMDB rating:");
            while (rs.next()) {
                String title = rs.getString(MovieUtils.COLUMN_TITLE);
                System.out.println("Title: " + title);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}