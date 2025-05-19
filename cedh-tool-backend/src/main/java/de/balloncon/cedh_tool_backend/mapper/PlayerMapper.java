package de.balloncon.cedh_tool_backend.mapper;

import de.balloncon.cedh_tool_backend.dto.PlayerDto;
import de.balloncon.cedh_tool_backend.player.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public PlayerDto toDto(Player player) {
    return new PlayerDto(
        player.getNickname(), player.getFirstname(), player.getLastname(), player.getEmail());
    }

}
