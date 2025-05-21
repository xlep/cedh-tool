package de.balloncon.cedh_tool_backend.mapper;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.player.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerMapper {
    public PlayerDto toDto(Player player) {
    return new PlayerDto(
        player.getNickname(), player.getFirstname(), player.getLastname(), player.getEmail(), player.getId());
    }

    public List<PlayerDto> toDto(List<Player> players) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        for (Player player : players) {
            playerDtos.add(toDto(player));
        }
        return playerDtos;
    }
}
