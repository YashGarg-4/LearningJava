package com.komdox.chipsTrial.services;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Move;
// import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.model.Round;
import com.komdox.chipsTrial.repo.GameRepo;
import com.komdox.chipsTrial.repo.PlayerRepo;
import com.komdox.chipsTrial.repo.RoundRepo;


@Service
public class GameService {

    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private GameRepo gameRepo;
    @Autowired
    private RoundRepo roundRepo;

    // Round round = roundRepo.findById(1).get();
    // Game game = gameRepo.findById(1).get();

    public void processMove(Move move) {

        switch (move.getAction()) {
            case "CALL":
                if(!isMoveValid(move)) {
                    throw new IllegalArgumentException("Invalid move: " + move);
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
                // isGameOver();
                break;

            case "RAISE":
                if(!isMoveValid(move)) {
                    throw new IllegalArgumentException("Invalid move: " + move);
                }
                updateRoundCurrentBet(move);
                updateGamePot(move);
                updatePlayerPurse(move);
                updateRoundMap(move);
                advancePlayerTurn(move);
            default:
                throw new IllegalArgumentException("Unknown action: " + move.getAction());
        }

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
        
        throw new UnsupportedOperationException("Unimplemented method 'advancePlayerTurn'");
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
        if (move.getAction() == "CALL"){ //For Call
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

    public Object getCurrentGame() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentGame'");
    }
}
