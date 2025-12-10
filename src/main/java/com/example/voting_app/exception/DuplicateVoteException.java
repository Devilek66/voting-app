package com.example.voting_app.exception;

public class DuplicateVoteException extends RuntimeException {
    public DuplicateVoteException(Long voterId, Long electionId) {
        super("Voter " + voterId + " has already voted in election " + electionId);
    }
}
