package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.mapper.PlayerMapper;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
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

  public void save(Player player) {
    playerRepository.save(player);
  }
}
