package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.RoundDto;
import java.util.UUID;

public interface TournamentLogic {

  RoundDto generateNextRound(UUID tournamentId);
}
