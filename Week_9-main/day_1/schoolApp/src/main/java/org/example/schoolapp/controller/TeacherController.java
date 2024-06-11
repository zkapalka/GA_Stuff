package org.example.schoolapp.controller;

import org.example.schoolapp.Student.Student;
import org.example.schoolapp.Teachers.Teacher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(new Teacher(001, "Mr. Bob", 58 ));
        teacherList.add(new Teacher(002, "Mistress Sue", 37 ));
        return teacherList;
    }

    @GetMapping("/teacher")
    public Teacher getOneTeacher() {
        Teacher singleTeacher = new Teacher(003, "Don Don", 23);
        return singleTeacher;
    }

}
