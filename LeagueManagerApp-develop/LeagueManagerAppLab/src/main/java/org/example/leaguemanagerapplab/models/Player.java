package org.example.leaguemanagerapplab.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int playerID;
    private String playerName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teamID")
    private Team team;
}
