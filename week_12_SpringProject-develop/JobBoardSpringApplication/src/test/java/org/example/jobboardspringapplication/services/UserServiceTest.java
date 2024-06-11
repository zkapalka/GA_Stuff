package org.example.jobboardspringapplication.services;

import org.example.jobboardspringapplication.exceptions.UserException;
import org.example.jobboardspringapplication.model.User;
import org.example.jobboardspringapplication.repo.IUserRepo;
import org.example.jobboardspringapplication.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser_Success() {
        User user = new User();
        user.setUserID(1);

        when(userRepo.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(user, createdUser);
        verify(userRepo).save(user);
    }

    @Test
    void testGetAllUsers_Success() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepo.findAll()).thenReturn(users);

        List<User> retrievedUsers = userService.getAllUsers();

        assertEquals(users.size(), retrievedUsers.size());
        assertEquals(users, retrievedUsers);
        verify(userRepo).findAll();
    }

    @Test
    void testGetUserById_Success() {
        User user = new User();
        user.setUserID(1);

        when(userRepo.findById(user.getUserID())).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(user.getUserID());

        assertTrue(retrievedUser.isPresent());
        assertEquals(user, retrievedUser.get());
        verify(userRepo).findById(user.getUserID());
    }

    @Test
    void testGetUserById_NotFound() {
        int userId = 1;

        when(userRepo.findById(userId)).thenReturn(Optional.empty());

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertFalse(retrievedUser.isPresent());
        verify(userRepo).findById(userId);
    }

    @Test
    void testSaveUser_Success() {
        User user = new User();
        user.setUserID(1);

        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals(user, savedUser);
        verify(userRepo).save(user);
    }

    @Test
    void testDeleteUserById_Success() {
        int userId = 1;

        assertDoesNotThrow(() -> userService.deleteUserById(userId));
        verify(userRepo).deleteById(userId);
    }

    @Test
    void testDeleteUserById_NotFound() {
        int userId = 1;

        // Mock the behavior of deleteById() to throw EmptyResultDataAccessException
        doThrow(EmptyResultDataAccessException.class).when(userRepo).deleteById(userId);

        assertThrows(UserException.class, () -> userService.deleteUserById(userId));
        verify(userRepo).deleteById(userId);
    }

    // Add other test methods as needed...

}

