package com.komdox.chipsTrial.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.komdox.chipsTrial.model.Move;
// import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.model.Table;

@Service
public class GameLogic {

    private Table currentTable;

    public void gameInit() {
        this.currentTable = new Table();
        currentTable.setId(1);
        currentTable.setPot(0);
        currentTable.setCurrentBet(0);
        currentTable.setDealerIndex(0);
        currentTable.setCurrentPlayerIndex(1);
        currentTable.setPlayers(new HashMap<>()); // Initialize with an map
        currentTable.setPlayerTurnOrder(new ArrayList<>()); // Initialize with an empty list
        currentTable.setActiveCount(currentTable.getPlayerTurnOrder().size());// Initialize active player count
        currentTable.setGamePlay(true); 
        
        // Add players to the table **PENDING**
        System.out.println("Game initialized!!");
    }

    public Table processMove(Move move) {

        switch (move.getAction()) {

            case "call":
                if (move.getAmount() < 0 || move.getAmount() > currentTable.getPlayers().get(move.getPlayerId()).getPurse() || move.getAmount() != currentTable.getCurrentBet()) {
                    System.out.println("Invalid bet amount: " + move.getAmount());
                    return currentTable; // Invalid bet, return current state
                }

                currentTable.setPot(currentTable.getPot() + move.getAmount());
                currentTable.getPlayers().get(move.getPlayerId()).setPurse(
                    currentTable.getPlayers().get(move.getPlayerId()).getPurse() - move.getAmount()
                ); // Deduct the bet amount from the player's purse

                currentTable.setCurrentBet(move.getAmount()); // Update the current bet
                currentTable.setCurrentPlayerIndex((currentTable.getCurrentPlayerIndex() + 1) % currentTable.getPlayers().size()); // Move to the next player
                System.out.println("Player " + move.getPlayerId() + " called!");
                
                break;

            case "fold":
                currentTable.getPlayers().get(move.getPlayerId()).setStatus("folded"); // Set player status to false (folded)   
                currentTable.setCurrentPlayerIndex((currentTable.getCurrentPlayerIndex() + 1) % currentTable.getPlayers().size()); // Move to the next player
                break;
            // Add more cases for other move types as needed

            case "raise":
                if(move.getAmount() <= 0 || move.getAmount() > currentTable.getPlayers().get(move.getPlayerId()).getPurse() || move.getAmount() < 2*currentTable.getCurrentBet()) {
                    System.out.println("Invalid raise amount: " + move.getAmount());
                    return currentTable; // Invalid raise, return current state
                }
                currentTable.setPot(currentTable.getPot() + move.getAmount()); // Add the raise amount to the pot
                currentTable.getPlayers().get(move.getPlayerId()).setPurse(
                    currentTable.getPlayers().get(move.getPlayerId()).getPurse() - move.getAmount()
                ); // Deduct the raise amount from the player's purse
                currentTable.setCurrentBet(move.getAmount()); // Update the current bet
                currentTable.setCurrentPlayerIndex((currentTable.getCurrentPlayerIndex() + 1) % currentTable.getPlayers().size()); // Move to the next player
                break;
            default:
                System.out.println("Unknown move type: " + move.getAction());
                return currentTable; // Unknown move, return current state
        }

        return this.currentTable;
    }
    // Optionally, add getters/setters or methods to interact with currentTable

    public Table getCurrentTable() {
        return this.currentTable;
    }

    public void advanceToNextPlayer() {
        if (currentTable.getActiveCount() > 1) { // Check if there are more than one active players
            // Increment the current player index
            currentTable.setCurrentPlayerIndex((currentTable.getCurrentPlayerIndex() + 1) % currentTable.getPlayers().size());
            // Check if the next player is active4
            while (currentTable.getPlayers().get(currentTable.getPlayerTurnOrder().get(currentTable.getCurrentPlayerIndex())).getStatus().equals("folded")) {
                currentTable.setCurrentPlayerIndex((currentTable.getCurrentPlayerIndex() + 1) % currentTable.getPlayers().size());
            }
        } else {
            currentTable.setGamePlay(false); // No active players left, end the game
        }
    }
}
