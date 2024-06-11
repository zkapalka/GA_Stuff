package org.example.leaguemanagerapplab.service;

import org.example.leaguemanagerapplab.models.Player;
import org.example.leaguemanagerapplab.models.Team;
import org.example.leaguemanagerapplab.models.Tourney;
import org.example.leaguemanagerapplab.repos.ITeamRepo;
import org.example.leaguemanagerapplab.repos.ITourneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourneyService {
    @Autowired
    ITourneyRepo tourneyRepo;

    @Autowired
    ITeamRepo teamRepo;

    //READ

    //retrieve a tourney by id
    public Optional<Tourney> findByTourneyId(Integer id) {
        return tourneyRepo.findById(id);
    }
    
    //get all teams in a tournament
    public List<Team> getTeamsByTourneyID(Integer id) throws Exception {
        Tourney tourney = tourneyRepo.findById(id).orElseThrow(() -> new Exception("Tourney with id " + id + " not found"));
        return tourney.getTeams();
    }

    //get all players in a tournament
    public List<Player> getPlayersByTourneyID(Integer id) throws Exception {
        Tourney tourney = tourneyRepo.findById(id).orElseThrow(() -> new Exception ("Tournament with id " + id + " not found"));
        return tourney.getPlayers();
    }


    //CREATE

    //create a new tourney
    public Tourney addTourney(Tourney tourney) {
        return tourneyRepo.save(tourney);
    }

    //add team to tourney
    public Tourney addTeamToTourney(Integer id, Team team) throws Exception {
        Tourney tourney = tourneyRepo.findById(id).orElseThrow(() -> new Exception("Tourney not found"));
        team.setTourney();
        teamRepo.save(team);

        return tourney;
    }

    //UPDATE

    //update a tourney's details
    public Tourney updateTourney(int id, Tourney tourney) throws Exception {
        Tourney oldTourney = tourneyRepo.findById(id).orElse(null);

        if(oldTourney == null) {
            throw new Exception("Tourney with id " + id + " not found");
        }
        oldTourney.setTourneyName(tourney.getTourneyName());
        oldTourney.setTeams(tourney.getTeams());

        return tourneyRepo.save(oldTourney);
    }

    //DELETE

    //delete a tourney
    public void deleteTourney(int id) {
        tourneyRepo.deleteById(id);
    }

    //delete a team from a tournament
    public void deleteTeamFromTourney(Integer id, Integer teamId) throws Exception {
        Tourney tourney = tourneyRepo.findById(id).orElseThrow(() -> new Exception("Tourney with id " + id + " not found"));
        tourney.getTeams().remove(teamId);
    }
}
