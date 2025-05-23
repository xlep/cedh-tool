package de.balloncon.cedh_tool_backend.seat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.balloncon.cedh_tool_backend.dto.Result;
import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat implements Cloneable {

  @EmbeddedId
  private SeatId id = new SeatId();

  @JsonIgnore
  @MapsId("pod")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pod", nullable = false)
  private Pod pod;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @MapsId("player")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "player", nullable = false)
  private Player player;

  @Column(name = "seat")
  private Integer seat;

  @Enumerated(EnumType.STRING)
  @Column(name = "result", length = 4)
  private Result result;
}
