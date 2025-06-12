package com.komdox.chipsTrial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.komdox.chipsTrial.model.Round;

@Repository
public interface RoundRepo extends JpaRepository<Round, Integer> {
    // This interface can be used to define custom query methods for Round entities.
    // For example, you can add methods to find rounds by game or player.
    
    // Example:
    // List<Round> findByGame(Game game);
    // List<Round> findByPlayer(Player player);

}
