package org.example.leaguemanagerapplab.service;

import org.example.leaguemanagerapplab.models.Player;
import org.example.leaguemanagerapplab.models.Team;
import org.example.leaguemanagerapplab.repos.IPlayerRepo;
import org.example.leaguemanagerapplab.repos.ITeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeamService {

    @Autowired
    ITeamRepo teamRepo;

    @Autowired
    IPlayerRepo playerRepo;



    //READ

    //retrieve a team by id
    public Team getTeamById(Integer id) throws Exception {
        return teamRepo.findById(id).orElseThrow(() -> new Exception("Team with id " + id + " not found"));
    }

    //CREATE

    //create a new team
    public Team addTeam(Team team) {
        return teamRepo.save(team);
    }

    //add new player to team
    public Team addPlayerToTeam(Integer id, Player player) throws Exception {
        Team team = teamRepo.findById(id).orElseThrow(() -> new Exception("Team with id " + id + " not found"));
        player.setTeam(team);
        playerRepo.save(player);

        return team;
    }

    //UPDATE

    //update a team's details
    public Team updateTeam(int id, Team team) throws Exception {
        Team oldTeam = teamRepo.findById(id).orElse(null);
        if(oldTeam == null) {
            throw new Exception("Team with id " + id + " not found");
        }
        oldTeam.setTeamName(team.getTeamName());
        oldTeam.setPlayers(team.getPlayers());

        return teamRepo.save(oldTeam);
    }

    //DELETE

    //delete a team
    public void deleteTeam(int id) {
        teamRepo.deleteById(id);
    }

    //delete a player from a team
    public void deletePlayerFromTeam(Integer id, Integer playerId) throws Exception {
        Team team = teamRepo.findById(id).orElseThrow(() -> new Exception("Team with id " + id + " not found"));
        team.getPlayers().remove(playerId);
    }
}
