package com.komdox.chipsTrial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.komdox.chipsTrial.model.Move;
import com.komdox.chipsTrial.model.Table;
import com.komdox.chipsTrial.services.GameLogic;


@Controller
public class GameController {

    @Autowired
    GameLogic service;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/game.init")
    public void handleGameInit() {
        service.gameInit();
        Table currentTable = service.getCurrentTable(); // Assuming you have a method to get the current table
        messagingTemplate.convertAndSend("/topic/table/", currentTable);
    }

    @MessageMapping("/game.move")
    public void handleMove(Move move){

        Table updatedTable = service.processMove(move);

        messagingTemplate.convertAndSend("/topic/table/", updatedTable);

    }


}
