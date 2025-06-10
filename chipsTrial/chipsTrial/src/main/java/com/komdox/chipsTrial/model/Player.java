package com.komdox.chipsTrial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int purse = 100;
    public String status = "active"; // "active", "folded", etc.

}

