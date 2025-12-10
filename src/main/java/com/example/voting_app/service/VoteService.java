package com.example.voting_app.service;

import com.example.voting_app.exception.*;
import com.example.voting_app.repository.ElectionRepository;
import com.example.voting_app.repository.OptionRepository;
import com.example.voting_app.repository.VoteRepository;
import com.example.voting_app.repository.VoterRepository;
import com.example.voting_app.repository.entity.Election;
import com.example.voting_app.repository.entity.Option;
import com.example.voting_app.repository.entity.Vote;
import com.example.voting_app.repository.entity.Voter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoterRepository voterRepository;
    private final ElectionRepository electionRepository;
    private final OptionRepository optionRepository;


    @Transactional
    public Vote castVote(Long voterId, Long electionId, Long optionId) {
        Voter voter = voterRepository.findById(voterId)
                .orElseThrow(() -> new VoterNotFoundException(voterId));
        if (voter.isBlocked()) throw new VoterBlockedException(voterId);

        Election election = electionRepository.findById(electionId)
                .orElseThrow(() -> new ElectionNotFoundException(electionId));

        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new OptionNotFoundException(optionId));

        if (!option.getElection().getId().equals(election.getId()))
            throw new OptionDoesNotBelongToElectionException(electionId, optionId);

        if (voteRepository.existsByVoterAndElection(voter, election))
            throw new DuplicateVoteException(voterId, electionId);

        Vote vote = new Vote();
        vote.setVoter(voter);
        vote.setElection(election);
        vote.setOption(option);
        try {
            return voteRepository.save(vote);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateVoteException(voterId, electionId);
        }
    }

    @Transactional
    public Map<Long, Long> countVotesByElection(Long electionId) {
        Election election = electionRepository.findWithOptionsById(electionId)
                .orElseThrow(() -> new ElectionNotFoundException(electionId));

        return election.getOptions().stream()
                .collect(Collectors.toMap(
                        Option::getId,
                        option -> voteRepository.countByElectionAndOption(election, option)
                ));
    }
}