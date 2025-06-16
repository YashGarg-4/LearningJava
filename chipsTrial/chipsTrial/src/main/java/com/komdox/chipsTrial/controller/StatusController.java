package com.komdox.chipsTrial.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.komdox.chipsTrial.services.StatusService;
import com.komdox.chipsTrial.model.Status;

// import ch.qos.logback.core.status.Status; // Removed unused/incorrect import


@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/")
    public Status getStatus(){
        return statusService.getStatus();
    }
    
    
}
