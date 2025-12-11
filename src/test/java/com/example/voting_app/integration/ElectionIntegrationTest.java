package com.example.voting_app.integration;

import com.example.voting_app.RestTemplateConfig;
import com.example.voting_app.TestcontainersConfiguration;
import com.example.voting_app.repository.ElectionRepository;
import com.example.voting_app.repository.entity.Election;
import com.example.voting_app.repository.entity.Option;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import({RestTemplateConfig.class, TestcontainersConfiguration.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ElectionIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ElectionRepository electionRepository;

    @BeforeEach
    void setup() {
        electionRepository.deleteAll();

        Election e = new Election();
        e.setName("My Election");

        Option o1 = new Option();
        o1.setLabel("A");
        o1.setElection(e);

        Option o2 = new Option();
        o2.setLabel("B");
        o2.setElection(e);

        e.setOptions(List.of(o1, o2));

        electionRepository.save(e);
    }

    @Test
    void shouldReturnElectionAsJson() throws Exception {
        // when
        String response = restTemplate.getForObject("/elections/1", String.class);

        // then - expected JSON
        String expected = """
        {
          "id": 1,
          "name": "My Election",
          "options": [
            {"id": 1, "label": "A"},
            {"id": 2, "label": "B"}
          ]
        }
        """;

        JSONAssert.assertEquals(expected, response, false);
    }
}
