package de.balloncon.cedh_tool_backend.mapper;

import de.balloncon.cedh_tool_backend.dto.TournamentPlayerDto;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import org.springframework.stereotype.Component;

@Component
public class TournamentPlayerMapper {

  private final PlayerMapper playerMapper;

  public TournamentPlayerMapper(PlayerMapper playerMapper) {
    this.playerMapper = playerMapper;
  }

  public TournamentPlayerDto toDto(TournamentPlayer tournamentPlayer) {
    return new TournamentPlayerDto(
        tournamentPlayer.getTournament().getId(),
        tournamentPlayer.getScore(),
        tournamentPlayer.getStatus(),
        playerMapper.toDto(tournamentPlayer.getPlayer()));
  }
}
