package org.example.schoolapp.repository;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    // No need to add any method declarations here
}