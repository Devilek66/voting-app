package com.example.voting_app.exception;

public class VoterNotFoundException extends RuntimeException {
    public VoterNotFoundException(Long id)
    {
        super("Voter with id=" + id + " not found");
    }
}
