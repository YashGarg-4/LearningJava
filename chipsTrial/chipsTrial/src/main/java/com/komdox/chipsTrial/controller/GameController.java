package com.komdox.chipsTrial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Move;
import com.komdox.chipsTrial.repo.GameRepo;
import com.komdox.chipsTrial.services.GameService;


@Controller
public class GameController {
    @Autowired
    private GameRepo gameRepo; // H2 persistence [3][6]
    @Autowired
    private GameService gameService;
    // private final SimpMessagingTemplate wsTemplate = null;

    // Handle player actions (bet/fold/etc)
    @MessageMapping("/move")
    public void handleMove(@Payload Move move) {
        gameService.processMove(move);
    }
    
    // Current game state
    @GetMapping("/status")
    public ResponseEntity<Object> gameStatus() {
        return ResponseEntity.ok(gameRepo.findById(1)
                .orElseThrow(() -> new IllegalArgumentException("Game not found")));
    }
}


