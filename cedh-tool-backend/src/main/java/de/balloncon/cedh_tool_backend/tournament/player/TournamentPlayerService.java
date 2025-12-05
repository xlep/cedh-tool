package de.balloncon.cedh_tool_backend.tournament.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.dto.TournamentPlayerDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import de.balloncon.cedh_tool_backend.mapper.TournamentPlayerMapper;
import de.balloncon.cedh_tool_backend.player.PlayerService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TournamentPlayerService {

  @Autowired private TournamentPlayerRepository tournamentPlayerRepository;

  @Autowired private TournamentPlayerMapper tournamentPlayerMapper;

  @Autowired private PlayerMapper playerMapper;

  @Autowired
  private PlayerService playerService;

  public TournamentPlayerService(
      TournamentPlayerRepository tournamentPlayerRepository,
      TournamentPlayerMapper tournamentPlayerMapper,
      PlayerMapper playerMapper) {
    this.tournamentPlayerRepository = tournamentPlayerRepository;
    this.tournamentPlayerMapper = tournamentPlayerMapper;
    this.playerMapper = playerMapper;
  }

  public ResponseEntity<Void> setPlayerStatus(UUID tournamentId, UUID playerId,
      TournamentPlayerStatus status) {
    TournamentPlayer tournamentPlayer = tournamentPlayerRepository.findByTournamentAndPlayer(
        tournamentId, playerId);

    if (tournamentPlayer == null) {
      return ResponseEntity.notFound().build();
    }

    tournamentPlayer.setStatus(status);
    tournamentPlayerRepository.save(tournamentPlayer);

    return ResponseEntity.ok().build();
  }

  public void save(TournamentPlayer tournamentPlayer) {
    tournamentPlayerRepository.save(tournamentPlayer);
  }

  public List<PlayerDto> getPlayerDtosByTournament(UUID tournamentId) {
    List<TournamentPlayer> tournamentPlayers =
        tournamentPlayerRepository.findByTournamentId(tournamentId);

    return tournamentPlayers.stream()
        .map(TournamentPlayer::getPlayer)
        .map(playerMapper::toDtoList)
        .collect(Collectors.toList());
  }

  public List<TournamentPlayer> getTournamentPlayers(UUID tournamentId) {
    return tournamentPlayerRepository.findByTournamentId(tournamentId);
  }
  public List<TournamentPlayer> getPlayersForTournament (UUID tournamentId) {
    return tournamentPlayerRepository.findByTournament(tournamentId);
  }

  public TournamentPlayer getPlayerById(UUID tournamentId, UUID playerId) {
    return tournamentPlayerRepository.findByTournamentAndPlayer(tournamentId, playerId);
  }

  public List<TournamentPlayerDto> getPlayersDtosById(UUID tournamentId) {
    List<TournamentPlayer> tournamentPlayers =
        tournamentPlayerRepository.findByTournament(tournamentId);
    return tournamentPlayers.stream()
        .map(tournamentPlayerMapper::toDto)
        .collect(Collectors.toList());
  }

  public List<TournamentPlayer> findByTournament(UUID tournamentId) {
    return tournamentPlayerRepository.findByTournament(tournamentId);
  }

  public List<TournamentPlayer> findByTournamentAndStatus(
      UUID tournamentId, TournamentPlayerStatus status) {
    return tournamentPlayerRepository.findByTournamentIdAndStatus(tournamentId, status);
  }

  public List<TournamentPlayer> findByTournamentAndStatus(
      UUID tournamentId, List<TournamentPlayerStatus> statuses) {
    return tournamentPlayerRepository.findByTournament(tournamentId, statuses);
  }

  public void saveAll(List<TournamentPlayer> tournamentPlayers) {
    tournamentPlayerRepository.saveAll(tournamentPlayers);
  }

  public ResponseEntity<Void> lockPlayerToTable(UUID tournamentId, UUID playerId,
      Integer tableNumber) {
    TournamentPlayer tournamentAndPlayer = tournamentPlayerRepository.findByTournamentAndPlayer(
        tournamentId, playerId);
    tournamentAndPlayer.setTableLock(tableNumber);

    try {
      tournamentPlayerRepository.save(tournamentAndPlayer);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // TODO: error handling could be cleaner and mroe responsive
    }

    return ResponseEntity.ok().build();
  }

  public TournamentPlayer findPlayerByTournamentAndID(UUID tournamentId, UUID playerId) {
    return tournamentPlayerRepository.findByTournamentAndPlayer(tournamentId, playerId);
  }

  public List<TournamentPlayer> getActiveTournamentPlayers(UUID tournamentId) {
    return tournamentPlayerRepository.findByTournamentIdAndStatus(
        tournamentId, TournamentPlayerStatus.active);
  }
}
