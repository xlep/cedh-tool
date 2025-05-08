package de.balloncon.cedh_tool_backend.tournament.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tournamentplayers")
public class TournamentPlayer {
    @EmbeddedId
    private TournamentPlayerId id;

    @JsonBackReference
    @MapsId("tournament")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @JsonManagedReference
    @MapsId("player")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "score", precision = 7, scale = 3)
    private BigDecimal score;

}