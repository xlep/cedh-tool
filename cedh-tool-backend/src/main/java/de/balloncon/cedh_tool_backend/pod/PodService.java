package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.PodDto;
import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.mapper.PodMapper;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.seat.Seat;
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
  @Autowired private PodMapper podMapper;

  public Pod getPodById(UUID podId) {
    return podRepository.findById(podId).orElse(null);
  }

  public List<Pod> getPodsByTournamentId(UUID tournamentId) {
        return podRepository.findByTournamentId(tournamentId);
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

  public void save(Pod pod) {
    podRepository.save(pod);
  }

  @Transactional
  public ResponseEntity<Void> reportResult(
      UUID podId, UUID winningPlayerID, Result result, UUID tournamentId, PodType podType) {
    return switch (result) {
      case Result.win -> recordWin(podId, winningPlayerID, tournamentId, podType);
      case Result.draw -> recordDraw(podId);
      default -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    };
  }

  private ResponseEntity<Void> recordWin(
      UUID podId, UUID winningPlayerID, UUID tournamentId, PodType podType) {
    Pod pod = getPodById(podId);

    if (pod == null || !pod.hasPlayerWithId(winningPlayerID)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    switch (podType) {
      case SWISS -> reportResult(podId, winningPlayerID, pod);
      case SEMIFINAL -> recordSemifinal(podId, winningPlayerID, tournamentId);
      case FINAL -> recordFinals(podId, winningPlayerID);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  private void reportResult(UUID podId, UUID winningPlayerID, Pod pod) {
    // persist win for winning player
    seatService.saveResult(podId, winningPlayerID, Result.win);

    // persist loss for all other players in pod
    List<Player> players = pod.getPlayers();
    players.remove(playerService.findPlayerById(winningPlayerID));

    for (Player player : players) {
      seatService.saveResult(podId, player.getId(), Result.loss);
    }
  }

  private ResponseEntity<Void> recordDraw(UUID podId) {
    Pod pod = getPodById(podId);
    List<Player> players = pod.getPlayers();

    for (Player player : players) {
      seatService.saveResult(podId, player.getId(), Result.draw);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  private ResponseEntity<Void> recordSemifinal(
      UUID podId, UUID winningPlayerID, UUID tournamentId) {
    persistResults(podId, winningPlayerID);
    seatWinnerInFinals(winningPlayerID, tournamentId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  private void seatWinnerInFinals(UUID winningPlayerID, UUID tournamentId) {
    Pod finalPod = getFinalPodByTournamentIdAndType(tournamentId, PodType.FINAL);
    Player finalist = playerService.findPlayerById(winningPlayerID);
    Seat seatForFinalist = generateSeat(finalPod, finalist, finalPod.getSeats().size() + 1);
    finalPod.getSeats().add(seatForFinalist);
  }

  private void persistResults(UUID podId, UUID winningPlayerID) {
    Pod pod = getPodById(podId);
    Player winningPlayer = playerService.findPlayerById(winningPlayerID);
    Set<Seat> seats = pod.getSeats();
    for (Seat seat : seats) {
      if (seat.getPlayer().equals(winningPlayer)) {
        seat.setResult(Result.win);
      } else {
        seat.setResult(Result.loss);
      }
    }
  }

  private ResponseEntity<Void> recordFinals(UUID podId, UUID winningPlayerID) {
    persistResults(podId, winningPlayerID);
    // todo additional logic for generatring the raked list
    return new ResponseEntity<>(HttpStatus.OK);
  }

  Seat generateSeat(Pod pod, Player player, int seatNum) {
    Seat seat = new Seat();
    seat.setPod(pod);
    seat.setPlayer(player);
    seat.setSeat(seatNum);
    return seat;
  }

  public List<Pod> findByTournamentIdOrderByRoundAscNameAsc(UUID tournamentId) {
    return podRepository.findByTournamentIdOrderByRoundAscNameAsc(tournamentId);
  }
}
