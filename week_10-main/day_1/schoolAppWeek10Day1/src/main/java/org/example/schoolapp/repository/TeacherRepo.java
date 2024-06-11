package org.example.schoolapp.repository;

import org.example.schoolapp.models.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
    // No need to add any method declarations here
}