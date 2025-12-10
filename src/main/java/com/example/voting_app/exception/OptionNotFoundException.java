package com.example.voting_app.exception;

public class OptionNotFoundException extends RuntimeException {
    public OptionNotFoundException(Long id) {
        super("Option with id=" + id + " not found");
    }
}
