package de.balloncon.cedh_tool_backend.tournament.player;

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
public class TournamentPlayerId implements Serializable {
    private static final long serialVersionUID = -6721082864744376078L;
    @NotNull
    @Column(name = "tournament_id", nullable = false)
    private UUID tournament;

    @NotNull
    @Column(name = "player_id", nullable = false)
    private UUID player;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TournamentPlayerId entity = (TournamentPlayerId) o;
        return Objects.equals(this.tournament, entity.tournament) &&
                Objects.equals(this.player, entity.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournament, player);
    }

}