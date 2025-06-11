package com.komdox.chipsTrial.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    //private List<Integer> activePlayersId; //Can use the repo for this
    private int stage;
    private int pot;
    private Map<Player, Integer> bets; // Maps player ID to their bet amount
    
    // @Override
    public void setBets(List<Player> players, int defaultValue) {
    Map<Player, Integer> bets = new HashMap<>();
    for (Player player : players) {
        bets.put(player, defaultValue);
    }
    this.bets = bets;
}

}
