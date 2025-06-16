package com.komdox.chipsTrial.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.komdox.chipsTrial.repo.RoundRepo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Move {

    @Autowired
    private RoundRepo roundRepo; // Repository to access round data

    private String action; // Action taken by the player (e.g., "fold", "call", "raise")
    private int betAmount = 0;//roundRepo.findById(1).get().getCurrentBet(); // Amount involved in the move, if applicable (e.g., bet or raise amount)
    private int playerId; // ID of the player making the move

}
