package org.example.schoolapp.controller;

import org.example.schoolapp.models.Student;
import org.example.schoolapp.repository.StudentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {


    //CREATE
    @PostMapping("/students")
    public String createStudent(@RequestBody Student student) {
        StudentRepo.addStudent(student);
        return "Student added successfully";
    }

    // Read (GET) endpoint to retrieve all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return StudentRepo.getStudents();
    }

    //READ/GET to retrieve student by name
    @GetMapping("/student/{name}")
    public Student getOneStudent(@PathVariable String name) {
        for(Student student: StudentRepo.getStudents()) {
            if(student.getName().equals(name)) {
                return student;
            }
        }

        return null;
    }

    //READ GET to retrieve student by id
    @GetMapping("/student/{id}")
    public Student getOneStudentByID(@PathVariable int id) {
        for(Student student: StudentRepo.getStudents()) {
            if(student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    //Update student by ID
    @PutMapping("/students/{id}")
    public String updateStudent(@PathVariable int id, @RequestBody Student student) {
        StudentRepo.updateStudent(id, student);
        return "Student updated successfully";
    }

    //Delete Student by ID
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        StudentRepo.deleteStudentById(id);
    }



}
