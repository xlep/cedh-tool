package de.balloncon.cedh_tool_backend.tournament.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tournamentplayers")
public class TournamentPlayer implements Cloneable {

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

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private TournamentPlayerStatus status;

  @Column(name = "table_lock")
  private Integer tableLock;

  @Override
  public TournamentPlayer clone() {
    try {
      TournamentPlayer clone = (TournamentPlayer) super.clone();

      // Deep copy the EmbeddedId
      if (this.id != null) {
        clone.id = new TournamentPlayerId(this.id.getTournament(), this.id.getPlayer());
      } else {
        clone.id = null;
      }

      return clone;
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException(e);
    }
  }
}
