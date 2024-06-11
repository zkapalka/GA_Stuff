package org.example.capstone_backend.repos;

import org.example.capstone_backend.models.AccessibilityOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessibilityOptionRepository extends JpaRepository<AccessibilityOptions, Long> {
    // You can add custom query methods if needed
}
