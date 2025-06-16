package com.komdox.chipsTrial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.komdox.chipsTrial.model.Game;
import com.komdox.chipsTrial.model.Round;
import com.komdox.chipsTrial.repo.GameRepo;
import com.komdox.chipsTrial.repo.RoundRepo;

// import ch.qos.logback.core.status.Status; // Remove this import and use your own Status class
import com.komdox.chipsTrial.model.Status;

@Service
public class StatusService {

    @Autowired
    private RoundRepo roundRepo;
    @Autowired
    private GameRepo gameRepo;

    public Status getStatus() {
        System.out.println("Getting status...");

        Round round = roundRepo.findById(1).get();
        System.out.println(round);

        Game game = gameRepo.findById(1).get();
        System.out.println(game);

        Status status = new Status();
        status.setRound(round);
        status.setGame(game);
        System.out.println(status);
        
        return status;
    }

}
