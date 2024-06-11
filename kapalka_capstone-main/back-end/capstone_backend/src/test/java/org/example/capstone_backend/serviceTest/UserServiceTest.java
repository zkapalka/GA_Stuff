package org.example.capstone_backend.serviceTest;

import org.example.capstone_backend.models.User;
import org.example.capstone_backend.repos.UserRepository;
import org.example.capstone_backend.services.UserService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_Success_ReturnsListOfUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        // Act
        ResponseEntity<List<User>> response = userService.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void findAll_DataAccessException_ReturnsInternalServerError() {
        // Arrange
        when(userRepository.findAll()).thenThrow(new DataAccessException("Test Exception") {});


        // Act
        ResponseEntity<List<User>> response = userService.findAll();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findById_Success_ReturnsUser() {
        // Arrange
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<User> response = userService.findById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void findById_UserNotFound_ReturnsNotFound() {
        // Arrange
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<User> response = userService.findById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findById_DataAccessException_ReturnsInternalServerError() {
        // Arrange
        when(userRepository.findById(anyLong())).thenThrow(new DataAccessException("Test Exception") {});

        // Act
        ResponseEntity<User> response = userService.findById(1L);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void save_Success_ReturnsCreated() {
        // Arrange
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        ResponseEntity<User> response = userService.save(user);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void save_DataAccessException_ReturnsBadRequest() {
        // Arrange
        when(userRepository.save(any(User.class))).thenThrow(new DataAccessException("Test Exception") {});

        // Act
        ResponseEntity<User> response = userService.save(new User());

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteById_Success_ReturnsNoContent() {
        // Arrange
        doNothing().when(userRepository).deleteById(anyLong());

        // Act
        ResponseEntity<Void> response = userService.deleteById(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_UserNotFound_ReturnsNotFound() {
        // Arrange
        doThrow(EmptyResultDataAccessException.class).when(userRepository).deleteById(anyLong());

        // Act
        ResponseEntity<Void> response = userService.deleteById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteById_DataAccessException_ReturnsInternalServerError() {
        // Arrange
        doThrow(new DataAccessException("Test Exception") {}).when(userRepository).deleteById(anyLong());

        // Act
        ResponseEntity<Void> response = userService.deleteById(1L);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // Add more test methods as needed
}

