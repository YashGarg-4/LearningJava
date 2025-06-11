package com.komdox.chipsTrial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Move;
import com.komdox.chipsTrial.services.GameService;


@Controller
public class GameController {
    private final GameService gameService = new GameService();
    private final SimpMessagingTemplate wsTemplate = null;

    // Handle player actions (bet/fold/etc)
    @MessageMapping("/move")
    public void handleMove(@Payload Move move) {
        Game updatedGame = gameService.processMove(move);
        wsTemplate.convertAndSend("/topic/game", updatedGame);
    }
    
    // Current game state
    @GetMapping("/status")
    public ResponseEntity<Object> gameStatus() {
        return ResponseEntity.ok(gameService.getCurrentGame());
    }
}


