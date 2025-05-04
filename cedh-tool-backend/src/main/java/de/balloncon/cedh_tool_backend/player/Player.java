package de.balloncon.cedh_tool_backend.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "playerid", columnDefinition = "uuid", updatable = false, nullable = false)
    UUID playerId;

    String nickname;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;
}
