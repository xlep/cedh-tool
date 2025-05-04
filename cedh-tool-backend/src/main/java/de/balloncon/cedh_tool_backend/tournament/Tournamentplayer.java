package de.balloncon.cedh_tool_backend.tournament;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.balloncon.cedh_tool_backend.player.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tournamentplayers")
public class Tournamentplayer {
    @EmbeddedId
    private TournamentplayerId id;

    @JsonBackReference
    @MapsId("tournament")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournament", nullable = false)
    private Tournament tournament;

    @JsonManagedReference
    @MapsId("player")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player", nullable = false)
    private Player player;

    @Column(name = "score", precision = 6, scale = 4)
    private BigDecimal score;

}