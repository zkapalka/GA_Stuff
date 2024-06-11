package org.example.schoolapp.controller;

import org.example.schoolapp.models.Teacher;
import org.example.schoolapp.repository.TeacherRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeacherController {
    //CREATE
    @PostMapping("/teachers")
    public String createTeacher(@RequestBody Teacher teacher) {
        TeacherRepo.addTeacher(teacher);
        return "Teacher added successfully";
    }

    // Read (GET) endpoint to retrieve all teachers
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return TeacherRepo.getTeachers();
    }

    //READ/GET to retrieve teacher by name
    @GetMapping("/teacher/{name}")
    public Teacher getOneTeacher(@PathVariable String name) {
        for(Teacher teacher: TeacherRepo.getTeachers()) {
            if(teacher.getName().equals(name)) {
                return teacher;
            }
        }
        return null;
    }

    //READ GET to retrieve teacher by id
    @GetMapping("/teacher/{id}")
    public Teacher getOneTeacherByID(@PathVariable int id) {
        for(Teacher teacher: TeacherRepo.getTeachers()) {
            if(teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    //Update teacher by ID
    @PutMapping("/teachers/{id}")
    public String updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        TeacherRepo.updateTeacher(id, teacher);
        return "Teacher updated successfully";
    }

    //Delete Teacher by ID
    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable int id) {
        TeacherRepo.deleteTeacherById(id);
    }

}
