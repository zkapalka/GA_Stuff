package org.example.capstone_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    private String gameName;

    //Ensures that rating of this will only be between 1 and 5
    @NotNull
    @Min(1)
    @Max(5)
    private Integer rating;
    private String comment;

    //Relationship
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnore
    private Game game;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private AccessibilityOptions accessibilityOptions;

}

