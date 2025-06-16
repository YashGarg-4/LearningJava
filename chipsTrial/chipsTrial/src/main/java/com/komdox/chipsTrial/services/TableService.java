package com.komdox.chipsTrial.services;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.model.PokerTable;
import com.komdox.chipsTrial.model.Round;
// import com.komdox.chipsTrial.model.Round;
import com.komdox.chipsTrial.repo.GameRepo;
import com.komdox.chipsTrial.repo.PlayerRepo;
import com.komdox.chipsTrial.repo.TableRepo; 
import com.komdox.chipsTrial.repo.RoundRepo;

@Service
public class TableService {

    @Autowired
    private RoundRepo roundRepo;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private TableRepo tableRepo;
    
    public Player seatPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        return playerRepo.save(player);
    }

    public PokerTable initializeTable() {
        PokerTable table = new PokerTable();
        return tableRepo.save(table);
    }
    
    public void initializeGame() {
        Game game = new Game();
        game.setPot(0);
        game.setStage(0);
        game.setActivePlayers(playerRepo.findAll());
        gameRepo.save(game);
        newRound();
    }

    public void newRound() {
        Round round = new Round();
        round.setCurrentBet(0);
        
        Game game = gameRepo.findById(1).get();
        PokerTable table = tableRepo.findById(1).get();
        
        // Fixed: Use active players for positioning
        if (!game.getActivePlayers().isEmpty()) {
            int dealerPosition = table.getDealerIndex();
            int firstPlayerPosition = (dealerPosition + 1) % game.getActivePlayers().size();
            round.setCurrentPlayerIndex(firstPlayerPosition);
        }
        
        round.setDealearPlayed(false);
        
        // Initialize bets map with active players
        Map<Integer, Integer> bets = game.getActivePlayers().stream()
            .collect(Collectors.toMap(
                Player::getId,
                _ -> 0
            ));
        round.setBets(bets);
        
        roundRepo.save(round);
    }
}



