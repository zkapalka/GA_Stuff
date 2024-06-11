package org.example.leaguemanagerapplab.controller;

import org.example.leaguemanagerapplab.models.Player;
import org.example.leaguemanagerapplab.models.Team;
import org.example.leaguemanagerapplab.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TeamController {
    @Autowired
    TeamService teamService;

    //READ

    //retrieve a team by id
    @GetMapping("/teams/{id}")
    public Team getTeamById(@PathVariable Integer id) throws Exception {
        return teamService.getTeamById(id);
    }

    //CREATE

    //create a new team
    @PostMapping("/teams")
    public Team addTeam(@RequestBody Team team) {
        Team newTeam = teamService.addTeam(team);
        return newTeam;
    }

    //add new player to team
    @PostMapping("/teams/{id}/players")
    public Team addPlayerToTeam(@PathVariable Integer id, @RequestBody Player player) throws Exception {
        Team team = teamService.addPlayerToTeam(id, player);
        return team;
    }

    //UPDATE

    //update a team's details
    @PutMapping("/teams/{id}")
    public Team updateTeam(@PathVariable int id, @RequestBody Team team) throws Exception {
        Team updatedTeam = teamService.updateTeam(id, team);

        return updatedTeam;
    }

    //DELETE

    //delete a team
    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable int id){
        teamService.deleteTeam(id);
    }
    
    //delete a player from a team
    @DeleteMapping("/teams/{id}/players/{playerId}")
    public void deletePlayerFromTeam(@PathVariable Integer id, @PathVariable Integer playerId) throws Exception {
        teamService.deletePlayerFromTeam(id, playerId);
    }

}
