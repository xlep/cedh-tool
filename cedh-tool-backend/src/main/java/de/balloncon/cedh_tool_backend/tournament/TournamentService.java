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

    private static final int POD_SIZE = 4;

    private final List<List<List<Integer>>> schedule = new ArrayList<>();
    private final Set<String> seenPairs = new HashSet<>();
    private Map<Integer, Player> indexToPlayer = new HashMap<>();
    private Map<UUID, Integer> playerIdToIndex = new HashMap<>();

    public RoundDto generateNextRound(UUID tournamentId) {
        Tournament tournament = tournamentRepository.findTournamentById(tournamentId);
        List<Player> players = tournamentPlayerRepository.findAllPlayersByTournamentId(tournamentId);
        int numPlayers = players.size();
        int groupsPerRound = numPlayers / POD_SIZE;

        // Map UUIDs to integers
        for (int i = 0; i < players.size(); i++) {
            indexToPlayer.put(i, players.get(i));
            playerIdToIndex.put(players.get(i).getId(), i);
        }

        // Populate seenPairs from existing pods
        populateSeenPairs(tournamentId);

        List<List<Integer>> groups = new ArrayList<>();
        boolean[] used = new boolean[numPlayers];

        boolean solved = backtrack(groups, used, 0, groupsPerRound, POD_SIZE, numPlayers);
        if (!solved) {
            throw new RuntimeException("Could not generate valid round.");
        }

        // Save result to DB
        int nextRound = podRepository.findMaxRoundForTournament(tournamentId).orElse(0) + 1;
        List<RoundDto.PodDto> podDtos = savePodsAndSeats(groups, nextRound, tournament);

        return RoundDto.builder()
                .round(nextRound)
                .pods(podDtos)
                .build();
    }

    private boolean backtrack(List<List<Integer>> groups, boolean[] used, int groupIdx, int groupsPerRound, int groupSize, int numPlayers) {
        if (groupIdx == groupsPerRound) {
            schedule.add(new ArrayList<>(groups));
            return true;
        }

        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            if (!used[i]) candidates.add(i);
        }

        List<List<Integer>> combinations = generateCombinations(candidates, groupSize);
        for (List<Integer> combo : combinations) {
            if (isValidGroup(combo)) {
                for (int p : combo) used[p] = true;
                addPairs(combo);
                groups.add(combo);

                if (backtrack(groups, used, groupIdx + 1, groupsPerRound, groupSize, numPlayers)) return true;

                groups.remove(groups.size() - 1);
                removePairs(combo);
                for (int p : combo) used[p] = false;
            }
        }

        return false;
    }

    private void populateSeenPairs(UUID tournamentId) {
        List<Pod> pods = podRepository.findByTournamentId(tournamentId);
        for (Pod pod : pods) {
            List<Seat> seats = seatRepository.findByPodId(pod.getId());
            for (int i = 0; i < seats.size(); i++) {
                for (int j = i + 1; j < seats.size(); j++) {
                    UUID id1 = seats.get(i).getPlayer().getId();
                    UUID id2 = seats.get(j).getPlayer().getId();
                    Integer idx1 = playerIdToIndex.get(id1);
                    Integer idx2 = playerIdToIndex.get(id2);
                    if (idx1 != null && idx2 != null) {
                        seenPairs.add(makeKey(idx1, idx2));
                    }
                }
            }
        }
    }

    private boolean isValidGroup(List<Integer> group) {
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                String key = makeKey(group.get(i), group.get(j));
                if (seenPairs.contains(key)) return false;
            }
        }
        return true;
    }

    private void addPairs(List<Integer> group) {
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                seenPairs.add(makeKey(group.get(i), group.get(j)));
            }
        }
    }

    private void removePairs(List<Integer> group) {
        for (int i = 0; i < group.size(); i++) {
            for (int j = i + 1; j < group.size(); j++) {
                seenPairs.remove(makeKey(group.get(i), group.get(j)));
            }
        }
    }

    private String makeKey(int a, int b) {
        return Math.min(a, b) + ":" + Math.max(a, b);
    }

    private List<List<Integer>> generateCombinations(List<Integer> players, int k) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinationsRecursive(players, k, 0, new ArrayList<>(), result);
        return result;
    }

    private void generateCombinationsRecursive(List<Integer> players, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < players.size(); i++) {
            current.add(players.get(i));
            generateCombinationsRecursive(players, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    private List<RoundDto.PodDto> savePodsAndSeats(List<List<Integer>> groups, int round, Tournament tournament) {
        List<RoundDto.PodDto> podDtos = new ArrayList<>();
        int podName = 1;

        for (List<Integer> group : groups) {
            Pod pod = new Pod();
            pod.setRound(round);
            pod.setName(podName++);
            pod.setTournament(tournament);
            podRepository.save(pod);

            List<RoundDto.SeatDto> seats = new ArrayList<>();
            int seatNum = 1;

            for (int idx : group) {
                Player player = indexToPlayer.get(idx);
                Seat seat = new Seat();
                seat.setPod(pod);
                seat.setPlayer(player);
                seat.setSeat(seatNum);
                seatRepository.save(seat);

                seats.add(RoundDto.SeatDto.builder()
                        .playerId(player.getId())
                        .nickname(player.getNickname())
                        .seat(seatNum++)
                        .build());
            }

            podDtos.add(RoundDto.PodDto.builder()
                    .podId(pod.getId())
                    .podName(pod.getName())
                    .seats(seats)
                    .build());
        }

        return podDtos;
    }
}


