package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.model.Address;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.User;
import org.example.jobboardspringapplication.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        // Given
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);

        // When
        ResponseEntity<User> responseEntity = userController.createUser(user);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testCreateUser_InternalServerError() {
        // Given
        User user = new User();
        when(userService.createUser(user)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<User> responseEntity = userController.createUser(user);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllUsers_Success() {
        // Given
        List<User> users = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);

        // When
        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(users, responseEntity.getBody());
    }

    @Test
    void testGetAllUsers_InternalServerError() {
        // Given
        when(userService.getAllUsers()).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetUserById_Success() {
        // Given
        Integer userId = 1;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        // When
        ResponseEntity<User> responseEntity = userController.getUserById(userId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testGetUserById_NotFound() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<User> responseEntity = userController.getUserById(userId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetUserById_InternalServerError() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<User> responseEntity = userController.getUserById(userId);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetUserPosts_Success() {
        // Given
        Integer userId = 1;
        List<Post> posts = new ArrayList<>();
        when(userService.getUserPosts(userId)).thenReturn(posts);

        // When
        ResponseEntity<List<Post>> responseEntity = userController.getUserPosts(userId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(posts, responseEntity.getBody());
    }

    @Test
    void testGetUserPosts_InternalServerError() {
        // Given
        Integer userId = 1;
        when(userService.getUserPosts(userId)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Post>> responseEntity = userController.getUserPosts(userId);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetAddressOfUser_Success() {
        // Given
        Integer userId = 1;
        Address address = new Address();
        User user = new User();
        user.setAddress(address);
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));

        // When
        ResponseEntity<Address> responseEntity = userController.getAddressOfUser(userId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(address, responseEntity.getBody());
    }

    @Test
    void testGetAddressOfUser_NotFound() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Address> responseEntity = userController.getAddressOfUser(userId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetAddressOfUser_InternalServerError() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Address> responseEntity = userController.getAddressOfUser(userId);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllCommentsForUser_Success() {
        // Given
        Integer userId = 1;
        List<Comment> comments = new ArrayList<>();
        when(userService.getAllCommentsForUser(userId)).thenReturn(comments);

        // When
        ResponseEntity<List<Comment>> responseEntity = userController.getAllCommentsForUser(userId);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comments, responseEntity.getBody());
    }

    @Test
    void testGetAllCommentsForUser_InternalServerError() {
        // Given
        Integer userId = 1;
        when(userService.getAllCommentsForUser(userId)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Comment>> responseEntity = userController.getAllCommentsForUser(userId);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateUser_Success() {
        // Given
        Integer userId = 1;
        User user = new User();
        when(userService.getUserById(userId)).thenReturn(Optional.of(user));
        when(userService.saveUser(user)).thenReturn(user);

        // When
        ResponseEntity<User> responseEntity = userController.updateUser(userId, user);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testUpdateUser_NotFound() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<User> responseEntity = userController.updateUser(userId, new User());

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateUser_InternalServerError() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(Optional.of(new User()));
        when(userService.saveUser(any())).thenThrow(new RuntimeException());

        // When
        ResponseEntity<User> responseEntity = userController.updateUser(userId, new User());

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }


    @Test
    void testDeleteUserById_Success() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(Optional.of(new User()));

        // When
        ResponseEntity<Void> responseEntity = userController.deleteUserById(userId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteUserById_NotFound() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Void> responseEntity = userController.deleteUserById(userId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteUserById_InternalServerError() {
        // Given
        Integer userId = 1;
        when(userService.getUserById(userId)).thenReturn(Optional.of(new User()));
        doThrow(new RuntimeException()).when(userService).deleteUserById(userId);

        // When
        ResponseEntity<Void> responseEntity = userController.deleteUserById(userId);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
