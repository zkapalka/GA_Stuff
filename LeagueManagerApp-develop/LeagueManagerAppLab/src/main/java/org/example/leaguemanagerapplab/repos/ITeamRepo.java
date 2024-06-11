package org.example.leaguemanagerapplab.repos;

import org.example.leaguemanagerapplab.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeamRepo extends JpaRepository <Team, Integer> {
    //Basic CRUD operations already enabled
}
