package de.balloncon.cedh_tool_backend.util;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.tournament.Tournament;

public class TestDataGenerator {

    public static Player generateDummyPlayer() {
        Player player = new Player();
        player.setNickname("nickname");
        player.setFirstname("firstname");
        player.setLastname("lastname");

        return player;
    }

    public static Tournament generateDummyTournament() {
        Tournament tournament = new Tournament();
        tournament.setName("tournament");

        return tournament;
    }

    public static Pod generateDummyPod(Tournament tournament) {
        Pod pod = new Pod();
        pod.setTournament(tournament);

        return pod;
    }

    public static Seat generateSeatWithoutResult(Pod pod, Player player) {
        Seat seat = new Seat();
        seat.setPlayer(player);
        seat.setPod(pod);

        return seat;
    }
}
