package com.example.blockbusterapiv3.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
        super("Movie with ID " + id + " not found.");
    }
}
