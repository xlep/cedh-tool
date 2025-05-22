package de.balloncon.cedh_tool_backend.tournament.player.score.view;


import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TournamentPlayerScoreViewFactory {

  public TournamentPlayerScoreView createFromTournamentPlayer(TournamentPlayer tournamentPlayer) {
    TournamentPlayerScoreView tournamentPlayerScoreView = new TournamentPlayerScoreView();
    tournamentPlayerScoreView.setNickname(tournamentPlayer.getPlayer().getNickname());
    tournamentPlayerScoreView.setScore(tournamentPlayer.getScore().toString());

    if (!tournamentPlayer.getPlayer().getFirstname().isEmpty()) {
        tournamentPlayerScoreView.setFirstname(tournamentPlayer.getPlayer().getFirstname());
    }
    if (!tournamentPlayer.getPlayer().getLastname().isEmpty()) {
        tournamentPlayerScoreView.setLastname(tournamentPlayer.getPlayer().getLastname());
    }

    return tournamentPlayerScoreView;
  }

  public List<TournamentPlayerScoreView> createFromTournamentPlayerList(List<TournamentPlayer> tournamentPlayerList) {
      List<TournamentPlayerScoreView> tournamentPlayerScoreViews = new ArrayList<>();
      for (TournamentPlayer tournamentPlayer : tournamentPlayerList) {
          tournamentPlayerScoreViews.add(createFromTournamentPlayer(tournamentPlayer));
      }
      return tournamentPlayerScoreViews;
  }
}
