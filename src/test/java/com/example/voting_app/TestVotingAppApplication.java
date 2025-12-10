package com.example.voting_app;

import org.springframework.boot.SpringApplication;

public class TestVotingAppApplication {

	public static void main(String[] args) {
		SpringApplication.from(VotingAppApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
