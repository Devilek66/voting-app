package com.example.voting_app.controller.dto;

public record VoteDto(
        Long voterId,
        Long electionId,
        Long optionId
) {}
