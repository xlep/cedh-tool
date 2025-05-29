package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import de.balloncon.cedh_tool_backend.dto.TournamentPlayerDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {

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
        .map(playerMapper::toDto)
        .collect(Collectors.toList());
  }

  public void savePlayer(PlayerDto player) {
    Player playerEntity = convertToDAO(player);
    playerRepository.save(playerEntity);
  }

  public void savePlayers(List<Player> players) {
    playerRepository.saveAll(players);
  }

  private Player convertToDAO(PlayerDto playerDTO) {
    Player player = new Player();
    player.setNickname(playerDTO.nickname());
    player.setLastname(playerDTO.lastname());
    player.setFirstname(playerDTO.firstname());
    return player;
  }
}
