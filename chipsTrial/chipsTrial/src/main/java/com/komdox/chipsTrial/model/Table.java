package com.komdox.chipsTrial.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Table {

    private int id;
    private Map<Integer, Player> players = new HashMap<>(); 
    
    // A separate list to maintain the order of play.
    private List<Integer> playerTurnOrder = new ArrayList<>();
    private int pot;
    private int currentBet;
    private int dealerIndex;
    private int currentPlayerIndex;
    private int ActiveCount; // Count of Active players
    private boolean GamePlay; // Flag to indicate if the game is over

    
}
