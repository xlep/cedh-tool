package de.balloncon.cedh_tool_backend.tournament;
import de.balloncon.cedh_tool_backend.dto.RoundDto;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatRepository;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class TournamentService {

  @Autowired private TournamentPlayerRepository tournamentPlayerRepository;

  @Autowired private PodRepository podRepository;

  @Autowired private SeatRepository seatRepository;

  @Autowired private TournamentRepository tournamentRepository;

  private static final int DEFAULT_POD_SIZE = 4;

    @Transactional
    public RoundDto generateNextRound(UUID tournamentId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        List<Player> players = tournamentPlayerRepository.findPlayersByTournamentId(tournamentId);
        Map<UUID, Set<UUID>> opponents = buildOpponentMap(players, tournamentId);
        Map<UUID, Map<UUID, Integer>> pairingMatrix = buildPairingMatrix(tournamentId);

        List<List<Player>> pods = attemptValidRound(players, opponents, pairingMatrix, DEFAULT_POD_SIZE);
        int nextRound = podRepository.findMaxRoundForTournament(tournamentId).orElse(0) + 1;

        List<RoundDto.PodDto> podDtos = savePodsAndSeats(pods, nextRound, tournament);

        return RoundDto.builder()
                .round(nextRound)
                .pods(podDtos)
                .build();
    }

    private List<RoundDto.PodDto> savePodsAndSeats(List<List<Player>> pods, int round, Tournament tournament) {
        List<RoundDto.PodDto> podDtos = new ArrayList<>();

        for (int i = 0; i < pods.size(); i++) {
            podDtos.add(savePodWithSeats(pods.get(i), round, i + 1, tournament));
        }

        return podDtos;
    }

    private RoundDto.PodDto savePodWithSeats(List<Player> players, int round, int podName, Tournament tournament) {
        Pod pod = new Pod();
        pod.setRound(round);
        pod.setName(podName);
        pod.setTournament(tournament);
        podRepository.save(pod);

        List<RoundDto.SeatDto> seatDtos = new ArrayList<>();
        int seatNumber = 1;

        for (Player player : players) {
            Seat seat = new Seat();
            seat.setPod(pod);
            seat.setPlayer(player);
            seat.setSeat(seatNumber);
            seatRepository.save(seat);

            seatDtos.add(RoundDto.SeatDto.builder()
                    .playerId(player.getId())
                    .nickname(player.getNickname())
                    .seat(seatNumber)
                    .build());

            seatNumber++;
        }

        return RoundDto.PodDto.builder()
                .podId(pod.getId())
                .podName(pod.getName())
                .seats(seatDtos)
                .build();
    }

        private Map<UUID, Set<UUID>> buildOpponentMap(List<Player> players, UUID tournamentId) {
            Map<UUID, Set<UUID>> opponents = new HashMap<>();
            for (Player player : players) {
                opponents.put(player.getId(), new HashSet<>());
            }

            // Fetch past pods to populate the opponent map
            List<Pod> previousPods = podRepository.findByTournamentId(tournamentId);
            for (Pod pod : previousPods) {
                List<Seat> seats = seatRepository.findByPodId(pod.getId());
                for (int i = 0; i < seats.size(); i++) {
                    for (int j = i + 1; j < seats.size(); j++) {
                        UUID p1 = seats.get(i).getPlayer().getId();
                        UUID p2 = seats.get(j).getPlayer().getId();
                        if (opponents.containsKey(p1) && opponents.containsKey(p2)) {
                            opponents.get(p1).add(p2);
                            opponents.get(p2).add(p1);
                        }
                    }
                }
            }

            return opponents;
        }

        private Map<UUID, Map<UUID, Integer>> buildPairingMatrix(UUID tournamentId) {
            Map<UUID, Map<UUID, Integer>> pairingMatrix = new HashMap<>();

            // Fetch past pods to populate the pairing matrix
            List<Pod> previousPods = podRepository.findByTournamentId(tournamentId);
            for (Pod pod : previousPods) {
                List<Seat> seats = seatRepository.findByPodId(pod.getId());
                for (int i = 0; i < seats.size(); i++) {
                    for (int j = i + 1; j < seats.size(); j++) {
                        UUID p1 = seats.get(i).getPlayer().getId();
                        UUID p2 = seats.get(j).getPlayer().getId();

                        pairingMatrix.putIfAbsent(p1, new HashMap<>());
                        pairingMatrix.putIfAbsent(p2, new HashMap<>());

                        pairingMatrix.get(p1).put(p2, pairingMatrix.get(p1).getOrDefault(p2, 0) + 1);
                        pairingMatrix.get(p2).put(p1, pairingMatrix.get(p2).getOrDefault(p1, 0) + 1);
                    }
                }
            }

            return pairingMatrix;
        }

        private List<List<Player>> attemptValidRound(List<Player> players, Map<UUID, Set<UUID>> opponents, Map<UUID, Map<UUID, Integer>> pairingMatrix, int podSize) {
            Random rand = new Random();
            int attempts = 0;
            final int maxAttempts = 2_000_000_000;
            int roundNumber = podRepository.findMaxRoundForTournament(players.get(0).getId()).orElse(0) + 1;

            while (attempts++ < maxAttempts) {
                List<Player> shuffled = new ArrayList<>(players);
                Collections.shuffle(shuffled, rand);

                List<Integer> podSizes = determinePodSizes(shuffled.size(), podSize);
                if (podSizes == null) continue;

                List<List<Player>> pods = splitIntoPods(shuffled, podSizes);
                if (arePodsValid(pods, pairingMatrix, roundNumber)) {
                    return pods;
                }
            }

            throw new RuntimeException("Could not generate a valid round after many attempts.");
        }

        private List<Integer> determinePodSizes(int totalPlayers, int podSize) {
            List<Integer> podSizes = new ArrayList<>();
            int fullPods = totalPlayers / podSize;
            int remaining = totalPlayers % podSize;

            for (int i = 0; i < fullPods; i++) {
                podSizes.add(podSize);
            }

            return switch (remaining) {
                case 0 -> podSizes;
                case 3 -> {
                    podSizes.add(3);
                    yield podSizes;
                }
                case 2 -> {
                    if (podSizes.size() == 0) yield null;
                    podSizes.remove(podSizes.size() - 1);
                    podSizes.add(3);
                    podSizes.add(3);
                    yield podSizes;
                }
                case 1 -> null;
                default -> null;
            };
        }

        private List<List<Player>> splitIntoPods(List<Player> shuffled, List<Integer> podSizes) {
            List<List<Player>> pods = new ArrayList<>();
            int index = 0;

            for (int size : podSizes) {
                pods.add(new ArrayList<>(shuffled.subList(index, index + size)));
                index += size;
            }

            return pods;
        }

        private boolean arePodsValid(List<List<Player>> pods, Map<UUID, Map<UUID, Integer>> pairingMatrix, int roundNumber) {
            int maxRepeats = 0;  // Allow no repeat the first 4 rounds
            if (roundNumber > 4) {
                maxRepeats = 1;  // Allow one repeats after round 4
            }

            // Check the validity of each pod
            for (List<Player> pod : pods) {
                for (int j = 0; j < pod.size(); j++) {
                    for (int k = j + 1; k < pod.size(); k++) {
                        UUID p1 = pod.get(j).getId();
                        UUID p2 = pod.get(k).getId();

                        // Check if the players have been paired too many times
                        int currentPairings = pairingMatrix.getOrDefault(p1, Map.of()).getOrDefault(p2, 0);
                        if (currentPairings > maxRepeats) {
                            return false;  // Invalid pod if pairing count exceeds allowed repeats
                        }
                    }
                }
            }
            return true;  // All pods are valid
        }
    }

