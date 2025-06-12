package com.komdox.chipsTrial.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.komdox.chipsTrial.model.PokerTable;

@Repository
public interface TableRepo extends JpaRepository<PokerTable, Integer> {
    // This interface can be used to define custom query methods for Table entities.
    // For example, you can add methods to find tables by game or player.
    
    // Example:
    // List<Table> findByGame(Game game);
    // List<Table> findByPlayer(Player player);

}
