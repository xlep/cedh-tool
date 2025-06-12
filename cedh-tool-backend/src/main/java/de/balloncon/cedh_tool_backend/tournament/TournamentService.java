package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.dto.RoundDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.pod.PodRepository;
import de.balloncon.cedh_tool_backend.pod.PodService;
import de.balloncon.cedh_tool_backend.pod.PodType;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.seat.SeatService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerId;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.score.ScoreService;
import de.balloncon.cedh_tool_backend.util.ResponseMessages;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TournamentService {

  @Autowired
  private PodRepository podRepository;
  @Autowired
  private TournamentRepository tournamentRepository;
  @Autowired
  private PodService podService;
  @Autowired
  private PlayerService playerService;
  @Autowired
  private SeatService seatService;
  @Autowired
  private ScoreService scoreService;
  @Autowired
  private TournamentPlayerService tournamentPlayerService;
  @Autowired
  private PlayerMapper playerMapper;

  private TournamentLogic tournamentLogic = null;

  // TODO: these should not be fields of a Service class
  private final static int TOP_TEN_CUT = 10;

  public void save(Tournament tournament) {
    tournamentRepository.save(tournament);
  }

  public RoundDto generateNextRound(UUID tournamentId){
    Tournament tournament = tournamentRepository.findTournamentById(tournamentId);

    switch (tournament.getMode()) {
      case hareruya:
      this.tournamentLogic = new TournamentServiceHareruya();
      break;
      case classic:
      this.tournamentLogic = new TournamentServiceSwiss();
      break;
    }

    return tournamentLogic.generateNextRound(tournamentId);
  }

  protected List<Player> generateSeatOrder(Tournament tournament, Set<Player> playersInProd) {
    Map<Player, Integer> seatHistory = new HashMap<>();

    // add all previous seats for all players in the pod
    for (Player player : playersInProd) {
      List<Seat> previousPlayerSeats = seatService.getPlayerSeatsByTournament(
          tournament.getId(), player.getId());

      Integer playerSeatHistory = 0;
      for (Seat seat : previousPlayerSeats) {
        playerSeatHistory += seat.getSeat();
      }
      seatHistory.put(player, playerSeatHistory);
    }

    // order so the player with the highest combined value is first in the list
    // -> will get the lowest seat next round
    return seatHistory.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  @Transactional
  public ResponseEntity<String> determineCut(UUID tournamentId, int cutTo) {

    Optional<Integer> optionalRoundNumber = podService.getLastPlayedRoundNumberByTournamentId(tournamentId);

    if (optionalRoundNumber.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    int roundNumber = optionalRoundNumber.get();
    List <TournamentPlayer> players = scoreService.calculatePlayerScoresAfterSwissRounds(tournamentId, roundNumber);

    if (cutTo == TOP_TEN_CUT) {
      return generateTopTenCut(players, roundNumber);
    } else {
      ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ResponseMessages.RESPONSE_BAD_REQUEST_TOP_CUT);
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  private ResponseEntity<String> generateTopTenCut(List<TournamentPlayer> players,
    int lastRoundNumber) {
    List<TournamentPlayer> topTenPlayers = generateTopCut(players, TOP_TEN_CUT);
    int firstPod = 1;
    List<TournamentPlayer> topEightPlayers = placeFinalPlayersInPod(topTenPlayers, lastRoundNumber, firstPod);
    generateSemifinals(topEightPlayers, lastRoundNumber, firstPod);
    return ResponseEntity.status(200).build();
  }

  private List<TournamentPlayer> generateTopCut(List<TournamentPlayer> players, int cutSize) {
    return players.stream()
        .sorted(Comparator.comparing(TournamentPlayer::getScore).reversed())
        .limit(cutSize)
        .toList();
  }

  private void generateSemifinals(List<TournamentPlayer> players, int lastRound, int firstPodName) {

    int semifinalRoundNumber = lastRound + 1;

    /*
    Player seating table for semifinals
    Seat => player number in list
    Pod 1     | Pod 2
    1   =>  1 | 2   =>  2
    4   =>  4 | 3   =>  3
    5   =>  5 | 6   =>  6
    8   =>  8 | 7   =>  7
    */

    List<TournamentPlayer> semifinalsPod1 = new ArrayList<>();
    List<TournamentPlayer> semifinalsPod2 = new ArrayList<>();

    // Define pod assignment: 1 for Pod1, 2 for Pod2
    int[] podAssignments = {1, 2, 2, 1, 1, 2, 2, 1};

    for (int i = 0; i < players.size(); i++) {
      TournamentPlayer player = players.get(i);

      if (i >= podAssignments.length) {
        System.out.println("Unhandled case at index " + i);
        continue;
      }

      if (podAssignments[i] == 1) {
        semifinalsPod1.add(player);
      } else if (podAssignments[i] == 2) {
        semifinalsPod2.add(player);
      }
    }

    Pod podOne =
        generateAndPersistPod(
            semifinalRoundNumber,
            firstPodName,
            semifinalsPod1.getFirst().getTournament(),
            PodType.SEMIFINAL);
    findPlayerForSeating(semifinalsPod1, podOne);

    Pod podTwo =
        generateAndPersistPod(
            semifinalRoundNumber,
            firstPodName + 1,
            semifinalsPod2.getFirst().getTournament(),
            PodType.SEMIFINAL);
    findPlayerForSeating(semifinalsPod2, podTwo);
  }

  private List<TournamentPlayer> placeFinalPlayersInPod(
      List<TournamentPlayer> immutablePlayerList, int lastRoundNumber, int podNumber) {
    // first and second player for final
    List<TournamentPlayer> mutablePlayerList = new ArrayList<>(immutablePlayerList);

    List<TournamentPlayer> finalists = new ArrayList<>();

    TournamentPlayer finalistOne = mutablePlayerList.get(0);
    TournamentPlayer finalistTwo = mutablePlayerList.get(1);

    finalists.add(finalistOne);
    finalists.add(finalistTwo);

    // remove these player from top 10
    mutablePlayerList.remove(finalistOne);
    mutablePlayerList.remove(finalistTwo);

    int finals = 2;
    int finalRoundNumber = lastRoundNumber + finals;

    Pod pod =
        generateAndPersistPod(
            finalRoundNumber, podNumber, finalistOne.getTournament(), PodType.FINAL);

    findPlayerForSeating(finalists, pod);

    return mutablePlayerList;
  }

  private void findPlayerForSeating(List<TournamentPlayer> finalists, Pod pod) {
    for (int i = 0; i < finalists.size(); i++) {
      Player player = playerService.findPlayerById(finalists.get(i).getPlayer().getId());
      seatService.generateAndPersistSeat(pod, player, i + 1);
    }
  }

  private Pod generateAndPersistPod(
      int round, int podName, Tournament tournament, PodType podType) {
    Pod pod = new Pod();
    pod.setRound(round);
    pod.setName(podName);
    pod.setTournament(tournament);
    pod.setType(podType);
    return podRepository.save(pod); // This will persist the pod and return the saved pod
  }

  public List<RoundDto> getRoundsByTournamentId(UUID tournamentId) {
    // Fetch pods sorted by round and name
    List<Pod> pods = podRepository.findByTournamentIdOrderByRoundAscNameAsc(tournamentId);

    // Group pods by round number
    Map<Integer, List<Pod>> podsByRound = pods.stream()
        .collect(Collectors.groupingBy(Pod::getRound));

    // Build RoundDto for each round
    List<RoundDto> rounds = podsByRound.entrySet().stream()
        .map(entry -> {
          int roundNumber = entry.getKey();
          List<RoundDto.PodDto> podDtos = entry.getValue().stream()
              .map(pod -> {
                // For each pod, get seats (players + seat numbers)
                List<RoundDto.SeatDto> seats = pod.getSeats().stream()
                    .map(seat -> new RoundDto.SeatDto(
                        seat.getPlayer().getId(),
                        seat.getPlayer().getFirstname(),
                        seat.getPlayer().getLastname(),
                        seat.getPlayer().getNickname(),
                        seat.getSeat(), seat.getResult()))
                    .collect(Collectors.toList());

                return new RoundDto.PodDto(pod.getId(), pod.getName(), seats);
              })
              .collect(Collectors.toList());

          return new RoundDto(roundNumber, podDtos);
        })
        .sorted(Comparator.comparingInt(RoundDto::round))
        .collect(Collectors.toList());

    return rounds;
  }

  public Tournament getTournament(UUID tournamentId) {
    return tournamentRepository.findById(tournamentId).orElse(null);
  }

  @Transactional
  public ResponseEntity<Void> addPlayersToTournament(List<PlayerDto> players, UUID tournamentId) {
    List <Player> playerList = playerMapper.toDaoList(players);
    playerService.savePlayers(playerList);

    Tournament tournament = getTournament(tournamentId);
    List<TournamentPlayer> tournamentPlayers = new ArrayList<>();

    for (Player player : playerList) {
      //Create ID
      TournamentPlayerId tournamentPlayerId = new TournamentPlayerId();
      tournamentPlayerId.setPlayer(player.getId());
      tournamentPlayerId.setTournament(tournamentId);

      //Create Player
      TournamentPlayer tournamentPlayer = new TournamentPlayer();
      tournamentPlayer.setPlayer(player);
      tournamentPlayer.setTournament(tournament);
      tournamentPlayer.setId(tournamentPlayerId);

      tournamentPlayers.add(tournamentPlayer);

    }

    tournamentPlayerService.saveAll(tournamentPlayers);
    return ResponseEntity.ok().build();
  }

  public RoundDto getLatestRoundByTournamentId(UUID tournamentId) {
    // Fetch pods sorted by round and name
    List<Pod> pods = podRepository.findByTournamentIdOrderByRoundAscNameAsc(tournamentId);

    if (pods.isEmpty()) {
      return null; // or throw exception, depending on your API strategy
    }

    // Group pods by round number
    Map<Integer, List<Pod>> podsByRound = pods.stream()
        .collect(Collectors.groupingBy(Pod::getRound));

    // Get the latest round number
    int latestRound = podsByRound.keySet().stream()
        .max(Integer::compareTo)
        .orElseThrow(); // safe, since we already checked that pods is not empty

    // Convert latest round's pods to PodDto
    List<RoundDto.PodDto> podDtos = podsByRound.get(latestRound).stream()
        .map(pod -> {
          List<RoundDto.SeatDto> seats = pod.getSeats().stream()
              .map(seat -> new RoundDto.SeatDto(
                  seat.getPlayer().getId(),
                  seat.getPlayer().getFirstname(),
                  seat.getPlayer().getLastname(),
                  seat.getPlayer().getNickname(),
                  seat.getSeat(), seat.getResult()))
              .collect(Collectors.toList());

          return new RoundDto.PodDto(pod.getId(), pod.getName(), seats);
        })
        .collect(Collectors.toList());

    return new RoundDto(latestRound, podDtos);
  }

}
