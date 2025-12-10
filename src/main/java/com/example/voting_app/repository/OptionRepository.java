package com.example.voting_app.repository;

import com.example.voting_app.repository.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
