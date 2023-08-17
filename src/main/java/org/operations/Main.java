package org.operations;

public class Main {
    public static void main(String[] args) {
        DBOperations dbOperations = new DBOperations();
        dbOperations.doConnectDB();
        dbOperations.fetchMovieData();
        dbOperations.fetchGenreData();
        dbOperations.fetchMovieDataBasedOnImdb();
        dbOperations.fetchMovieBasedOnGenre();
        dbOperations.fetchMovieBasedOnDirectorName();
    }
}