package org.example.leaguemanagerapplab.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int teamID;
    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;

    public void setTourney() {
    }
}
