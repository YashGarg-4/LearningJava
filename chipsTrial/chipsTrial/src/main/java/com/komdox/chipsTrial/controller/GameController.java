package com.komdox.chipsTrial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("/move")
    @ResponseBody
    public ResponseEntity<String> handleMoveRest(@RequestBody Move move) {
        try {
            gameService.processMove(move);
            return ResponseEntity.ok("Move processed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing move: " + e.getMessage());
        }
    }
    
}


