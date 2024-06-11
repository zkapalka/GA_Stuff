package org.example.schoolapp.controller;

import org.example.schoolapp.Courses.Course;
import org.example.schoolapp.Teachers.Teacher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course(001, "A study of weight lifting ", "Mr. Bob", 3 ));
        courseList.add(new Course(001, "Economics 101 ", "Mistress Sue", 3 ));
        return courseList;
    }

    @GetMapping("/course")
    public Course getOneCourse() {
        Course singleCourse = new Course(003, "How to be the donniest Don ever", "Don Don", 23);
        return singleCourse;
    }

}
