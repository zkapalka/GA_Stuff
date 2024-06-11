package org.example.capstone_backend.serviceTest;

import org.example.capstone_backend.models.Review;
import org.example.capstone_backend.repos.ReviewRepository;
import org.example.capstone_backend.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_Success_ReturnsListOfReviews() {
        // Mock repository method to return a list of reviews
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review());
        reviews.add(new Review());
        when(reviewRepository.findAll()).thenReturn(reviews);

        // Perform service action
        ResponseEntity<List<Review>> response = reviewService.findAll();

        // Verify repository method was called
        verify(reviewRepository, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void findAll_DataAccessException_ReturnsInternalServerError() {
        // Mock repository method to throw DataAccessException
        when(reviewRepository.findAll()).thenThrow(new DataAccessException("Test Exception") {});

        // Perform service action
        ResponseEntity<List<Review>> response = reviewService.findAll();

        // Verify repository method was called
        verify(reviewRepository, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findById_ExistingReview_ReturnsReview() {
        // Mock repository method to return an optional review
        Review mockReview = new Review();
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(mockReview));

        // Perform service action
        ResponseEntity<Review> response = reviewService.findById(1L);

        // Verify repository method was called
        verify(reviewRepository, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReview, response.getBody());
    }

    @Test
    void findById_NonExistingReview_ReturnsNotFound() {
        // Mock repository method to return an empty optional
        when(reviewRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Perform service action
        ResponseEntity<Review> response = reviewService.findById(1L);

        // Verify repository method was called
        verify(reviewRepository, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findById_DataAccessException_ReturnsInternalServerError() {
        // Mock repository method to throw DataAccessException
        when(reviewRepository.findById(anyLong())).thenThrow(new DataAccessException("Test Exception") {});

        // Perform service action
        ResponseEntity<Review> response = reviewService.findById(1L);

        // Verify repository method was called
        verify(reviewRepository, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void save_ValidReview_ReturnsCreated() {
        // Mock repository method to return the saved review
        Review mockReview = new Review();
        when(reviewRepository.save(any())).thenReturn(mockReview);

        // Perform service action
        ResponseEntity<Review> response = reviewService.save(new Review());

        // Verify repository method was called
        verify(reviewRepository, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockReview, response.getBody());
    }

    @Test
    void save_DataAccessException_ReturnsBadRequest() {
        // Mock repository method to throw DataAccessException
        when(reviewRepository.save(any())).thenThrow(new DataAccessException("Test Exception") {});

        // Perform service action
        ResponseEntity<Review> response = reviewService.save(new Review());

        // Verify repository method was called
        verify(reviewRepository, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteById_ExistingReview_ReturnsNoContent() {
        // Perform service action
        ResponseEntity<Void> response = reviewService.deleteById(1L);

        // Verify repository method was called
        verify(reviewRepository, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_NonExistingReview_ReturnsNotFound() {
        // Mock repository method to throw EmptyResultDataAccessException
        doThrow(EmptyResultDataAccessException.class).when(reviewRepository).deleteById(anyLong());

        // Perform service action
        ResponseEntity<Void> response = reviewService.deleteById(1L);

        // Verify repository method was called
        verify(reviewRepository, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteById_DataAccessException_ReturnsInternalServerError() {
        // Mock repository method to throw DataAccessException
        doThrow(new DataAccessException("Test Exception") {}).when(reviewRepository).deleteById(anyLong());

        // Perform service action
        ResponseEntity<Void> response = reviewService.deleteById(1L);

        // Verify repository method was called
        verify(reviewRepository, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // Add similar tests for findById, save, and deleteById methods...
}

