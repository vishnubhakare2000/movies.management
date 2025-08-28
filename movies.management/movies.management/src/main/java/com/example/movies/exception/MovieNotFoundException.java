package com.example.movies.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String id) {
        super("Movie not found with id: " + id);
    }
}
