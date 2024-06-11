package org.example.capstone_backend.controllers;

import jakarta.validation.Valid;
import org.example.capstone_backend.models.LoginRequest;
import org.example.capstone_backend.models.SignUpRequest;
import org.example.capstone_backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //CREATE
    @PostMapping("/users/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            Map<String, Object> response = new HashMap<>();
            response.put("authenticated", false);
            response.put("userID", null);
            response.put("message", errorMessage.toString());
            return ResponseEntity.badRequest().body(response);
        }

        Map<String, Object> authResponse = authService.authenticate(loginRequest);
        if ((boolean) authResponse.get("authenticated")) {
            return ResponseEntity.ok(authResponse);
        } else {
            authResponse.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
        }
    }





    @PostMapping("/users/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                // If there are validation errors, return BAD_REQUEST with error messages
                StringBuilder errorMessage = new StringBuilder();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMessage.append(error.getDefaultMessage()).append("; ");
                }
                return ResponseEntity.badRequest().body(errorMessage.toString());
            }

            if (authService.isEmailAvailable(signUpRequest.getEmail())) {
                authService.signUp(signUpRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully. Now please log in");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use");
            }
        } catch (Exception e) {
            // Handle any unexpected exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}

