package de.balloncon.cedh_tool_backend.pod;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PodService {

  @Autowired private PodRepository podRepository;

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
}
