package com.example.voting_app.exception;

public class ElectionNotFoundException extends RuntimeException {
    public ElectionNotFoundException(Long id) {
        super("Election with id=" + id + " not found");
    }
}
