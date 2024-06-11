package org.example.leaguemanagerapplab.controller;

import org.example.leaguemanagerapplab.models.Player;
import org.example.leaguemanagerapplab.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    //This is a constructor injection and generally considered to be a better thing to use than field injection
    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //READ
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Integer id) {
        Optional<Player> player = playerService.getPlayerById(id);
        return player.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.createPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Integer id, @RequestBody Player player) {
        if (playerService.getPlayerById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        player.setPlayerID(id); // Ensure the ID is set for the update
        Player updatedPlayer = playerService.updatePlayer(player);
        return ResponseEntity.ok(updatedPlayer);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Integer id) {
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }
}
