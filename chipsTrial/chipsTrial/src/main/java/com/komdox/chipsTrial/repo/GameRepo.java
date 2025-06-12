package com.komdox.chipsTrial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.komdox.chipsTrial.model.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {

    // This interface can be used to define custom query methods for Game entities.
    // For example, you can add methods to find games by status or player.
    
    // Example:
    // List<Game> findByStatus(String status);
    // List<Game> findByPlayer(Player player);

}
