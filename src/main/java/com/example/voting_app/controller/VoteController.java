package com.example.voting_app.controller;

import com.example.voting_app.controller.dto.VoteDto;
import com.example.voting_app.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> castVote(@RequestBody VoteDto request) {
        voteService.castVote(request.voterId(), request.electionId(), request.optionId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/results/{electionId}")
    public ResponseEntity<Map<Long, Long>> getElectionResults(@PathVariable Long electionId) {
        Map<Long, Long> results = voteService.countVotesByElection(electionId);
        return ResponseEntity.ok(results);
    }
}
