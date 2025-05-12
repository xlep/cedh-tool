package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerRepository;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class TournamentPlayerServiceTest {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TournamentPlayerRepository tournamentPlayerRepository;

    private Tournament tournament;
    private List<Player> players;
    private Map<String, Integer> seatMap;
    @Autowired
    private TournamentPlayerService tournamentPlayerService;

    @BeforeEach
    void setUp() {
        tournament = new Tournament();
        tournament.setName("Test Tournament");
        tournament.setMode("Standard");
        tournamentRepository.save(tournament);

        players = new ArrayList<>();
        seatMap = new HashMap<>();

        for (int i = 1; i <= 4; i++) {
            Player player = new Player();
            player.setFirstname("Player " + i);
            playerRepository.save(player);
            players.add(player);

            TournamentPlayer tournamentPlayer = new TournamentPlayer();
            TournamentPlayerId id = new TournamentPlayerId();
            id.setTournament(tournament.getId());
            id.setPlayer(player.getId());

            tournamentPlayer.setId(id);
            tournamentPlayer.setTournament(tournament);
            tournamentPlayer.setPlayer(player);
            tournamentPlayer.setScore(new BigDecimal("1500.000"));
            tournamentPlayerRepository.save(tournamentPlayer);

            seatMap.put(player.getId().toString(), i);
        }
    }

    @Test
    void testSeat1Wins() {
        runAndAssertScoreForWinnerSeat(1);
    }

    @Test
    void testSeat2Wins() {
        runAndAssertScoreForWinnerSeat(2);
    }

    @Test
    void testSeat3Wins() {
        runAndAssertScoreForWinnerSeat(3);
    }

    @Test
    void testSeat4Wins() {
        runAndAssertScoreForWinnerSeat(4);
    }

    private void runAndAssertScoreForWinnerSeat(int winningSeat) {
        Player winner = players.get(winningSeat - 1);
        UUID winnerId = winner.getId();

        tournamentPlayerService.calculateAndAssignNewScores(tournament.getId(), new HashMap<>(seatMap), winnerId);

        List<TournamentPlayer> updatedPlayers = tournamentPlayerRepository.findByTournamentAndPlayers(
                tournament.getId(), players.stream().map(p -> p.getId().toString()).toList()
        );

        BigDecimal totalContribution = BigDecimal.ZERO;
        for (TournamentPlayer tp : updatedPlayers) {
            if (!tp.getPlayer().getId().equals(winnerId)) {
                assertThat(tp.getScore()).isLessThan(new BigDecimal("1500.000"));
                totalContribution = totalContribution.add(new BigDecimal("1500.000").subtract(tp.getScore()));
            }
        }

        TournamentPlayer winningPlayer = updatedPlayers.stream()
                .filter(tp -> tp.getPlayer().getId().equals(winnerId))
                .findFirst().orElseThrow();

        assertThat(winningPlayer.getScore()).isEqualByComparingTo(new BigDecimal("1500.000").add(totalContribution));
    }

    @Test
    void testDrawScenario() {
        tournamentPlayerService.calculateAndAssignNewScores(tournament.getId(), new HashMap<>(seatMap), null);

        List<TournamentPlayer> updatedPlayers = tournamentPlayerRepository.findByTournamentAndPlayers(
                tournament.getId(), players.stream().map(p -> p.getId().toString()).toList()
        );

        // Expected final scores after draw
        Map<Integer, BigDecimal> expectedScores = Map.of(
                1, new BigDecimal("1466.400"),
                2, new BigDecimal("1494.750"),
                3, new BigDecimal("1509.450"),
                4, new BigDecimal("1529.400")
        );

        for (TournamentPlayer tournamentPlayer : updatedPlayers) {
            int seat = seatMap.get(tournamentPlayer.getPlayer().getId().toString());
            BigDecimal expected = expectedScores.get(seat);
            assertThat(tournamentPlayer.getScore())
                    .withFailMessage("Seat %d expected score %s but got %s", seat, expected, tournamentPlayer.getScore())
                    .isEqualByComparingTo(expected);
        }
    }

}