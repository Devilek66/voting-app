package com.example.voting_app.repository;

import com.example.voting_app.repository.entity.Election;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ElectionRepository extends JpaRepository<Election, Long> {

    @EntityGraph(attributePaths = "options")
    Optional<Election> findWithOptionsById(Long id);
}
