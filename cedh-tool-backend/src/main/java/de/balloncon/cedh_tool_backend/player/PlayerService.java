package de.balloncon.cedh_tool_backend.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(String id) {
        return playerRepository.findById(id).orElse(null);
    }
}
