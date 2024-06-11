package org.example.capstone_backend.controllerTest;

import org.example.capstone_backend.controllers.GameController;
import org.example.capstone_backend.models.Game;
import org.example.capstone_backend.services.GameService;
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

class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    private Game testGame;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        testGame = new Game();
        testGame.setGameID(1L);
        testGame.setName("Test Game");
    }

    @Test
    void createGame() {
        // Mock service method
        when(gameService.save(any())).thenReturn(ResponseEntity.ok(testGame));

        // Perform controller action
        ResponseEntity<Game> response = gameController.createGame(testGame);

        // Verify service method was called
        verify(gameService, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testGame, response.getBody());
    }

    @Test
    void getAllGames() {
        // Mock service method
        List<Game> games = new ArrayList<>();
        games.add(testGame);
        when(gameService.findAll()).thenReturn(ResponseEntity.ok(games));

        // Perform controller action
        ResponseEntity<List<Game>> response = gameController.getAllGames();

        // Verify service method was called
        verify(gameService, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(games, response.getBody());
    }

    @Test
    void getGameById() {
        // Mock service method
        when(gameService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testGame)));

        // Perform controller action
        ResponseEntity<Game> response = gameController.getGameById(1L);

        // Verify service method was called
        verify(gameService, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testGame, response.getBody());
    }

    @Test
    void updateGame() {
        // Mock service method
        when(gameService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testGame)));
        when(gameService.save(any())).thenReturn(ResponseEntity.ok(testGame));

        // Perform controller action
        ResponseEntity<Game> response = gameController.updateGame(1L, testGame);

        // Verify service methods were called
        verify(gameService, times(1)).findById(anyLong());
        verify(gameService, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testGame, response.getBody());
    }

    @Test
    void deleteGame() {
        // Mock service method
        when(gameService.deleteById(anyLong())).thenReturn(ResponseEntity.noContent().build());

        // Perform controller action
        ResponseEntity<Void> response = gameController.deleteGame(1L);

        // Verify service method was called
        verify(gameService, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

