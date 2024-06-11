package org.example.capstone_backend.services;

import org.example.capstone_backend.exceptions.GameNotFoundException;
import org.example.capstone_backend.exceptions.UserNotFoundException;
import org.example.capstone_backend.models.*;
import org.example.capstone_backend.repos.AccessibilityOptionRepository;
import org.example.capstone_backend.repos.GameRepository;
import org.example.capstone_backend.repos.ReviewRepository;
import org.example.capstone_backend.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    private final AccessibilityOptionRepository accessibilityOptionRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, GameRepository gameRepository, AccessibilityOptionRepository accessibilityOptionRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.accessibilityOptionRepository = accessibilityOptionRepository;
    }

    //CREATE

    //This pulls userID and tie accessiblity options to review model and then submit together
    public Review saveReviewWithUserAndGame(ReviewRequestDTO reviewRequestDTO) {
        try {
            User user = userRepository.findById(reviewRequestDTO.getUserId())
                    .orElseThrow(() -> new NoSuchElementException("User not found"));

            Review review = new Review();
            review.setRating(reviewRequestDTO.getRating());
            review.setComment(reviewRequestDTO.getComment());
            review.setUser(user);
            review.setGameName(reviewRequestDTO.getGameName());

            AccessibilityOptions options = new AccessibilityOptions();
            options.setHasSubtitles(reviewRequestDTO.getAccessibilityOptions().isHasSubtitles());
            options.setDepictsSoundEffectsInCaptions(reviewRequestDTO.getAccessibilityOptions().isDepictsSoundEffectsInCaptions());
            options.setHasVisualCues(reviewRequestDTO.getAccessibilityOptions().isHasVisualCues());
            options.setHasCleanUI(reviewRequestDTO.getAccessibilityOptions().isHasCleanUI());
            options.setHasSkippableAudioMiniGames(reviewRequestDTO.getAccessibilityOptions().isHasSkippableAudioMiniGames());

            // Save the AccessibilityOptions entity
//            accessibilityOptionRepository.save(options);

            // Associate the saved AccessibilityOptions with the review
            AccessibilityOptions savedOptions = accessibilityOptionRepository.save(options);
            savedOptions.setReview(review);
            review.setAccessibilityOptions(savedOptions);

            // Save the Review entity
            return reviewRepository.save(review);
        } catch (NoSuchElementException e) {
            // Handle case where user or game is not found
            throw new RuntimeException("User or Game not found", e);
        } catch (DataAccessException e) {
            // Handle database access issues
            throw new RuntimeException("Database error occurred", e);
        } catch (Exception e) {
            // Handle other exceptions
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }


    //READ
    public ResponseEntity<List<Review>> findAll() {
        try {
            List<Review> reviews = reviewRepository.findAll();
            return ResponseEntity.ok(reviews);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Review> findById(Long id) {
        try {
            Optional<Review> review = reviewRepository.findById(id);
            return review.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public List<Review> getReviewsByUser(Long userId) {
        try {
            return userRepository.findById(userId)
                    .map(reviewRepository::findByUser)
                    .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching reviews for user with ID " + userId, e);
        }
    }

    public List<Review> getAllReviewsForGame(Long gameId) throws GameNotFoundException {
        try {
            return gameRepository.findById(gameId)
                    .map(reviewRepository:: findByGame)
                    .orElseThrow(() -> new GameNotFoundException("Game with ID " + gameId + " not found"));
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching reviews for game with ID " + gameId, e);
        }
    }

    //Get all reviews with ratings over 4
    public List<Review> getHighRatedReviews() {
        try {
            return reviewRepository.findHighRatedReviews();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching highly-rated reviews " + e);
        }
    }


    //UPDATE
    public ResponseEntity<Review> save(Review review) {
        try {
            Review savedReview = reviewRepository.save(review);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //DELETE
    public ResponseEntity<Void> deleteById(Long id) {
        try {
            reviewRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}