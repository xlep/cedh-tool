package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerRepository;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {

  @Autowired
  private TournamentPlayerRepository tournamentPlayerRepository;
  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private PlayerMapper playerMapper;

  public List<Player> getAllPlayers() {
    return playerRepository.findAll();
  }

  public Player findPlayerById(UUID id) {
    return playerRepository.findById(id).orElse(null);
  }

  public List<Player> getPlayersByTournament(UUID tournamentId) {
    return playerRepository.findByTournamentId(tournamentId);
  }

  public List<PlayerDto> getPlayerDtosByTournament(UUID tournamentId) {
    List<Player> tournamentPlayers = playerRepository.findByTournamentId(tournamentId);
    return tournamentPlayers.stream()
        .map(playerMapper::toDtoList)
        .collect(Collectors.toList());
  }

  public void savePlayer(PlayerDto player) {
    Player playerEntity = playerMapper.toDao(player);
    playerRepository.save(playerEntity);
  }

  public void savePlayers(List<Player> players) {
    playerRepository.saveAll(players);
  }

  public List<TournamentPlayer> getActiveTournamentPlayers(UUID tournamentId) {
    return tournamentPlayerRepository.findByTournamentIdAndStatus(tournamentId, TournamentPlayerStatus.active);
  }

  public List<TournamentPlayer> getTournamentPlayers(UUID tournamentId) {
    return tournamentPlayerRepository.findByTournamentId(tournamentId);
  }
}
