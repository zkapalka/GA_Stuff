package org.example.schoolapp.services;

import org.example.schoolapp.models.Teacher;
import org.example.schoolapp.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepo teacherRepo;

    @Autowired
    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Optional<Teacher> getTeacherById(int id) {
        return teacherRepo.findById(id);
    }

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepo.save(teacher);
    }

    public Teacher updateTeacher(int id, Teacher updatedTeacher) {
        if (teacherRepo.existsById(id)) {
            updatedTeacher.setId(id);
            return teacherRepo.save(updatedTeacher);
        } else {
            throw new IllegalArgumentException("Teacher with ID " + id + " does not exist");
        }
    }

    public void deleteTeacher(int id) {
        teacherRepo.deleteById(id);
    }
}
