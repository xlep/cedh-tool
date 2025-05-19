package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class PodService {

  @Autowired private PodRepository podRepository;
  @Autowired private SeatService seatService;
  @Autowired private PlayerService playerService;

  public Pod getPodById(UUID podId) {
    return podRepository.findById(podId).orElse(null);
  }

  public Optional<List<Pod>> getPodsByTournamentId(UUID tournamentId) {
    List<Pod> pods = podRepository.findByTournamentId(tournamentId);
    return pods.isEmpty() ? Optional.empty() : Optional.of(pods);
  }

  public List<Pod> getPodsAndSeatsByTournamentId(UUID tournamentId) {
    return podRepository.findAllWithSeats(tournamentId);
  }

  public Optional<Integer> getLastPlayedRoundNumberByTournamentId(UUID tournamentId) {
    return podRepository.findHighestColumnValueForRound(tournamentId);
  }

  public List<Pod> getPodsByRoundNumber(UUID tournamentId, int roundNumber) {
    return podRepository.findPodsWithSeatsAndPlayersByTournamentIdAndRound(
        tournamentId, roundNumber);
  }

  public Pod getFinalPodByTournamentIdAndType(UUID tournamentId, PodType podType) {
    return podRepository.findPodByTournamentIdAndType(tournamentId, podType);
  }

  public Optional<List<Player>> getPlayersByPodId(UUID podId) {
    return podRepository.findPlayersByPodId(podId);
  }

  @Transactional
  public ResponseEntity<Void> reportResult(UUID podId, UUID winningPlayerID, Result result) {
    return switch (result) {
      case Result.win -> recordWin(podId, winningPlayerID);
      case Result.draw -> recordDraw(podId);
      default ->
              new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    };
  }

  private ResponseEntity<Void> recordWin(UUID podId, UUID winningPlayerID) {
    Pod pod = getPodById(podId);

    if (pod == null || !pod.hasPlayerWithId(winningPlayerID)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // persist win for winning player
    seatService.saveResult(podId, winningPlayerID, Result.win);

    // persist loss for all other players in pod
    List<Player> players = pod.getPlayers();
    players.remove(playerService.findPlayerById(winningPlayerID));

    for (Player player : players) {
      seatService.saveResult(podId, player.getId(), Result.loss);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  private ResponseEntity<Void> recordDraw(UUID podId) {
    Pod pod = getPodById(podId);
    List<Player> players = pod.getPlayers();

    for (Player player : players) {
      seatService.saveResult(podId, player.getId(), Result.draw);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  public void save(Pod pod) {
    podRepository.save(pod);
  }
}
