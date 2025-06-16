package com.komdox.chipsTrial.services;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Move;
import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.model.PokerTable;
// import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.model.Round;
import com.komdox.chipsTrial.repo.GameRepo;
import com.komdox.chipsTrial.repo.PlayerRepo;
import com.komdox.chipsTrial.repo.RoundRepo;
import com.komdox.chipsTrial.repo.TableRepo;

import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GameService {

    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private TableRepo tableRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private RoundRepo roundRepo;
    @Autowired
    private TableService tableService; // Assuming this service is used to initialize the game and manage players

    // Round round = roundRepo.findById(1).get();
    // Game game = gameRepo.findById(1).get();

    public void processMove(Move move) {
        Round round = roundRepo.findById(1).get();

        switch (move.getAction()) {
            case "CALL":
                if(!isMoveValid(move)) {
                    System.out.println("Invalid move: " + move);
                    break;
                }
                updateGamePot(move);
                updatePlayerPurse(move);
                updateRoundMap(move);
                advancePlayerTurn(move);
                break;

            case "FOLD":
                updateRoundMap(move);
                updateActivePlayers(move);
                advancePlayerTurn(move);
                isGameOver();
                break;

            case "RAISE":
                if(!isMoveValid(move)) {
                    System.out.println("Invalid move: " + move);
                    break;
                }
                updateRoundCurrentBet(move);
                updateGamePot(move);
                updatePlayerPurse(move);
                updateRoundMap(move);
                advancePlayerTurn(move);
                break;
            default:
                System.out.println("Invalid move: " + move);
        }
        
        PokerTable table = tableRepo.findById(1).get(); // Assuming this method retrieves the current table
        if (move.getPlayerId() == table.getDealerIndex() + 1) {
            round.setDealearPlayed(true); // Mark the dealer as having played
        }

        if(round.getDealerPlayed() && 
            round.getBets().get(move.getPlayerId()).equals(getMaxBetFromMap(round.getBets()))){
            roundOver();
        } // make condition to check if dealer has played
}

private Integer getMaxBetFromMap(Map<Integer, Integer> bets) {
    return bets.values().stream()
              .max(Integer::compareTo)
              .orElse(0);
}

    private void roundOver() {
        newRound();
        Game game = gameRepo.findById(1).get();
        game.setStage(game.getStage() + 1);
        gameRepo.save(game); // Update game stage to next stage;
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
                _ -> 0      // Value: initial bet of 0
            ));
        round.setBets(bets);
        
        roundRepo.save(round);
    }

    private void isGameOver() {
        if (gameRepo.findById(1).get().getActivePlayers().size() == 1 || gameRepo.findById(1).get().getStage() == 4) {
            updateWinners();
            tableRepo.findById(1).ifPresent(table -> {
                table.setDealerIndex((table.getDealerIndex() + 1) % playerRepo.findAll().size()); // Move dealer to next player
                tableRepo.save(table);
            });
            tableService.initializeGame(); // Reset the game for a new round    
        }
    }

    private void updateWinners() {
        Game game = gameRepo.findById(1).get();
        game.getActivePlayers().get(0).setPurse(
            game.getActivePlayers().get(0).getPurse() + game.getPot()
        );        
    }

    private void updateActivePlayers(Move move) {
        Game game = gameRepo.findById(1).get();
        game.getActivePlayers().removeIf(player -> player.getId() == move.getPlayerId());
        gameRepo.save(game);
        
    }

    private void updateRoundCurrentBet(Move move) {

        Round round = roundRepo.findById(1).get();

        round.setCurrentBet(move.getBetAmount());
        roundRepo.save(round);
    }

    private void advancePlayerTurn(Move move) {

        Round round = roundRepo.findById(1).get();

        int currentIndex = round.getCurrentPlayerIndex();
        int nextIndex = (currentIndex + 1) % round.getBets().size();
        round.setCurrentPlayerIndex(nextIndex);///////////////// May face the Id vs Index issue while referencing player at some point *Warning*
        roundRepo.save(round);
        }

    private void updateRoundMap(Move move) {

        Round round = roundRepo.findById(1).get();

        if (move.getAction().equals("FOLD")) {

            round.getBets().remove(move.getPlayerId());
            roundRepo.save(round);

        } else {

            round.getBets().put(move.getPlayerId(), round.getBets().get(move.getPlayerId()) + move.getBetAmount());   
            roundRepo.save(round);
        }
        }


    private void updatePlayerPurse(Move move) {
        playerRepo.findById(move.getPlayerId()).ifPresent(player -> {
            player.setPurse(player.getPurse() - move.getBetAmount());
            playerRepo.save(player);
        });
    }

    private void updateGamePot(Move move) {
            gameRepo.findById(1).get().setPot(
            gameRepo.findById(1).get().getPot() + move.getBetAmount()
        );
    }

    private boolean isMoveValid(Move move) {
        if (move.getAction().equals("CALL")){ //For Call
            if(move.getBetAmount() >= 0 && move.getBetAmount() <= playerRepo.findById(move.getPlayerId()).get().getPurse() && move.getBetAmount() == roundRepo.findById(1).get().getCurrentBet()) {
                return true;
            } else {
                return false;
            } 
        }
        else{ //For Raise
            if(move.getBetAmount() >= 0 && move.getBetAmount() <= playerRepo.findById(move.getPlayerId()).get().getPurse() && move.getBetAmount() >= 2*roundRepo.findById(1).get().getCurrentBet()) { 
                return true;
            } else {
                return false;
            }

        }        
    }

}
