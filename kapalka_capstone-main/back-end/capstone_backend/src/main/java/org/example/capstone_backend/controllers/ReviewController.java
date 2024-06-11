package org.example.capstone_backend.controllers;

import org.example.capstone_backend.exceptions.UserNotFoundException;
import org.example.capstone_backend.models.Review;
import org.example.capstone_backend.models.ReviewRequestDTO;
import org.example.capstone_backend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    //CREATE

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        try {
            Review review = reviewService.saveReviewWithUserAndGame(reviewRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(review);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // I commented this out on purpose. This is meant for testing purpose only.

//    @PostMapping
//    public ResponseEntity<Review> createReview(@RequestBody Review review) {
//        try {
//            return reviewService.save(review);
//        } catch (DataAccessException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }

//    @PostMapping("/users/{userID}/reviews")
//    public ResponseEntity<Review> saveReviewWithUser(
//            @PathVariable Long userID,
//            @RequestBody Review review) {
//        try {
//            ResponseEntity<Review> response = reviewService.saveReviewWithUser(userID, review);
//            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
//        } catch (DataAccessException e) {
//            // Handle database access exception
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    //READ
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        try {
            return reviewService.findAll();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        try {
            return reviewService.findById(id);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<?> getReviewsByUser(@PathVariable Long userID) {
        try {
            List<Review> reviews = reviewService.getReviewsByUser(userID);
            return ResponseEntity.ok(reviews);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<List<Review>> getAllReviewsForGame(@PathVariable Long gameId) {
        try {
            List<Review> reviews = reviewService.getAllReviewsForGame(gameId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/high-rated")
    public ResponseEntity<?> getHighRatedReviews() {
        try {
            List<Review> reviews = reviewService.getHighRatedReviews();
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            // Log the exception (using a logging framework like SLF4J)
            System.err.println("Error in getHighRatedReviews: " + e.getMessage());
            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching high-rated reviews.");
        }
    }


    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        try {
            Review existingReview = reviewService.findById(id).getBody();
            if (existingReview != null) {
                review.setReviewID(id); // Ensure the ID in the request body matches the ID in the path
                return ResponseEntity.ok(reviewService.save(review).getBody());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        try {
            return reviewService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

