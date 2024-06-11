package org.example.capstone_backend.controllerTest;

import org.example.capstone_backend.controllers.AuthController;
import org.example.capstone_backend.models.LoginRequest;
import org.example.capstone_backend.models.SignUpRequest;
import org.example.capstone_backend.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_Successful() {
        // Mock the successful authentication response
        Map<String, Object> authResponse = new HashMap<>();
        authResponse.put("authenticated", true);
        authResponse.put("userID", 1L); // Example user ID
        authResponse.put("message", "Login successful for user with email: username");

        // Mock the authService.authenticate method to return the successful response
        when(authService.authenticate(any())).thenReturn(authResponse);

        // Perform the controller action
        ResponseEntity<Map<String, Object>> response = authController.login(new LoginRequest("username", "password"), mock(BindingResult.class));

        // Verify that the authService.authenticate method was called
        verify(authService, times(1)).authenticate(any());

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(true, response.getBody().get("authenticated"));
        assertEquals(1L, response.getBody().get("userID"));
        assertEquals("Login successful for user with email: username", response.getBody().get("message"));
    }


    @Test
    void login_Unsuccessful() {
        // Mock the unsuccessful authentication response
        Map<String, Object> authResponse = new HashMap<>();
        authResponse.put("authenticated", false);
        authResponse.put("userID", null);
        authResponse.put("message", "Invalid credentials");

        // Mock the authService.authenticate method to return the unsuccessful response
        when(authService.authenticate(any())).thenReturn(authResponse);

        // Perform the controller action
        ResponseEntity<Map<String, Object>> response = authController.login(new LoginRequest("username", "password"), mock(BindingResult.class));

        // Verify that the authService.authenticate method was called
        verify(authService, times(1)).authenticate(any());

        // Verify the response
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(false, response.getBody().get("authenticated"));
        assertNull(response.getBody().get("userID"));
        assertEquals("Invalid credentials", response.getBody().get("message"));
    }

    @Test
    void login_WithBindingErrors() {
        // Create a mock BindingResult with errors
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        List<FieldError> fieldErrors = List.of(new FieldError("loginRequest", "username", "Username is required"));
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // Perform the controller action
        ResponseEntity<Map<String, Object>> response = authController.login(new LoginRequest("", "password"), bindingResult);

        // Verify that the authService.authenticate method was not called
        verify(authService, times(0)).authenticate(any());

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(false, response.getBody().get("authenticated"));
        assertNull(response.getBody().get("userID"));
        assertEquals("Username is required; ", response.getBody().get("message"));
    }


    @Test
    void signUp_EmailAvailable() {
        // Mock isEmailAvailable method to return true (email available)
        when(authService.isEmailAvailable(anyString())).thenReturn(true);

        // Create a SignUpRequest object with valid data
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testUser");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("test@example.com"); // Ensure a valid email address

        // Perform controller action
        ResponseEntity<String> response = authController.signUp(signUpRequest, mock(BindingResult.class));

        // Verify service method was called
        verify(authService, times(1)).isEmailAvailable(anyString());
        verify(authService, times(1)).signUp(any());

        // Verify response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("User registered successfully. Now please log in", response.getBody());
    }


    @Test
    void signUp_EmailNotAvailable() {
        // Mock isEmailAvailable method to return false (email not available)
        when(authService.isEmailAvailable(anyString())).thenReturn(false);

        // Create a SignUpRequest object with valid data
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("testUser");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("test@example.com"); // Ensure a valid email address

        // Perform controller action
        ResponseEntity<String> response = authController.signUp(signUpRequest, mock(BindingResult.class));

        // Verify service method was called
        verify(authService, times(1)).isEmailAvailable(anyString());
        verify(authService, never()).signUp(any());

        // Verify response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Email is already in use", response.getBody());
    }


    @Test
    void signUp_InvalidRequest() {
        // Create a SignUpRequest object with invalid data
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("");
        signUpRequest.setPassword("pass");

        // Perform controller action
        ResponseEntity<String> response = authController.signUp(signUpRequest, mock(BindingResult.class));

        // Verify that authService methods were not called
        verify(authService, never()).isEmailAvailable(anyString());
        verify(authService, never()).signUp(any());

        // Verify response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Email is already in use", response.getBody());
    }
}