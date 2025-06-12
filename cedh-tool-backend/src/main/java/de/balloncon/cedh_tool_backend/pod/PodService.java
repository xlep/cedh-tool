package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PodService {

  @Autowired
  private PodRepository podRepository;
  @Autowired
  private SeatService seatService;
  @Autowired
  private PlayerService playerService;
  @Autowired
  private TournamentPlayerService tournamentPlayerService;

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
      UUID podId, UUID winningPlayerID, Result result) {
    return switch (result) {
      case Result.WIN -> recordWin(podId, winningPlayerID);
      case Result.DRAW -> recordDraw(podId);
      default -> new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    };
  }

  private ResponseEntity<Void> recordWin(
      UUID podId, UUID winningPlayerID) {
    Pod pod = getPodById(podId);

    if (pod == null || !pod.hasPlayerWithId(winningPlayerID)) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    switch (pod.getType()) {
      case SWISS -> reportPodResult(podId, winningPlayerID, pod);
      case SEMIFINAL -> recordSemifinal(podId, winningPlayerID, pod.getTournament().getId());
      case FINAL -> recordFinals(podId, winningPlayerID);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }

  private void reportPodResult(UUID podId, UUID winningPlayerID, Pod pod) {
    // persist win for winning player
    seatService.saveResult(podId, winningPlayerID, Result.WIN);

    // persist loss for all other players in pod
    List<Player> players = pod.getPlayers();

    players.remove(playerService.findPlayerById(winningPlayerID));

    for (Player player : players) {
      seatService.saveResult(podId, player.getId(), Result.LOSS);
    }
  }

  private ResponseEntity<Void> recordDraw(UUID podId) {
    Pod pod = getPodById(podId);
    List<Player> players = pod.getPlayers();

    for (Player player : players) {
      seatService.saveResult(podId, player.getId(), Result.DRAW);
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
    int podFullyfilled = 4;
    Pod finalPod = getFinalPodByTournamentIdAndType(tournamentId, PodType.FINAL);
    Player finalist = playerService.findPlayerById(winningPlayerID);
    Seat seatForFinalist = generateSeat(finalPod, finalist, finalPod.getSeats().size() + 1);
    Set<Seat> seats = finalPod.getSeats();
    seats.add(seatForFinalist);


    if (seats.size() == podFullyfilled) {
      arrangeSeatsInFinalPod(finalPod);
    }
  }

  private void arrangeSeatsInFinalPod(Pod pod) {
    // 1. Filter seats 3 and 4
    Seat seat3 = null;
    Seat seat4 = null;

    for (Seat seat : pod.getSeats()) {
      if (seat.getSeat() != null) {
        if (seat.getSeat() == 3) {
          seat3 = seat;
        } else if (seat.getSeat() == 4) {
          seat4 = seat;
        }
      }
    }
    //2. remove seats 3 and 4 to reassign them later
    pod.getSeats().remove(seat3);
    pod.getSeats().add(seat4);

    //3. Look up their scores (assuming you have tournament context)
    if (seat3 != null && seat4 != null) {
      Player player3 = seat3.getPlayer();
      Player player4 = seat4.getPlayer();

      Tournament tournament = pod.getTournament();

      TournamentPlayer tp3 = tournamentPlayerService.getPlayerById(tournament.getId(), player3.getId());
      TournamentPlayer tp4 = tournamentPlayerService.getPlayerById(tournament.getId(), player4.getId());

      BigDecimal score3 = tp3.getScore();
      BigDecimal score4 = tp4.getScore();

      // 4. Reassign seats: higher score gets seat 3
      if (score3.compareTo(score4) < 0) {
        // seat4 has higher score → swap
        seat3.setSeat(4);
        seat4.setSeat(3);
      } else {
        // seat3 already has higher score → do nothing or reassign to be sure
        seat3.setSeat(3);
        seat4.setSeat(4);
      }
      pod.getSeats().add(seat3);
      pod.getSeats().add(seat4);
    }

  }

  private void persistResults(UUID podId, UUID winningPlayerID) {
    Pod pod = getPodById(podId);
    Player winningPlayer = playerService.findPlayerById(winningPlayerID);
    Set<Seat> seats = pod.getSeats();
    for (Seat seat : seats) {
      if (seat.getPlayer().equals(winningPlayer)) {
        seat.setResult(Result.WIN);
      } else {
        seat.setResult(Result.LOSS);
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

  public Result getResult(Pod pod) {
    // default to a winning pod
    Result podResult = Result.WIN;

    if (pod != null) {
      for (Seat seat : pod.getSeats()) {
        if (seat.getResult() == null) {
          break;
        }
        Result result = seat.getResult();
        switch (result) {
          case Result.DRAW -> podResult = Result.DRAW;
          case Result.BYE -> podResult = Result.BYE;
        }
        ;
      }
    }

    return podResult;
  }

  public Pod getPodByRoundAndTableNumber(UUID tournamentId, int round, int tableNumber) {
    return podRepository.findPodByRoundAndPodNumber(tournamentId, round, tableNumber);
  }

  public List<Pod> getAllByePodsByTournamentId(UUID tournamentId) {
    return podRepository.findAllByePodsForTournament(tournamentId);
  }

  public Pod findPodByRoundAndPodNumber(UUID tournamentId, int round, int tableLock) {
    return podRepository.findPodByRoundAndPodNumber(tournamentId, round, tableLock);
  }

  public List<Pod> getByTournamentIdOrderByRoundAscNameAsc(UUID tournamentId) {
    return podRepository.findByTournamentIdOrderByRoundAscNameAsc(tournamentId);
  }

  public Optional<Pod> findByID(UUID podId) {
    return podRepository.findById(podId);
  }
}
