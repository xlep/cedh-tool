package de.balloncon.cedh_tool_backend.player;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PlayerService {

  @Autowired private PlayerRepository playerRepository;

  public List<Player> getAllPlayers() {
    return playerRepository.findAll();
  }

  public Player findPlayerById(UUID id) {
    return playerRepository.findById(id).orElse(null);
  }

  public void savePlayer(PlayerDto player) {
    Player playerEntity = convertToDAO(player);
    playerRepository.save(playerEntity);
  }

  private Player convertToDAO(PlayerDto playerDTO) {
    Player player = new Player();
    player.setNickname(playerDTO.nickname());
    player.setLastname(playerDTO.lastname());
    player.setFirstname(playerDTO.firstname());
    return player;
  }
}
