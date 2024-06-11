package org.example.schoolapp.controller;

import org.example.schoolapp.Student.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> studentList;

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "Bob", 18 ));
        studentList.add(new Student(2, "Sue", 17 ));
        return studentList;
    }

    @GetMapping("/student")
    public Student getOneStudent() {
        Student singleStudent = new Student(3, "Don", 23);
        return singleStudent;
    }

    @GetMapping("/{name}")
    public Student getOneStudentByName(@PathVariable String name) {
        return getStudentByName(name);
    }


    public Student getStudentByName(String name) {
        for (Student student : studentList) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null; // Return null if student with given name is not found
    }


}
