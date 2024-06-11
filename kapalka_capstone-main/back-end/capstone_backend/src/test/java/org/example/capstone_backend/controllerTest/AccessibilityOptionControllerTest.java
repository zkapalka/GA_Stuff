package org.example.capstone_backend.controllerTest;

import org.example.capstone_backend.controllers.AccessibilityOptionController;
import org.example.capstone_backend.models.AccessibilityOptions;
import org.example.capstone_backend.services.AccessibilityOptionService;
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


class AccessibilityOptionControllerTest {

    @Mock
    private AccessibilityOptionService accessibilityOptionService;

    @InjectMocks
    private AccessibilityOptionController accessibilityOptionController;

    private AccessibilityOptions testAccessibilityOption;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        testAccessibilityOption = new AccessibilityOptions();
        testAccessibilityOption.setOptionsID(1L);
    }

    //Commenting this out because createAccessibilityOptions is not supported in prod anymore

//    @Test
//    void createAccessibilityOption() {
//        // Mock service method
//        when(accessibilityOptionService.save(any())).thenReturn(ResponseEntity.ok(testAccessibilityOption));
//
//        // Perform controller action
//        ResponseEntity<AccessibilityOptions> response = accessibilityOptionController.createAccessibilityOption(testAccessibilityOption);
//
//        // Verify service method was called
//        verify(accessibilityOptionService, times(1)).save(any());
//
//        // Verify response
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(testAccessibilityOption, response.getBody());
//    }

    @Test
    void getAllAccessibilityOptions() {
        // Mock service method
        List<AccessibilityOptions> accessibilityOptions = new ArrayList<>();
        accessibilityOptions.add(testAccessibilityOption);
        when(accessibilityOptionService.findAll()).thenReturn(ResponseEntity.ok(accessibilityOptions));

        // Perform controller action
        ResponseEntity<List<AccessibilityOptions>> response = accessibilityOptionController.getAllAccessibilityOptions();

        // Verify service method was called
        verify(accessibilityOptionService, times(1)).findAll();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(accessibilityOptions, response.getBody());
    }

    @Test
    void getAccessibilityOptionById() {
        // Mock service method
        when(accessibilityOptionService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testAccessibilityOption)));

        // Perform controller action
        ResponseEntity<AccessibilityOptions> response = accessibilityOptionController.getAccessibilityOptionById(1L);

        // Verify service method was called
        verify(accessibilityOptionService, times(1)).findById(anyLong());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testAccessibilityOption, response.getBody());
    }

    @Test
    void updateAccessibilityOption() {
        // Mock service method
        when(accessibilityOptionService.findById(anyLong())).thenReturn(ResponseEntity.of(Optional.of(testAccessibilityOption)));
        when(accessibilityOptionService.save(any())).thenReturn(ResponseEntity.ok(testAccessibilityOption));

        // Perform controller action
        ResponseEntity<AccessibilityOptions> response = accessibilityOptionController.updateAccessibilityOption(1L, testAccessibilityOption);

        // Verify service methods were called
        verify(accessibilityOptionService, times(1)).findById(anyLong());
        verify(accessibilityOptionService, times(1)).save(any());

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testAccessibilityOption, response.getBody());
    }

    @Test
    void deleteAccessibilityOption() {
        // Mock service method
        when(accessibilityOptionService.deleteById(anyLong())).thenReturn(ResponseEntity.noContent().build());

        // Perform controller action
        ResponseEntity<Void> response = accessibilityOptionController.deleteAccessibilityOption(1L);

        // Verify service method was called
        verify(accessibilityOptionService, times(1)).deleteById(anyLong());

        // Verify response
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

