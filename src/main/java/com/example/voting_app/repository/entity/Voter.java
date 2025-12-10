package com.example.voting_app.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "voter")
@Getter
@Setter
@NoArgsConstructor
public class Voter {

    public Voter(String externalId)
    {
        this.externalId = externalId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String externalId;


    @Column(nullable = false)
    private boolean blocked = false;
}
