package org.example.capstone_backend.services;

import org.example.capstone_backend.models.LoginRequest;
import org.example.capstone_backend.models.SignUpRequest;
import org.example.capstone_backend.models.User;
import org.example.capstone_backend.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //CREATE

    //This auth returns an userID so that React can store the userID in localstorage and then use it as piped text as needed
    public Map<String, Object> authenticate(LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword().trim(); // Trim whitespace

            User user = userRepository.findByEmail(email);
            if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
                response.put("authenticated", true);
                response.put("userID", user.getUserID());
                response.put("message", "Login successful for user with email: " + email);
            } else {
                response.put("authenticated", false);
                response.put("userID", null);
                response.put("message", "Invalid credentials");
            }
        } catch (DataAccessException e) {
            response.put("authenticated", false);
            response.put("userID", null);
            response.put("message", "An unexpected error occurred");
        }
        return response;
    }

    public ResponseEntity<User> signUp(SignUpRequest signUpRequest) {
        try {
            if (isEmailAvailable(signUpRequest.getEmail())) {
                User newUser = new User();
                newUser.setFirstName(signUpRequest.getFirstName());
                newUser.setLastName(signUpRequest.getLastName());
                newUser.setEmail(signUpRequest.getEmail());
                newUser.setPassword(signUpRequest.getPassword());
                newUser.setBirthDate(signUpRequest.getBirthDate());

                // Save the new user to the repository
                User savedUser = userRepository.save(newUser);

                // Return the created user with HTTP status CREATED
                return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
            } else {
                // Return BAD_REQUEST status if email is not available
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (DataAccessException e) {
            // Return INTERNAL_SERVER_ERROR status if there's a data access exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //READ
    public boolean isEmailAvailable(String email) {
        try {
            User user = userRepository.findByEmail(email);
            return user == null;
        } catch (DataAccessException e) {
            // Log the exception or handle it accordingly
            return false; // Return false indicating email availability cannot be determined due to error
        }
    }

}

