package com.komdox.chipsTrial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.repo.PlayerRepo; 

@Service
public class TableService {
    
    @Autowired
    private PlayerRepo playerRepo; // H2 persistence [3][6]
    
    public Player seatPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        playerRepo.save(player); // H2 persistence [3][6]
        // currentTable.getPlayers().add(player);
        return player;
    }
    
    public Game initializeGame() {
        Game game = new Game();
        game.setPot(0);
        game.setStage(0); // Pre-flop
        game.setBets(playerRepo.findAll(), 0); // Initialize bets for all players to 0
        return game;
    }

}


