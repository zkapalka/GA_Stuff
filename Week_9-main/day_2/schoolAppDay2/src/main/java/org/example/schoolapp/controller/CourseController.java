package org.example.schoolapp.controller;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.repository.CourseRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {
    //CREATE
    @PostMapping("/courses")
    public String createCourse(@RequestBody Course course) {
        CourseRepo.addCourse(course);
        return "Course added successfully";
    }

    // Read (GET) endpoint to retrieve all courses
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return CourseRepo.getCourses();
    }

    // READ/GET to retrieve course by name
    @GetMapping("/course/{name}")
    public Course getOneCourse(@PathVariable String name) {
        for(Course course: CourseRepo.getCourses()) {
            if(course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }

    //READ GET to retrieve course by id
    @GetMapping("/course/{id}")
    public Course getOneCourseByID(@PathVariable int id) {
        for(Course course: CourseRepo.getCourses()) {
            if(course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    //Update course by ID
    @PutMapping("/courses/{id}")
    public String updateCourse(@PathVariable int id, @RequestBody Course course) {
        CourseRepo.updateCourse(id, course);
        return "Course updated successfully";
    }

    //Delete Course by ID
    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable int id) {
        CourseRepo.deleteCourseById(id);
    }

}
