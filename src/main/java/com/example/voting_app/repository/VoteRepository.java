package com.example.voting_app.repository;

import com.example.voting_app.repository.entity.Election;
import com.example.voting_app.repository.entity.Option;
import com.example.voting_app.repository.entity.Vote;
import com.example.voting_app.repository.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByVoterAndElection(Voter voter, Election election);
    long countByElectionAndOption(Election election, Option option);
}
