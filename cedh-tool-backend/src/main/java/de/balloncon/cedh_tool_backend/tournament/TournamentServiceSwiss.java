package de.balloncon.cedh_tool_backend.tournament;

import de.balloncon.cedh_tool_backend.dto.RoundDto;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TournamentServiceSwiss implements TournamentLogic {

  @Override
  public RoundDto generateNextRound(UUID tournamentId){
  //    Swiss round logic goes here
  //    5 pts win
  //    1 point draw
  return null;
  }
}
