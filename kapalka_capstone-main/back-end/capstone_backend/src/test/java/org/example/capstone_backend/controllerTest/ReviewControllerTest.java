package org.example.capstone_backend.controllerTest;

import org.example.capstone_backend.controllers.ReviewController;
import org.example.capstone_backend.models.Review;
import org.example.capstone_backend.models.ReviewRequestDTO;
import org.example.capstone_backend.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private Review testReview;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        testReview = new Review();
        testReview.setReviewID(1L);
        testReview.setRating(5);
        testReview.setComment("Test comment");
    }

    @Test
    void createReview() {
        // Mock service method
        when(reviewService.saveReviewWithUserAndGame(any())).thenReturn(testReview);

        // Perform controller action
        ResponseEntity<Review> response = reviewController.createReview(new ReviewRequestDTO());

        // Verify service method was called
        verify(reviewService, times(1)).saveReviewWithUserAndGame(any());

        // Verify response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testReview, response.getBody());
    }

    @Test
    void getAllReviews() {
        // Mock service method
        List<Review> reviews = new ArrayList<>();
        reviews.add(testReview);
        when(reviewService.findAll()).thenReturn(ResponseEntity.ok(reviews));

        // Perform controller action
        ResponseEntity<List<Review>> response = reviewController.getAllReviews();

        // Verify service method was called
        verify(reviewService, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(reviews, response.getBody());
    }

    @Test
    void getReviewById() {
        // Mock service method
        when(reviewService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testReview)));

        // Perform controller action
        ResponseEntity<Review> response = reviewController.getReviewById(1L);

        // Verify service method was called
        verify(reviewService, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testReview, response.getBody());
    }

    @Test
    void getAllReviewsForGame() {
        // Mock service method
        List<Review> reviews = new ArrayList<>();
        reviews.add(testReview);
        when(reviewService.getAllReviewsForGame(anyLong())).thenReturn(reviews);

        // Perform controller action
        ResponseEntity<List<Review>> response = reviewController.getAllReviewsForGame(1L);

        // Verify service method was called
        verify(reviewService, times(1)).getAllReviewsForGame(anyLong());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(reviews, response.getBody());
    }

    @Test
    void updateReview() {
        // Mock service method
        when(reviewService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testReview)));
        when(reviewService.save(any())).thenReturn(ResponseEntity.ok(testReview));

        // Perform controller action
        ResponseEntity<Review> response = reviewController.updateReview(1L, testReview);

        // Verify service methods were called
        verify(reviewService, times(1)).findById(anyLong());
        verify(reviewService, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testReview, response.getBody());
    }

    @Test
    void deleteReview() {
        // Mock service method
        when(reviewService.deleteById(anyLong())).thenReturn(ResponseEntity.noContent().build());

        // Perform controller action
        ResponseEntity<Void> response = reviewController.deleteReview(1L);

        // Verify service method was called
        verify(reviewService, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
