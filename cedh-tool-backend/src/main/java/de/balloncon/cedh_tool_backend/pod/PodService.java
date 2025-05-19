package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PodService {

  @Autowired private PodRepository podRepository;

  public Pod getPodById(UUID podId) {
    return podRepository.findById(podId).orElse(null);
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

  public Optional<List<Pod>> getPodsByTournamentId(UUID tournamentId) {
    List<Pod> pods = podRepository.findByTournamentId(tournamentId);
    return pods.isEmpty() ? Optional.empty() : Optional.of(pods);
  }

  public void save(Pod pod) {
    podRepository.save(pod);
  }
}
