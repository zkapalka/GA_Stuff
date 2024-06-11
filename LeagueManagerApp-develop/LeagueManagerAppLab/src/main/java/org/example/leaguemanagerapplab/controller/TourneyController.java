package org.example.leaguemanagerapplab.controller;

import org.example.leaguemanagerapplab.models.Player;
import org.example.leaguemanagerapplab.models.Team;
import org.example.leaguemanagerapplab.models.Tourney;
import org.example.leaguemanagerapplab.service.TourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TourneyController {
    private final TourneyService tourneyService;

    @Autowired
    public TourneyController(TourneyService tourneyService) {
        this.tourneyService = tourneyService;
    }

    //READ

    //retrieve a tourney by id
    @GetMapping("/tourneys/{id}")
    public ResponseEntity<Tourney> getTourneyByID(@PathVariable Integer id) {
        Optional<Tourney> tourney = tourneyService.findByTourneyId(id);
        return tourney.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //get all teams in a tournament
    @GetMapping("/tourneys/{id}/teams")
    public List<Team> getTourneyTeams(@PathVariable Integer id) throws Exception {
        return tourneyService.getTeamsByTourneyID(id);
    }

    //todo get all players in a tournament
    @GetMapping("/tourneys/{id}/players")
    public List<Player> getTourneyPlayers(@PathVariable Integer id) throws Exception {
        return tourneyService.getPlayersByTourneyID(id);
    }



    //CREATE

    //create a new tourney
    @PostMapping("/tourneys")
    public Tourney addTourney(@RequestBody Tourney tourney) {
        Tourney newTourney = tourneyService.addTourney(tourney);
        return newTourney;
    }

    //add team to tourney
    @PostMapping("/tourneys/{id}/teams")
    public Tourney addTeamToTourney(@PathVariable Integer id, @RequestBody Team team) throws Exception {
        Tourney tourney = tourneyService.addTeamToTourney(id, team);
        return tourney;
    }


    //UPDATE

    //update a tourney's details
    @PutMapping("/tourneys/{id}")
    public Tourney updateTourney(@PathVariable int id, @RequestBody Tourney tourney) throws Exception {
        Tourney updatedTourney = tourneyService.updateTourney(id, tourney);

        return updatedTourney;
    }

    //DELETE

    //delete a tourney
    @DeleteMapping("/tourneys/{id}")
    public void deleteTourney(@PathVariable int id) {
        tourneyService.deleteTourney(id);
    }

    //delete a team from a tournament
    @DeleteMapping("/tourneys/{id}/teams/{teamId}")
    public void deleteTeamFromTourney(@PathVariable Integer id, @PathVariable Integer teamId) throws Exception {
        tourneyService.deleteTeamFromTourney(id, teamId);
    }
}
