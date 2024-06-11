package org.example.capstone_backend.controllerTest;

import org.example.capstone_backend.controllers.UserController;
import org.example.capstone_backend.models.User;
import org.example.capstone_backend.services.UserService;
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

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        testUser = new User();
        testUser.setUserID(1L);
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setEmail("john@example.com");
    }

    @Test
    void getAllUsers() {
        // Mock service method
        List<User> users = new ArrayList<>();
        users.add(testUser);
        when(userService.findAll()).thenReturn(ResponseEntity.ok(users));

        // Perform controller action
        ResponseEntity<List<User>> response = userController.getAllUsers();

        // Verify service method was called
        verify(userService, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(users, response.getBody());
    }

    @Test
    void getUserById() {
        // Mock service method
        when(userService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testUser)));

        // Perform controller action
        ResponseEntity<User> response = userController.getUserById(1L);

        // Verify service method was called
        verify(userService, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void updateUser() {
        // Mock service method
        when(userService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testUser)));
        when(userService.save(any())).thenReturn(ResponseEntity.ok(testUser));

        // Perform controller action
        ResponseEntity<User> response = userController.updateUser(1L, testUser);

        // Verify service methods were called
        verify(userService, times(1)).findById(anyLong());
        verify(userService, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testUser, response.getBody());
    }

    @Test
    void deleteUser() {
        // Mock service method
        when(userService.deleteById(anyLong())).thenReturn(ResponseEntity.noContent().build());

        // Perform controller action
        ResponseEntity<Void> response = userController.deleteUser(1L);

        // Verify service method was called
        verify(userService, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

