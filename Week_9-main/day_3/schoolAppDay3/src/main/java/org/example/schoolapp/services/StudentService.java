package org.example.schoolapp.services;

import org.example.schoolapp.models.Student;
import org.example.schoolapp.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepo.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student updateStudent(int id, Student updatedStudent) {
        if (studentRepo.existsById(id)) {
            updatedStudent.setId(id);
            return studentRepo.save(updatedStudent);
        } else {
            throw new IllegalArgumentException("Student with ID " + id + " does not exist");
        }
    }

    public void deleteStudent(int id) {
        studentRepo.deleteById(id);
    }
}

