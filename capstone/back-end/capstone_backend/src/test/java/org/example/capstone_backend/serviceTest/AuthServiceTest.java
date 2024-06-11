package org.example.capstone_backend.serviceTest;

import org.example.capstone_backend.models.LoginRequest;
import org.example.capstone_backend.models.SignUpRequest;
import org.example.capstone_backend.models.User;
import org.example.capstone_backend.repos.UserRepository;
import org.example.capstone_backend.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void authenticate_ValidCredentials_ReturnsAuthenticatedTrue() {
        // Mock repository to return a user with matching credentials
        User mockUser = new User();
        mockUser.setPassword("password123"); // Assuming this is the correct password
        mockUser.setUserID(1L); // Set a user ID for the mock user
        when(userRepository.findByEmail(anyString())).thenReturn(mockUser);

        // Perform authentication
        LoginRequest loginRequest = new LoginRequest("username", "password123"); // Provide correct password
        Map<String, Object> response = authService.authenticate(loginRequest);

        // Verify response
        assertTrue((Boolean) response.get("authenticated")); // Expecting true for valid credentials
        assertEquals(1L, response.get("userID")); // Check if the user ID is correctly returned
        assertEquals("Login successful for user with email: username", response.get("message"));
    }


    @Test
    public void authenticate_InvalidCredentials_ReturnsAuthenticatedFalse() {
        // Mock repository to return null, indicating no user found with provided username
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        // Perform authentication with invalid credentials
        LoginRequest loginRequest = new LoginRequest("username", "password123");
        Map<String, Object> response = authService.authenticate(loginRequest);

        // Verify response
        assertFalse((Boolean) response.get("authenticated")); // Expecting false for invalid credentials
        assertNull(response.get("userID")); // Expecting null for user ID
        assertEquals("Invalid credentials", response.get("message"));
    }


    @Test
    public void authenticate_ExceptionThrown_ReturnsErrorMessage() {
        // Mock repository to throw a DataAccessException
        when(userRepository.findByEmail(anyString())).thenThrow(new DataAccessException("Test Exception") {});

        // Perform authentication (which would cause an exception)
        LoginRequest loginRequest = new LoginRequest("username", "password123");
        Map<String, Object> response = authService.authenticate(loginRequest);

        // Verify response
        assertFalse((Boolean) response.get("authenticated")); // Expecting false due to exception
        assertNull(response.get("userID")); // Expecting null for user ID
        assertEquals("An unexpected error occurred", response.get("message"));
    }





}

