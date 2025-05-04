package de.balloncon.cedh_tool_backend.pod;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.balloncon.cedh_tool_backend.player.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {
    @EmbeddedId
    private SeatId id;

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

    @Column(name = "result", length = Integer.MAX_VALUE)
    private String result;

}