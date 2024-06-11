package org.example.schoolapp.services;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepo courseRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepo.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public Course updateCourse(int id, Course updatedCourse) {
        if (courseRepo.existsById(id)) {
            updatedCourse.setId(id);
            return courseRepo.save(updatedCourse);
        } else {
            throw new IllegalArgumentException("Course with ID " + id + " does not exist");
        }
    }

    public void deleteCourse(int id) {
        courseRepo.deleteById(id);
    }
}
