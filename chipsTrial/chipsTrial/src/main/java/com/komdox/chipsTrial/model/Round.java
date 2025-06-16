package com.komdox.chipsTrial.model;

import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Round {

    @Id
    private int id=1; // Unique identifier for the round // Default value set to 1 as we want singeleton Round enttry in the table
    private boolean dealearPlayed;
    private int currentPlayerIndex;
    private int currentBet = 1;
    @ElementCollection
    private Map<Integer, Integer> bets; 

    public Map<Integer, Integer> getBets() {
        return bets;
    }

    public boolean getDealerPlayed() {
        return dealearPlayed;
    }

}
