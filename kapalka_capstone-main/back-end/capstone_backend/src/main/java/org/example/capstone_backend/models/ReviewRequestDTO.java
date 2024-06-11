package org.example.capstone_backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {

    //This is a DTO

    private int rating;
    private String comment;
    private Long userId;
    private String gameName;
    private AccessibilityOptions accessibilityOptions;

//    public static class AccessibilityOptions {
//        private boolean hasSubtitles;
//        private boolean depictsSoundEffectsInCaptions;
//        private boolean hasVisualCues;
//        private boolean hasCleanUI;
//        private boolean hasSkippableAudioMiniGames;
//
//        // Constructors, getters, and setters for each property
////
//        public boolean hasSubtitles() {
//            return hasSubtitles;
//        }
//
//        public boolean depictsSoundEffectsInCaptions() {
//            return depictsSoundEffectsInCaptions;
//        }
//
//        public boolean hasVisualCues() {
//            return hasVisualCues;
//        }
//
//        public boolean hasCleanUI() {
//            return hasCleanUI;
//        }
//
//        public boolean hasSkippableAudioMiniGames() {
//            return hasSkippableAudioMiniGames;
//        }
//    }
}

