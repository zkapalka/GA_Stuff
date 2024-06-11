package org.example.schoolapp.repository;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.models.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CourseRepo {
    private static List<Course> courses = Arrays.asList(new Course(1, "Intro to Sword-fighting", "Oswin", 3),
            new Course(2, "The Art Of Defense", "Oswin", 3),
            new Course(3, "Dark Magic 101", "Athos", 3),
            new Course(4, "How To Befriend Dragons", "Athos", 4),
            new Course(5, "Horse Archery", "Rath", 1)
    );

    public static List<Course> getCourses() {
        return courses;
    }


    public static List<Course> addCourse(Course course) {
        courses.add(course);
        return courses;
    }

    public static void updateCourse(int id, Course course) {
        courses.stream().map(currentCourse -> {
            if (currentCourse.getId() == id) {
                currentCourse.setInstructor(course.getInstructor());
                currentCourse.setName(course.getName());
            }
            return currentCourse;
        }).collect(Collectors.toList());
    }

    public static void deleteCourseById(int id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) {
                courses.remove(i);
                return;
            }
        }
        throw new RuntimeException("Course not found with id: " + id);
    }

}
