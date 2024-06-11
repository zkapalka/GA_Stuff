package org.example.capstone_backend.controllers;

import org.example.capstone_backend.models.Game;
import org.example.capstone_backend.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        try {
            return gameService.save(game);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        try {
            return gameService.findAll();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        try {
            return gameService.findById(id);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        try {
            Game existingGame = gameService.findById(id).getBody();
            if (existingGame != null) {
                game.setGameID(id); // Ensure the ID in the request body matches the ID in the path
                return ResponseEntity.ok(gameService.save(game).getBody());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        try {
            return gameService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
