package de.balloncon.cedh_tool_backend.util;

import de.balloncon.cedh_tool_backend.player.Player;
import de.balloncon.cedh_tool_backend.pod.Pod;
import de.balloncon.cedh_tool_backend.seat.Seat;
import de.balloncon.cedh_tool_backend.tournament.Tournament;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayer;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerId;
import de.balloncon.cedh_tool_backend.tournament.player.TournamentPlayerService;

import java.math.BigDecimal;

public class TestDataGenerator {

    public static Player generatePlayer() {
        Player player = new Player();
        player.setNickname("nickname");
        player.setFirstname("firstname");
        player.setLastname("lastname");

        return player;
    }

    public static Player generatePlayer(String nickname) {
        Player player = new Player();
        player.setNickname(nickname);
        player.setFirstname("firstname");
        player.setLastname("lastname");

        return player;
    }

    public static Tournament generateTournament() {
        Tournament tournament = new Tournament();
        tournament.setName("tournament");

        return tournament;
    }

    public static Pod generatePod(Tournament tournament) {
        Pod pod = new Pod();
        pod.setTournament(tournament);
        pod.setRound(1);

        return pod;
    }

    public static Seat generateSeatWithoutResult(Pod pod, Player player, int seatNumber) {
        Seat seat = new Seat();
        seat.setPlayer(player);
        seat.setPod(pod);
        seat.setSeat(seatNumber);

        return seat;
    }

    public static Seat generateSeatWithoutResult(Pod pod, Player player) {
        return generateSeatWithoutResult(pod, player, 1);
    }



    public static TournamentPlayer generateTournamentPlayer(Tournament tournament, Player player) {
        TournamentPlayer tournamentPlayer = new TournamentPlayer();
        TournamentPlayerId id = new TournamentPlayerId();
        id.setTournament(tournament.getId());
        id.setPlayer(player.getId());

        tournamentPlayer.setId(id);
        tournamentPlayer.setTournament(tournament);
        tournamentPlayer.setPlayer(player);
        tournamentPlayer.setScore(TournamentPlayerService.STARTING_SCORE);

        return tournamentPlayer;
    }
}
