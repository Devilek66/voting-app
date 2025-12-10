package com.example.voting_app.exception;

public class VoterBlockedException extends RuntimeException {
    public VoterBlockedException(Long id) {
        super("Voter with id=" + id + " is blocked");
    }
}
