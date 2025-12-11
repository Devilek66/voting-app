package com.example.voting_app.service;

import com.example.voting_app.controller.dto.ElectionCreateDto;
import com.example.voting_app.controller.dto.ElectionDto;
import com.example.voting_app.controller.dto.ElectionWithOptionsDto;
import com.example.voting_app.exception.ElectionNotFoundException;
import com.example.voting_app.repository.ElectionRepository;
import com.example.voting_app.repository.entity.Election;
import com.example.voting_app.repository.entity.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ElectionServiceTest {

    private ElectionRepository electionRepository;
    private ElectionService electionService;

    @BeforeEach
    void setUp() {
        electionRepository = mock(ElectionRepository.class);
        electionService = new ElectionService(electionRepository);
    }

    @Test
    void shouldCreateElection() {
        // given
        ElectionCreateDto dto = new ElectionCreateDto("Test election", List.of("A", "B"));

        ArgumentCaptor<Election> electionCaptor = ArgumentCaptor.forClass(Election.class);

        Election saved = new Election();
        saved.setId(10L);
        saved.setName("Test election");

        Option o1 = new Option();
        o1.setId(1L);
        o1.setLabel("A");

        Option o2 = new Option();
        o2.setId(2L);
        o2.setLabel("B");

        saved.setOptions(List.of(o1, o2));

        when(electionRepository.save(any(Election.class))).thenReturn(saved);

        // when
        ElectionWithOptionsDto result = electionService.createElection(dto);

        // then
        verify(electionRepository).save(electionCaptor.capture());
        Election passedToRepo = electionCaptor.getValue();

        assertThat(passedToRepo.getName()).isEqualTo("Test election");
        assertThat(passedToRepo.getOptions()).hasSize(2);
        assertThat(passedToRepo.getOptions().get(0).getLabel()).isEqualTo("A");
        assertThat(passedToRepo.getOptions().get(1).getLabel()).isEqualTo("B");

        assertThat(result.id()).isEqualTo(10L);
        assertThat(result.name()).isEqualTo("Test election");
        assertThat(result.options()).hasSize(2);
    }

    @Test
    void shouldReturnAllElections() {
        // given
        Election e1 = new Election();
        e1.setId(1L);
        e1.setName("E1");

        Election e2 = new Election();
        e2.setId(2L);
        e2.setName("E2");

        when(electionRepository.findAll()).thenReturn(List.of(e1, e2));

        // when
        List<ElectionDto> result = electionService.getAllElections();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).name()).isEqualTo("E1");
        assertThat(result.get(1).name()).isEqualTo("E2");
    }

    @Test
    void shouldReturnElectionById() {
        // given
        Election election = new Election();
        election.setId(1L);
        election.setName("Test1");

        Option o1 = new Option();
        o1.setId(5L);
        o1.setLabel("Yes");
        Option o2 = new Option();
        o2.setId(6L);
        o2.setLabel("No");
        election.setOptions(List.of(o1,o2));

        when(electionRepository.findWithOptionsById(1L)).thenReturn(Optional.of(election));

        // when
        ElectionWithOptionsDto dto = electionService.getElection(1L);

        // then
        assertThat(dto.id()).isEqualTo(1L);
        assertThat(dto.name()).isEqualTo("Test1");
        assertThat(dto.options()).hasSize(2);
        assertThat(dto.options().get(0).label()).isEqualTo("Yes");
        assertThat(dto.options().get(1).label()).isEqualTo("No");
    }

    @Test
    void shouldThrowWhenElectionNotFound() {
        // given
        when(electionRepository.findWithOptionsById(123L)).thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> electionService.getElection(123L))
                .isInstanceOf(ElectionNotFoundException.class)
                .hasMessageContaining("123");
    }
}