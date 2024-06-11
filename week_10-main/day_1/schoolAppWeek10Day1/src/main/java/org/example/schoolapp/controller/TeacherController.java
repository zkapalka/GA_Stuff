package org.example.schoolapp.controller;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.models.Teacher;
import org.example.schoolapp.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //GET
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        return teacherService.getTeacherById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @GetMapping("/{teacherId}/courses")
    public List<Course> getCoursesOfTeacher(@PathVariable int teacherId) throws ChangeSetPersister.NotFoundException {
        return teacherService.getCoursesOfTeacher(teacherId);
    }

    //POST

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @PostMapping("/{teacherId}/courses")
    public Teacher addCourseToTeacher(@PathVariable int teacherId, @RequestBody Course course) throws ChangeSetPersister.NotFoundException {
        return teacherService.addCourseToTeacher(teacherId, course);
    }

    //PUT

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
    }
}
