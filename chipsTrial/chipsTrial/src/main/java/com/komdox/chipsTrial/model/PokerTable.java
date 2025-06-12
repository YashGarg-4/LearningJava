package com.komdox.chipsTrial.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// import java.util.ArrayList;
// import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PokerTable {

    @Id
    private int id = 1; // Unique identifier for the table
    private int dealerIndex = 0; // Index of the dealer in the players list
}
