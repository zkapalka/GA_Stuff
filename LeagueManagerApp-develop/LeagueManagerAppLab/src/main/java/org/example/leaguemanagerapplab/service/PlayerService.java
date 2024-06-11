package org.example.leaguemanagerapplab.service;

import org.example.leaguemanagerapplab.models.Player;
import org.example.leaguemanagerapplab.repos.IPlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    private final IPlayerRepo playerRepo;

    @Autowired
    public PlayerService(IPlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    //READ

    //todo retrieve a player by id
    public Optional<Player> getPlayerById(Integer id) {
        return playerRepo.findById(id);
    }


    //CREATE

    //todo create a new player
    public Player createPlayer(Player player) {
        return playerRepo.save(player);
    }

    //UPDATE

    //todo update a player's details
    public Player updatePlayer(Player player) {
        return playerRepo.save(player);
    }


    //DELETE

    //todo delete a player
    public void deletePlayerById(Integer id) {
        playerRepo.deleteById(id);
    }
}
