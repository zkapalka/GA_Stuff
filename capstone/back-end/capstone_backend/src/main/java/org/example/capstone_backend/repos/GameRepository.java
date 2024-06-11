package org.example.capstone_backend.repos;

import org.example.capstone_backend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // You can add custom query methods if needed
}

