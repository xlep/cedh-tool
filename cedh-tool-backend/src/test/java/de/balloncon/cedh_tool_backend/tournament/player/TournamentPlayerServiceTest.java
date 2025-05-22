package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerRepository;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatRepository;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentRepository;
import de.balloncon.cedh_tool_backend.util.TestDataGenerator;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentPlayerServiceTest {

  @Autowired private PlayerRepository playerRepository;
  @Autowired private PodRepository podRepository;
  @Autowired private SeatRepository seatRepository;
  @Autowired private TournamentPlayerRepository tournamentPlayerRepository;
  @Autowired private TournamentRepository tournamentRepository;

  @Autowired private TournamentPlayerService tournamentPlayerService;

  private static Tournament tournament;
  private static Pod pod;


  void setupTournamentWithOnePod() {
    tournament = TestDataGenerator.generateTournament();
    tournamentRepository.save(tournament);

    pod = TestDataGenerator.generatePod(tournament);
    pod.setType(PodType.SWISS);
    podRepository.save(pod);


    for (int i = 1; i <= 4; i++) {
      Player player = TestDataGenerator.generatePlayer("Player" + i);
      playerRepository.save(player);

      Seat seat = TestDataGenerator.generateSeatWithoutResult(pod, player, i);
      seatRepository.save(seat);

      TournamentPlayer tournamentPlayer = TestDataGenerator.generateTournamentPlayer(tournament, player);
      tournamentPlayerRepository.save(tournamentPlayer);
    }
  }

  @Test
  void testSeat1Wins() {
    setupTournamentWithOnePod();
    setupWinner(1);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("1187.60000")),
                    new Tuple("Player2", new BigDecimal("926.50000")),
                    new Tuple("Player3", new BigDecimal("936.30000")),
                    new Tuple("Player4", new BigDecimal("949.60000"))
            );
  }

  @Test
  void testSeat2Wins() {
    setupTournamentWithOnePod();
    setupWinner(2);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("907.60000")),
                    new Tuple("Player2", new BigDecimal("1206.50000")),
                    new Tuple("Player3", new BigDecimal("936.30000")),
                    new Tuple("Player4", new BigDecimal("949.60000"))
            );
  }

  @Test
  void testSeat3Wins() {
    setupTournamentWithOnePod();
    setupWinner(3);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(),1);

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("907.60000")),
                    new Tuple("Player2", new BigDecimal("926.50000")),
                    new Tuple("Player3", new BigDecimal("1216.30000")),
                    new Tuple("Player4", new BigDecimal("949.60000"))
            );
  }

  @Test
  void testSeat4Wins() {
    setupTournamentWithOnePod();
    setupWinner(4);

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("907.60000")),
                    new Tuple("Player2", new BigDecimal("926.50000")),
                    new Tuple("Player3", new BigDecimal("936.30000")),
                    new Tuple("Player4", new BigDecimal("1229.60000"))
            );
  }

  @Test
  void testDraw() {
    setupTournamentWithOnePod();
    setupDraw();

    // -- end test setup

    // calculate scores
    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService
            .calculatePlayerScoresAfterSwissRounds(tournament.getId(), 1);

    // check points
    assertThat(tournamentPlayers)
            .hasSize(4)
            .extracting(tp -> tp.getPlayer().getNickname(),
                    TournamentPlayer::getScore)
            .containsExactlyInAnyOrder(
                    new Tuple("Player1", new BigDecimal("977.60000")),
                    new Tuple("Player2", new BigDecimal("996.50000")),
                    new Tuple("Player3", new BigDecimal("1006.30000")),
                    new Tuple("Player4", new BigDecimal("1019.60000"))
            );
  }


  @Test
  void testFullTournament() {
    UUID tournamentId = UUID.fromString("7addec25-9af0-452f-9e01-6481892e545d");

    List<TournamentPlayer> tournamentPlayers = tournamentPlayerService.calculatePlayerScoresAfterSwissRounds(tournamentId, 5);

    HashMap<UUID, TournamentPlayer> tournamentPlayerMap = new HashMap<>();
    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      tournamentPlayerMap.put(tournamentPlayer.getPlayer().getId(), tournamentPlayer);
    }

    assertThat(tournamentPlayers)
            .hasSize(118)
            .extracting(tp -> tp.getPlayer().getNickname(),
                        tp -> tp.getScore().setScale(2, RoundingMode.HALF_UP))
            .contains(new Tuple("NightHawk", new BigDecimal("762.02")))
            .contains(new Tuple("StarDust", new BigDecimal("934.00")))
            .contains(new Tuple("WildCard", new BigDecimal("994.62")))
            .contains(new Tuple("IronMan", new BigDecimal("1455.21")))
            .contains(new Tuple("StormBreaker", new BigDecimal("808.78")))
            .contains(new Tuple("Blaze", new BigDecimal("878.11")))
            .contains(new Tuple("DarkKnight", new BigDecimal("755.30")))
            .contains(new Tuple("Speedster", new BigDecimal("829.07")))
            .contains(new Tuple("EagleEye", new BigDecimal("996.80")))
            .contains(new Tuple("Spectre", new BigDecimal("737.17")))
            .contains(new Tuple("Rogue", new BigDecimal("1110.53")))
            .contains(new Tuple("Viper", new BigDecimal("752.52")))
            .contains(new Tuple("PlayerOne", new BigDecimal("1199.16")))
            .contains(new Tuple("Maverick", new BigDecimal("750.43")))
            .contains(new Tuple("GamerTwo", new BigDecimal("829.00")))
            .contains(new Tuple("Titan", new BigDecimal("908.42")))
            .contains(new Tuple("Guardian", new BigDecimal("998.24")))
            .contains(new Tuple("Sentinel", new BigDecimal("963.81")))
            .contains(new Tuple("Nomad", new BigDecimal("1040.99")))
            .contains(new Tuple("Wanderer", new BigDecimal("793.96")))
            .contains(new Tuple("Oracle", new BigDecimal("1065.05")))
            .contains(new Tuple("Scout", new BigDecimal("1258.08")))
            .contains(new Tuple("Pathfinder", new BigDecimal("1232.02")))
            .contains(new Tuple("Navigator", new BigDecimal("756.31")))
            .contains(new Tuple("Trailblazer", new BigDecimal("678.56")))
            .contains(new Tuple("Explorer", new BigDecimal("954.61")))
            .contains(new Tuple("Voyager", new BigDecimal("1043.64")))
            .contains(new Tuple("Pioneer", new BigDecimal("1425.83")))
            .contains(new Tuple("Seeker", new BigDecimal("1239.91")))
            .contains(new Tuple("Dreamer", new BigDecimal("881.88")))
            .contains(new Tuple("Innovator", new BigDecimal("1461.77")))
            .contains(new Tuple("Visionary", new BigDecimal("1270.01")))
            .contains(new Tuple("Architect", new BigDecimal("1241.71")))
            .contains(new Tuple("Builder", new BigDecimal("732.39")))
            .contains(new Tuple("Creator", new BigDecimal("1024.15")))
            .contains(new Tuple("Designer", new BigDecimal("1267.04")))
            .contains(new Tuple("Engineer", new BigDecimal("1252.88")))
            .contains(new Tuple("Technician", new BigDecimal("1439.82")))
            .contains(new Tuple("Analyst", new BigDecimal("826.15")))
            .contains(new Tuple("Strategist", new BigDecimal("757.73")))
            .contains(new Tuple("Planner", new BigDecimal("932.59")))
            .contains(new Tuple("Coordinator", new BigDecimal("791.07")))
            .contains(new Tuple("Manager", new BigDecimal("1082.37")))
            .contains(new Tuple("Leader", new BigDecimal("1520.56")))
            .contains(new Tuple("Commander", new BigDecimal("815.09")))
            .contains(new Tuple("Captain", new BigDecimal("692.69")))
            .contains(new Tuple("Chief", new BigDecimal("1050.05")))
            .contains(new Tuple("Director", new BigDecimal("758.27")))
            .contains(new Tuple("Overseer", new BigDecimal("1320.65")))
            .contains(new Tuple("Supervisor", new BigDecimal("1559.51")))
            .contains(new Tuple("Foreman", new BigDecimal("947.71")))
            .contains(new Tuple("Phoenix", new BigDecimal("1394.18")))
            .contains(new Tuple("Head", new BigDecimal("788.66")))
            .contains(new Tuple("Shadow", new BigDecimal("872.81")))
            .contains(new Tuple("Principal", new BigDecimal("1445.09")))
            .contains(new Tuple("Master", new BigDecimal("816.13")))
            .contains(new Tuple("Grandmaster", new BigDecimal("1145.98")))
            .contains(new Tuple("DragonSlayer", new BigDecimal("980.24")))
            .contains(new Tuple("Prodigy", new BigDecimal("1136.78")))
            .contains(new Tuple("Expert", new BigDecimal("1222.57")))
            .contains(new Tuple("Virtuoso", new BigDecimal("688.20")))
            .contains(new Tuple("Ace", new BigDecimal("747.65")))
            .contains(new Tuple("Champion", new BigDecimal("949.17")))
            .contains(new Tuple("Winner", new BigDecimal("875.25")))
            .contains(new Tuple("Victor", new BigDecimal("1067.88")))
            .contains(new Tuple("Conqueror", new BigDecimal("819.05")))
            .contains(new Tuple("Hero", new BigDecimal("815.68")))
            .contains(new Tuple("Legend", new BigDecimal("1307.02")))
            .contains(new Tuple("Myth", new BigDecimal("878.70")))
            .contains(new Tuple("Oracle", new BigDecimal("753.17")))
            .contains(new Tuple("Wizard", new BigDecimal("874.96")))
            .contains(new Tuple("Sorcerer", new BigDecimal("741.84")))
            .contains(new Tuple("Enchanter", new BigDecimal("999.74")))
            .contains(new Tuple("Mage", new BigDecimal("678.56")))
            .contains(new Tuple("Alchemist", new BigDecimal("1057.93")))
            .contains(new Tuple("Mystic", new BigDecimal("926.13")))
            .contains(new Tuple("Seer", new BigDecimal("704.59")))
            .contains(new Tuple("Prophet", new BigDecimal("1308.21")))
            .contains(new Tuple("Sage", new BigDecimal("1235.52")))
            .contains(new Tuple("Guru", new BigDecimal("808.08")))
            .contains(new Tuple("Admin", new BigDecimal("885.50")))
            .contains(new Tuple("Mentor", new BigDecimal("872.92")))
            .contains(new Tuple("Guide", new BigDecimal("746.03")))
            .contains(new Tuple("Teacher", new BigDecimal("1323.25")))
            .contains(new Tuple("Professor", new BigDecimal("909.48")))
            .contains(new Tuple("Doctor", new BigDecimal("818.34")))
            .contains(new Tuple("Scholar", new BigDecimal("810.33")))
            .contains(new Tuple("Student", new BigDecimal("918.18")))
            .contains(new Tuple("Apprentice", new BigDecimal("756.03")))
            .contains(new Tuple("Novice", new BigDecimal("1059.94")))
            .contains(new Tuple("Beginner", new BigDecimal("980.43")))
            .contains(new Tuple("Rookie", new BigDecimal("955.44")))
            .contains(new Tuple("Freshman", new BigDecimal("1519.52")))
            .contains(new Tuple("Junior", new BigDecimal("742.20")))
            .contains(new Tuple("Sophomore", new BigDecimal("1069.34")))
            .contains(new Tuple("Senior", new BigDecimal("1150.26")))
            .contains(new Tuple("Veteran", new BigDecimal("987.78")))
            .contains(new Tuple("Elder", new BigDecimal("1330.07")))
            .contains(new Tuple("Founder", new BigDecimal("874.78")))
            .contains(new Tuple("Ancestor", new BigDecimal("811.16")))
            .contains(new Tuple("Pioneer", new BigDecimal("1067.35")))
            .contains(new Tuple("Innovator", new BigDecimal("1040.11")))
            .contains(new Tuple("Creator", new BigDecimal("828.16")))
            .contains(new Tuple("Inventor", new BigDecimal("704.59")))
            .contains(new Tuple("Discoverer", new BigDecimal("942.62")))
            .contains(new Tuple("Architect", new BigDecimal("1535.39")))
            .contains(new Tuple("Engineer", new BigDecimal("1015.87")))
            .contains(new Tuple("Builder", new BigDecimal("737.17")))
            .contains(new Tuple("Developer", new BigDecimal("987.93")))
            .contains(new Tuple("Coder", new BigDecimal("1216.35")))
            .contains(new Tuple("Programmer", new BigDecimal("859.77")))
            .contains(new Tuple("Hacker", new BigDecimal("1229.69")))
            .contains(new Tuple("Cyber", new BigDecimal("1245.98")))
            .contains(new Tuple("Digital", new BigDecimal("1123.16")))
            .contains(new Tuple("Virtual", new BigDecimal("1062.28")))
            .contains(new Tuple("Online", new BigDecimal("984.78")))
            .contains(new Tuple("Netizen", new BigDecimal("1009.21")))
            .contains(new Tuple("Webmaster", new BigDecimal("1072.43")));

  }

  private void setupWinner(int seatNumber) {
    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();
    pod = podRepository.findById(pod.getId()).orElseThrow();

    // assign win to player 1, assign losses to all other players
    for (Seat seat : pod.getSeats()) {
      if (seat.getSeat() == seatNumber) {
        seat.setResult(Result.win);
      } else {
        seat.setResult(Result.loss);
      }
    }
    podRepository.save(pod);
  }

  private void setupDraw() {
    // needed to ensure a commit the transaction before we start
    TestTransaction.flagForCommit();
    TestTransaction.end();
    TestTransaction.start();
    pod = podRepository.findById(pod.getId()).orElseThrow();

    // assign win to player 1, assign losses to all other players
    for (Seat seat : pod.getSeats()) {
      seat.setResult(Result.draw);
    }
    podRepository.save(pod);
  }


  @Test
  void TestRoundPermutations() {

    List<Integer> rounds = List.of(1, 2, 3, 4, 5);
    List<List<Integer>> permutations = tournamentPlayerService.generatePermutations(rounds);

    assertThat(permutations)
            .hasSize(120)
            .contains(List.of(1, 2, 3, 5, 4), List.of(5, 1, 2, 3, 4));
  }

}
