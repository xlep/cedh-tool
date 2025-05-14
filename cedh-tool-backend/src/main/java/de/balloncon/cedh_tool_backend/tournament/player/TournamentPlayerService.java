package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.tournament.player.score.view.TournamentPlayerScoreView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Component
public class TournamentPlayerService {

  @Autowired private final TournamentPlayerRepository tournamentPlayerRepository;

  private static final BigDecimal WAGER_AMOUNT = new BigDecimal("0.07");
  private static final int NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT = 3;
  private static final BigDecimal SEAT_ONE_MULTIPLICATOR = new BigDecimal("1.32");
  private static final BigDecimal SEAT_TWO_MULTIPLICATOR = new BigDecimal("1.05");
  private static final BigDecimal SEAT_THREE_MULTIPLICATOR = new BigDecimal("0.91");
  private static final BigDecimal SEAT_FOUR_MULTIPLICATOR = new BigDecimal("0.72");
  private static final String DIVIDER_FOR_DRAW = "4.000";

  public TournamentPlayerService(TournamentPlayerRepository tournamentPlayerRepository) {
    this.tournamentPlayerRepository = tournamentPlayerRepository;
  }

  public List<TournamentPlayerScoreView> getPlayerScoresByTournament(UUID tournamentId) {
    return tournamentPlayerRepository.findPlayerScoresByTournament(tournamentId);
  }

  public Optional<List<TournamentPlayer>> getTopTenByTournamentOrderByScoreDesc(
      UUID tournamentId, int cutSize) {
    return tournamentPlayerRepository.findTopTenByTournamentOrderByScoreDesc(
        tournamentId, PageRequest.of(0, cutSize));
  }

  public void calculateAndAssignNewScores(
      UUID tournamentId, HashMap<String, Integer> playerSeatMap, UUID winningPlayerId) {

    List<String> playerIds = new ArrayList<>(playerSeatMap.keySet());
    List<TournamentPlayer> tournamentPlayers =
        tournamentPlayerRepository.findByTournamentAndPlayers(tournamentId, playerIds);

    if (playerSeatMap.isEmpty()) {
      throw new IllegalStateException("No tournament players found for given pod");
    }

    BigDecimal totalContribution = deductPointsFromPlayers(playerSeatMap, tournamentPlayers);
    givePointsToPlayers(winningPlayerId, tournamentPlayers, totalContribution);
    tournamentPlayerRepository.saveAll(tournamentPlayers);
  }

  private static void givePointsToPlayers(
      UUID winningPlayerId,
      List<TournamentPlayer> tournamentPlayers,
      BigDecimal totalContribution) {
    // Step 2: Add the totalContribution to the winner
    boolean isWinningPlayer = winningPlayerId != null;
    BigDecimal divider = new BigDecimal(DIVIDER_FOR_DRAW);

    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      // Case player won
      if (isWinningPlayer) {
        if (tournamentPlayer.getPlayer().getId().toString().equals(winningPlayerId.toString())) {
          tournamentPlayer.setScore(tournamentPlayer.getScore().add(totalContribution));
        }
        // case draw
      } else {
        tournamentPlayer.setScore(
            tournamentPlayer
                .getScore()
                .add(
                    (totalContribution.divide(
                        divider, NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT, RoundingMode.UP))));
      }
    }
  }

  private static BigDecimal deductPointsFromPlayers(
      HashMap<String, Integer> playerSeatMap, List<TournamentPlayer> tournamentPlayers) {
    // Step 1: Deduct 7% from each player
    BigDecimal totalContribution = new BigDecimal("0.000");

    for (TournamentPlayer tournamentPlayer : tournamentPlayers) {
      Integer seat = playerSeatMap.get(tournamentPlayer.getPlayer().getId().toString());

      switch (seat) {
        case 1:
          totalContribution =
              totalContribution.add(
                  performHareruyaCalculation(tournamentPlayer, SEAT_ONE_MULTIPLICATOR));
          break;
        case 2:
          totalContribution =
              totalContribution.add(
                  performHareruyaCalculation(tournamentPlayer, SEAT_TWO_MULTIPLICATOR));
          break;
        case 3:
          totalContribution =
              totalContribution.add(
                  performHareruyaCalculation(tournamentPlayer, SEAT_THREE_MULTIPLICATOR));
          break;
        case 4:
          totalContribution =
              totalContribution.add(
                  performHareruyaCalculation(tournamentPlayer, SEAT_FOUR_MULTIPLICATOR));
          break;
        default:
          System.out.println("Invalid seat number: " + seat);
          break;
      }
    }
    return totalContribution;
  }

  private static BigDecimal performHareruyaCalculation(
      TournamentPlayer player, BigDecimal seatMultiplicator) {
    BigDecimal contribution =
        player
            .getScore()
            .multiply(WAGER_AMOUNT)
            .setScale(NUMBER_OF_DIGITS_BEHIND_DECIMAL_POINT, RoundingMode.UP)
            .multiply(seatMultiplicator);
    player.setScore(player.getScore().subtract(contribution));
    return contribution;
  }
}
