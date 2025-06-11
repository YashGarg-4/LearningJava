package com.komdox.chipsTrial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Move {

    private String action; // Action taken by the player (e.g., "bet", "fold", "call", "raise")
    private int betAmount; // Amount involved in the move, if applicable (e.g., bet or raise amount)
    private int playerId; // ID of the player making the move

}
