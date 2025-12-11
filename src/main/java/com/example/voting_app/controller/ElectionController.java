package com.example.voting_app.controller;

import com.example.voting_app.controller.dto.ElectionCreateDto;
import com.example.voting_app.controller.dto.ElectionDto;
import com.example.voting_app.controller.dto.ElectionWithOptionsDto;
import com.example.voting_app.service.ElectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/elections")
public class ElectionController {

    private final ElectionService electionService;

    @PostMapping
    public ElectionWithOptionsDto create(@Valid @RequestBody ElectionCreateDto dto) {
        return electionService.createElection(dto);
    }

    @GetMapping("/{id}")
    public ElectionWithOptionsDto get(@PathVariable Long id) {
        return electionService.getElection(id);
    }

    @GetMapping
    public List<ElectionDto> get()
    {
        return electionService.getAllElections();
    }
}