package com.example.voting_app.service;

import com.example.voting_app.controller.dto.VoterCreateDto;
import com.example.voting_app.controller.dto.VoterDto;
import com.example.voting_app.exception.VoterAlreadyExistsException;
import com.example.voting_app.exception.VoterNotFoundException;
import com.example.voting_app.repository.VoterRepository;
import com.example.voting_app.repository.entity.Voter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoterService {
    private final VoterRepository voterRepository;

    public VoterDto create(@Valid VoterCreateDto dto) {

        if (voterRepository.existsByExternalId(dto.externalId())) {
            throw new VoterAlreadyExistsException(dto.externalId());
        }

        Voter voter = new Voter(dto.externalId());

        voterRepository.save(voter);

        return mapToDto(voter);
    }

    public void setBlocked(Long id, boolean block) {
        Voter voter = voterRepository.findById(id)
                .orElseThrow(() -> new VoterNotFoundException(id));

        voter.setBlocked(block);
        voterRepository.save(voter);
    }

    private VoterDto mapToDto(Voter voter) {
        return new VoterDto(voter.getId(), voter.getExternalId(), voter.isBlocked());
    }
}
