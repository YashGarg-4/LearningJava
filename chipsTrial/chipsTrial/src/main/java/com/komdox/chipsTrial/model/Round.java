package com.komdox.chipsTrial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    private int currentBet;
    private int currentPlayerIndex;
    private boolean dealerPlayed;

}
