package de.balloncon.cedh_tool_backend.player;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String nickname;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;
}
