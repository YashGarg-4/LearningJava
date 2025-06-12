package com.komdox.chipsTrial.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Game {

    @Id
    private int id = 1; // Unique identifier for the game
    //private List<Integer> activePlayersId; //Can use the repo for this
    private int stage;
    private int pot; // Maps player ID to their bet amount

    @ElementCollection
    private List<Player> activePlayers;
}

