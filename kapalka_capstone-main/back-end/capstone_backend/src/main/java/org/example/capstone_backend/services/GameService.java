package org.example.capstone_backend.services;

import org.example.capstone_backend.models.Game;
import org.example.capstone_backend.repos.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    //READ
    public ResponseEntity<List<Game>> findAll() {
        try {
            List<Game> games = gameRepository.findAll();
            return ResponseEntity.ok(games);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Game> findById(Long id) {
        try {
            Optional<Game> game = gameRepository.findById(id);
            return game.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //UPDATE
    public ResponseEntity<Game> save(Game game) {
        try {
            Game savedGame = gameRepository.save(game);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //DELETE
    public ResponseEntity<Void> deleteById(Long id) {
        try {
            gameRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}