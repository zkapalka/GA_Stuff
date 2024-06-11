package org.example.schoolapp.controller;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        return courseService.getCourseById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    //POST
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    //PUT
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }


    //DELETE

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
