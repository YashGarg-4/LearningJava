package com.komdox.chipsTrial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.komdox.chipsTrial.model.Player;
import com.komdox.chipsTrial.services.TableService;

@RestController
@RequestMapping("/table")
public class TableController {
    
    private final TableService tableService = new TableService();
    
    // Join the game table
    @PostMapping("/join")
    public ResponseEntity<Player> joinTable(@RequestBody String playerName) {
        Player newPlayer = tableService.seatPlayer(playerName);
        return ResponseEntity.ok(newPlayer);
    }
    
    // Start new game
    @PostMapping("/start")
    public void startGame() {
        tableService.initializeGame();
    }

}

