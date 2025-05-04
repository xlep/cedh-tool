package de.balloncon.cedh_tool_backend.player;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import de.balloncon.cedh_tool_backend.pod.Seat;
import de.balloncon.cedh_tool_backend.tournament.Tournamentplayer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nickname", length = Integer.MAX_VALUE)
    private String nickname;

    @Column(name = "firstname", length = Integer.MAX_VALUE)
    private String firstname;

    @Column(name = "lastname", length = Integer.MAX_VALUE)
    private String lastname;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private Set<Seat> seats = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
    private Set<Tournamentplayer> tournamentplayers = new LinkedHashSet<>();
}