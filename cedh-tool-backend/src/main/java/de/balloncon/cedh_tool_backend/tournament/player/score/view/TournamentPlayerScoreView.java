package de.balloncon.cedh_tool_backend.tournament.player.score.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TournamentPlayerScoreView {
    String nickname;
    String score;
    String firstname;
    String lastname;
}

