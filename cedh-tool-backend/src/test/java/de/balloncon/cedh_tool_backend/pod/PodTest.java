package de.balloncon.cedh_tool_backend.pod;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.seat.Seat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PodTest {

  static Pod pod;
  static Player player1;
  static Player player2;
  static Player player3;
  static Player player4;

  static UUID player1Id;

  @BeforeAll
  static void setUp() {
    pod = new Pod();
    Set<Seat> seats = new HashSet<>();
    pod.setSeats(seats);

    Seat seat1 = new Seat();
    player1 = new Player();
    player1Id = UUID.randomUUID();
    player1.setId(player1Id);
    seat1.setPlayer(player1);
    seats.add(seat1);

    Seat seat2 = new Seat();
    player2 = new Player();
    seat2.setPlayer(player2);
    seats.add(seat2);

    Seat seat3 = new Seat();
    player3 = new Player();
    seat3.setPlayer(player3);
    seats.add(seat3);

    Seat seat4 = new Seat();
    player4 = new Player();
    seat4.setPlayer(player4);
    seats.add(seat4);
  }

  @Test
  void getPlayers() {
    List<Player> players = pod.getPlayers();

    assertThat(players.size()).isEqualTo(4);
    assertThat(players.contains(player1)).isTrue();
    assertThat(players.contains(player2)).isTrue();
    assertThat(players.contains(player3)).isTrue();
    assertThat(players.contains(player4)).isTrue();
  }

  @Test
  void hasPlayerWithId() {
    assertThat(pod.hasPlayerWithId(player1Id)).isTrue();
    assertThat(pod.hasPlayerWithId(UUID.randomUUID())).isFalse();
  }
}