package com.example.voting_app.exception;

public class VoterAlreadyExistsException extends RuntimeException {
    public VoterAlreadyExistsException(String externalId) {
        super("Voter with externalId " + externalId + " already exists");
    }
}
