package de.balloncon.cedh_tool_backend.seat;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class SeatId implements Serializable {
  private static final long serialVersionUID = -5986839943965338788L;

  @NotNull
  @Column(name = "pod", nullable = false)
  private UUID pod;

  @NotNull
  @Column(name = "player", nullable = false)
  private UUID player;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    SeatId entity = (SeatId) o;
    return Objects.equals(this.pod, entity.pod) && Objects.equals(this.player, entity.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pod, player);
  }
}
