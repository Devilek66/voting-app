package com.example.voting_app.exception;

public class OptionDoesNotBelongToElectionException extends RuntimeException {
    public OptionDoesNotBelongToElectionException(Long electionId, Long optionId) {

        super("Option " + optionId + "does not belong to election " + electionId);
    }
}
