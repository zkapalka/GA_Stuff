package org.example.capstone_backend.serviceTest;

import org.example.capstone_backend.models.Game;
import org.example.capstone_backend.repos.GameRepository;
import org.example.capstone_backend.services.GameService;
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
import static org.mockito.Mockito.*;

public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_Success() {
        // Mock repository method to return a list of games
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        games.add(new Game());
        when(gameRepository.findAll()).thenReturn(games);

        // Perform service action
        ResponseEntity<List<Game>> response = gameService.findAll();

        // Verify repository method was called
        verify(gameRepository, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void findById_GameExists_ReturnsGame() {
        // Mock repository method to return an existing game
        Game game = new Game();
        game.setGameID(1L);
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        // Perform service action
        ResponseEntity<Game> response = gameService.findById(1L);

        // Verify repository method was called
        verify(gameRepository, times(1)).findById(1L);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(game.getGameID(), response.getBody().getGameID());
    }

    @Test
    void findById_GameNotExists_ReturnsNotFound() {
        // Mock repository method to return an empty optional
        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        // Perform service action
        ResponseEntity<Game> response = gameService.findById(1L);

        // Verify repository method was called
        verify(gameRepository, times(1)).findById(1L);

        // Verify response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void save_ValidGame_ReturnsCreated() {
        // Mock repository method to return saved game
        Game game = new Game();
        game.setGameID(1L);
        when(gameRepository.save(any())).thenReturn(game);

        // Perform service action
        ResponseEntity<Game> response = gameService.save(game);

        // Verify repository method was called
        verify(gameRepository, times(1)).save(game);

        // Verify response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(game.getGameID(), response.getBody().getGameID());
    }

    @Test
    void deleteById_GameExists_ReturnsNoContent() {
        // Perform service action
        ResponseEntity<Void> response = gameService.deleteById(1L);

        // Verify repository method was called
        verify(gameRepository, times(1)).deleteById(1L);

        // Verify response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteById_GameNotExists_ReturnsNotFound() {
        // Mock repository method to throw EmptyResultDataAccessException
        doThrow(EmptyResultDataAccessException.class).when(gameRepository).deleteById(1L);

        // Perform service action
        ResponseEntity<Void> response = gameService.deleteById(1L);

        // Verify repository method was called
        verify(gameRepository, times(1)).deleteById(1L);

        // Verify response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void findAll_DataAccessException_ReturnsInternalServerError() {
        // Mock repository method to throw DataAccessException
        when(gameRepository.findAll()).thenThrow(new DataAccessException("Test Exception") {});

        // Perform service action
        ResponseEntity<List<Game>> response = gameService.findAll();

        // Verify repository method was called
        verify(gameRepository, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    // Add more test cases for other scenarios as needed
}

