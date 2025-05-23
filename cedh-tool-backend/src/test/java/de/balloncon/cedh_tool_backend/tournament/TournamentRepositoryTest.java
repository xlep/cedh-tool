package de.balloncon.cedh_tool_backend.tournament;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentRepositoryTest {

  @Autowired
  private TournamentRepository tournamentRepository;

  @Test
  void findMaxRound() {
    // check for empty test tournament for round creation
    assertThat(tournamentRepository.findMaxRound(
        UUID.fromString("58e3de5f-1358-48e6-9268-4b5e451a495a")))
        .isEqualTo(null);

    // check for test tournament for scoring
    assertThat(tournamentRepository.findMaxRound(
        UUID.fromString("7addec25-9af0-452f-9e01-6481892e545d")))
        .isEqualTo(5);
  }
}