package com.example.voting_app.service;

import com.example.voting_app.controller.dto.ElectionCreateDto;
import com.example.voting_app.controller.dto.ElectionDto;
import com.example.voting_app.controller.dto.ElectionWithOptionsDto;
import com.example.voting_app.controller.dto.OptionDto;
import com.example.voting_app.exception.ElectionNotFoundException;
import com.example.voting_app.repository.ElectionRepository;
import com.example.voting_app.repository.entity.Election;
import com.example.voting_app.repository.entity.Option;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionRepository electionRepository;

    @Transactional
    public ElectionWithOptionsDto createElection(ElectionCreateDto dto) {
        Election election = new Election();
        election.setName(dto.name());

        List<Option> optionEntities = dto.options().stream()
                .map(label -> {
                    Option option = new Option();
                    option.setLabel(label);
                    option.setElection(election);
                    return option;
                })
                .toList();

        election.setOptions(optionEntities);

        Election saved = electionRepository.save(election);

        return mapToDtoWithOptions(saved);
    }

    @Transactional
    public List<ElectionDto> getAllElections() {
        return electionRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    @Transactional
    public ElectionWithOptionsDto getElection(Long id) {
        Election election = electionRepository.findWithOptionsById(id)
                .orElseThrow(() -> new ElectionNotFoundException(id));

        return mapToDtoWithOptions(election);
    }

    private ElectionWithOptionsDto mapToDtoWithOptions(Election election) {
        return new ElectionWithOptionsDto(
                election.getId(),
                election.getName(),
                election.getOptions()
                        .stream()
                        .map(o -> new OptionDto(o.getId(), o.getLabel()))
                        .toList()
        );
    }

    private ElectionDto mapToDto(Election election) {
        return new ElectionDto(
                election.getId(),
                election.getName()
        );
    }
}