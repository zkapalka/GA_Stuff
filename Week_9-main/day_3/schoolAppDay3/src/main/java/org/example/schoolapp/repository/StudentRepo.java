package org.example.schoolapp.repository;

import org.example.schoolapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    // No need to add any method declarations here
}
