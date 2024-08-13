package com.keyin.Sprint1_API.Movie;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long movie_id) {
        super("Movie not found with id: " + movie_id);
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
