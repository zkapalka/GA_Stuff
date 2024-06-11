package org.example.leaguemanagerapplab.repos;


import org.example.leaguemanagerapplab.models.Tourney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITourneyRepo extends JpaRepository <Tourney, Integer> {
}
