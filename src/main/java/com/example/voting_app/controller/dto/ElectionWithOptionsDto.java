package com.example.voting_app.controller.dto;

import java.util.List;

public record ElectionWithOptionsDto(
        Long id,
        String name,
        List<OptionDto> options
) { }
