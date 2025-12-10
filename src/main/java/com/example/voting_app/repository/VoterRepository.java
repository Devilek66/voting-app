package com.example.voting_app.repository;

import com.example.voting_app.repository.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByExternalId(String externalId);
    boolean existsByExternalId(String externalId);
}
