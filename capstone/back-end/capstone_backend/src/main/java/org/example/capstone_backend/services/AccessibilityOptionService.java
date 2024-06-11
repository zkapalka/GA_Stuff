package org.example.capstone_backend.services;

import org.example.capstone_backend.models.AccessibilityOptions;
import org.example.capstone_backend.repos.AccessibilityOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.List;
import java.util.Optional;

@Service
public class AccessibilityOptionService {
    private final AccessibilityOptionRepository accessibilityOptionRepository;

    @Autowired
    public AccessibilityOptionService(AccessibilityOptionRepository accessibilityOptionRepository) {
        this.accessibilityOptionRepository = accessibilityOptionRepository;
    }

    //READ

    public ResponseEntity<List<AccessibilityOptions>> findAll() {
        try {
            List<AccessibilityOptions> accessibilityOptions = accessibilityOptionRepository.findAll();
            return ResponseEntity.ok(accessibilityOptions);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<AccessibilityOptions> findById(Long id) {
        try {
            Optional<AccessibilityOptions> accessibilityOption = accessibilityOptionRepository.findById(id);
            return accessibilityOption.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //UPDATE
    public ResponseEntity<AccessibilityOptions> save(AccessibilityOptions accessibilityOption) {
        try {
            AccessibilityOptions savedAccessibilityOption = accessibilityOptionRepository.save(accessibilityOption);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAccessibilityOption);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //DELETE
    public ResponseEntity<Void> deleteById(Long id) {
        try {
            accessibilityOptionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}