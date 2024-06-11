package org.example.leaguemanagerapplab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tourney {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int tourneyID;
    private String tourneyName;

    @ManyToMany
    @JoinTable(
            name = "tournament_teams",
            joinColumns = @JoinColumn(name = "tourneyID"),
            inverseJoinColumns = @JoinColumn(name = "teamID")
    )
    List<Team> teams = new ArrayList<>();

    public List<Player> getPlayers() {
        return null;
    }
}
