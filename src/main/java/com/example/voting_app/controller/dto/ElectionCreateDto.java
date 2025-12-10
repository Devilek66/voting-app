package com.example.voting_app.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ElectionCreateDto(
        @NotBlank(message = "Election name cannot be empty")
        String name,
        @NotEmpty(message = "Election must have at least one option")
        @Size(min = 1, message = "Election must contain at least 1 option")
        List<@NotBlank(message = "Option label cannot be empty") String> options
) {}
