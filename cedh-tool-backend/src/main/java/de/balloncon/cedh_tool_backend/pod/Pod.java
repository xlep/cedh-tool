package de.balloncon.cedh_tool_backend.pod;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "pod")
public class Pod {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "tournament_id", nullable = false)
  private Tournament tournament;

  @Column(name = "name")
  private Integer name;

  @Column(name = "round")
  private Integer round;

  @OneToMany(mappedBy = "pod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Seat> seats = new HashSet<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "type", length = 12)
  private PodType type;

  public List<Player> getPlayers() {
    Set<Seat> seatsList = this.getSeats();
    List<Player> players = new ArrayList<>();

    for (Seat seat : seatsList) {
      players.add(seat.getPlayer());
    }
    return players;
  }

  public boolean hasPlayerWithId(UUID id) {
    boolean found = false;
    for (Player player : this.getPlayers()) {
      if (id.equals(player.getId())) {
        found = true;
      }
    }
    return found;
  }
}
