package com.komdox.chipsTrial.services;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.model.Round;
// import com.komdox.chipsTrial.model.Round;
import com.komdox.chipsTrial.repo.GameRepo;
import com.komdox.chipsTrial.repo.PlayerRepo;
import com.komdox.chipsTrial.repo.TableRepo; 
import com.komdox.chipsTrial.repo.RoundRepo;

@Service
public class TableService {

    @Autowired
    private RoundRepo roundRepo; // H2 persistence [3][6]
    @Autowired
    private PlayerRepo playerRepo; // H2 persistence [3][6]
    @Autowired
    private GameRepo gameRepo; // H2 persistence [3][6]
    @Autowired
    private TableRepo tableRepo; // H2 persistence [3][6]
    
    public Player seatPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        playerRepo.save(player); // H2 persistence [3][6]
        // currentTable.getPlayers().add(player);
        return player;
    }
    
    public void initializeGame() {
        Game game = new Game();
        game.setPot(0);
        game.setStage(0); // Pre-flop
        game.setActivePlayers(playerRepo.findAll());
        gameRepo.save(game);
        newRound();
    }

    public void newRound() {
        Round round = new Round();
        round.setCurrentBet(0);
        round.setCurrentPlayerIndex((tableRepo.findById(1).get().getDealerIndex() + 1) % playerRepo.findAll().size());
        round.setDealearPlayed(false);
        
        // Initialize bets map with active players and 0 as initial bet
        Game game = gameRepo.findById(1).get();
        Map<Integer, Integer> bets = game.getActivePlayers().stream()
            .collect(Collectors.toMap(
                Player::getId,    // Key: player ID
                player -> 0      // Value: initial bet of 0
            ));
        round.setBets(bets);
        
        roundRepo.save(round);
    }

}


