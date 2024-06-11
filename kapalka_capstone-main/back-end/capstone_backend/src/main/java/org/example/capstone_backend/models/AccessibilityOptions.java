package org.example.capstone_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AccessibilityOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionsID;
    private boolean hasSubtitles;
    private boolean depictsSoundEffectsInCaptions;
    private boolean hasVisualCues;
    private boolean hasCleanUI;
    private boolean hasSkippableAudioMiniGames;

    //Relationship
    @ManyToMany(mappedBy = "accessibilityOptions")
    @JsonIgnore
    private Set<Game> games = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "review_id")
    @JsonIgnore
    private Review review;
}

