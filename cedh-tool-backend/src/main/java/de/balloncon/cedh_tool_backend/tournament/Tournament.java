package de.balloncon.cedh_tool_backend.tournament;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tournament")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "mode", length = Integer.MAX_VALUE)
    private String mode;

}