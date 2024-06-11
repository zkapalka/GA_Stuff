package org.example.capstone_backend.controllers;

import org.example.capstone_backend.models.AccessibilityOptions;
import org.example.capstone_backend.services.AccessibilityOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accessibility-options")
public class AccessibilityOptionController {
    private final AccessibilityOptionService accessibilityOptionService;

    @Autowired
    public AccessibilityOptionController(AccessibilityOptionService accessibilityOptionService) {
        this.accessibilityOptionService = accessibilityOptionService;
    }

    // Commenting this out on purpose. This is used for testing purposes
//    //CREATE
//    @PostMapping
//    public ResponseEntity<AccessibilityOptions> createAccessibilityOption(@RequestBody AccessibilityOptions accessibilityOption) {
//        try {
//            return accessibilityOptionService.save(accessibilityOption);
//        } catch (DataAccessException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }

    //READ
    @GetMapping
    public ResponseEntity<List<AccessibilityOptions>> getAllAccessibilityOptions() {
        try {
            return accessibilityOptionService.findAll();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessibilityOptions> getAccessibilityOptionById(@PathVariable Long id) {
        try {
            return accessibilityOptionService.findById(id);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<AccessibilityOptions> updateAccessibilityOption(@PathVariable Long id, @RequestBody AccessibilityOptions accessibilityOption) {
        try {
            AccessibilityOptions existingOption = accessibilityOptionService.findById(id).getBody();
            if (existingOption != null) {
                accessibilityOption.setOptionsID(id); // Ensure the ID in the request body matches the ID in the path
                return ResponseEntity.ok(accessibilityOptionService.save(accessibilityOption).getBody());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //DELETE
    public ResponseEntity<Void> deleteAccessibilityOption(@PathVariable Long id) {
        try {
            return accessibilityOptionService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}