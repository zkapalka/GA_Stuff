package org.example.leaguemanagerapplab.repos;

import org.example.leaguemanagerapplab.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPlayerRepo extends JpaRepository <Player, Integer> {
    //Basic CRUD operations are enabled via jparepo
}
