package com.example.voting_app.controller;

import com.example.voting_app.controller.dto.VoterCreateDto;
import com.example.voting_app.controller.dto.VoterDto;
import com.example.voting_app.service.VoterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voters")
@RequiredArgsConstructor
public class VoterController {
    private final VoterService voterService;

    @PostMapping
    public VoterDto create(@Valid @RequestBody VoterCreateDto dto) {
        return voterService.create(dto);
    }

    @PatchMapping("/{id}/block")
    public ResponseEntity<Void> block(@PathVariable Long id, @RequestParam boolean block) {
        voterService.setBlocked(id, block);
        return ResponseEntity.noContent().build();
    }
}
