package com.komdox.chipsTrial.repo;

import org.springframework.stereotype.Repository;

import com.komdox.chipsTrial.model.Player;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {

    // This class can be used to define custom query methods if needed.
    // For example, you can add methods to find players by name or status.
    
    // Example:
    // List<Player> findByName(String name);
    // List<Player> findByStatus(boolean status);

}
