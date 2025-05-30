package de.balloncon.cedh_tool_backend.mapper;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.player.Player;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

  public PlayerDto toDtoList(Player player) {
    return new PlayerDto(
        player.getNickname(), player.getFirstname(), player.getLastname(), player.getEmail(),
        player.getId());
  }

  public List<PlayerDto> toDtoList(List<Player> players) {
    List<PlayerDto> playerDtos = new ArrayList<>();
    for (Player player : players) {
      playerDtos.add(toDtoList(player));
    }
    return playerDtos;
  }

  public Player toDao(PlayerDto playerDTO) {
    Player player = new Player();
    player.setNickname(playerDTO.nickname());
    player.setLastname(playerDTO.lastname());
    player.setFirstname(playerDTO.firstname());
    return player;
  }

  public List<Player> toDaoList(List<PlayerDto> playerDTOs) {
    List<Player> players = new ArrayList<>();
    for (PlayerDto dto : playerDTOs) {
      players.add(toDao(dto));
    }
    return players;
  }
}
