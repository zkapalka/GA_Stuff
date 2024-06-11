package org.example.capstone_backend.serviceTest;

import org.example.capstone_backend.models.AccessibilityOptions;
import org.example.capstone_backend.repos.AccessibilityOptionRepository;
import org.example.capstone_backend.services.AccessibilityOptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessibilityOptionServiceTest {

    @Mock
    private AccessibilityOptionRepository accessibilityOptionRepository;

    @InjectMocks
    private AccessibilityOptionService accessibilityOptionService;

    private AccessibilityOptions testAccessibilityOption;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        testAccessibilityOption = new AccessibilityOptions();
        testAccessibilityOption.setOptionsID(1L);
    }

    @Test
    void findAll() {
        // Mock repository method
        List<AccessibilityOptions> accessibilityOptions = new ArrayList<>();
        accessibilityOptions.add(testAccessibilityOption);
        when(accessibilityOptionRepository.findAll()).thenReturn(accessibilityOptions);

        // Perform service action
        ResponseEntity<List<AccessibilityOptions>> response = accessibilityOptionService.findAll();

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(accessibilityOptions, response.getBody());
    }

    @Test
    void findById() {
        // Mock repository method
        when(accessibilityOptionRepository.findById(anyLong())).thenReturn(Optional.of(testAccessibilityOption));

        // Perform service action
        ResponseEntity<AccessibilityOptions> response = accessibilityOptionService.findById(1L);

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testAccessibilityOption, response.getBody());
    }

    @Test
    void save() {
        // Mock repository method
        when(accessibilityOptionRepository.save(any())).thenReturn(testAccessibilityOption);

        // Perform service action
        ResponseEntity<AccessibilityOptions> response = accessibilityOptionService.save(testAccessibilityOption);

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testAccessibilityOption, response.getBody());
    }

    @Test
    void deleteById() {
        // Perform service action
        ResponseEntity<Void> response = accessibilityOptionService.deleteById(1L);

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteById_NotFound() {
        // Mock repository method to throw EmptyResultDataAccessException
        doThrow(EmptyResultDataAccessException.class).when(accessibilityOptionRepository).deleteById(anyLong());

        // Perform service action
        ResponseEntity<Void> response = accessibilityOptionService.deleteById(1L);

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findAll_DataAccessException() {
        // Mock repository method to throw CannotAcquireLockException (subclass of DataAccessException)
        when(accessibilityOptionRepository.findAll()).thenThrow(CannotAcquireLockException.class);

        // Perform service action
        ResponseEntity<List<AccessibilityOptions>> response = accessibilityOptionService.findAll();

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }


    @Test
    void findById_DataAccessException() {
        // Mock repository method to throw a TransientDataAccessException directly
        when(accessibilityOptionRepository.findById(anyLong())).thenThrow(new DataAccessException("Test Exception") {});

        // Perform service action
        ResponseEntity<AccessibilityOptions> response = accessibilityOptionService.findById(1L);

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void save_DataAccessException() {
        // Mock repository method to throw TransientDataAccessException (subclass of DataAccessException)
        when(accessibilityOptionRepository.save(any())).thenThrow(new DataAccessException("Test Exception") {});

        // Perform service action
        ResponseEntity<AccessibilityOptions> response = accessibilityOptionService.save(testAccessibilityOption);

        // Verify repository method was called
        verify(accessibilityOptionRepository, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }
}

