package org.example.schoolapp.controller;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.models.Student;
import org.example.schoolapp.models.Teacher;
import org.example.schoolapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    //GETS
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        return studentService.getStudentById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @GetMapping("/{id}/courses")
    public List<Course> getCoursesOfStudent(@PathVariable int id) throws ChangeSetPersister.NotFoundException {
        return studentService.getCoursesOfStudent(id);
    }

    @GetMapping("/{studentId}/courses/{courseId}")
    public Course getCourseDetailOfStudent(@PathVariable int studentId, @PathVariable int courseId) throws ChangeSetPersister.NotFoundException {
        return studentService.getCourseDetailOfStudent(studentId, courseId);
    }

    @GetMapping("/{studentId}/courses/{courseId}/teachers")
    public List<Teacher> getTeachersOfStudentCourse(@PathVariable int studentId, @PathVariable int courseId) throws ChangeSetPersister.NotFoundException {
        return studentService.getTeachersOfStudentCourse(studentId, courseId);
    }


    //POST

    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PostMapping("/{id}/courses")
    public Student addCourseToStudent(@PathVariable int id, @RequestBody Course course) throws ChangeSetPersister.NotFoundException {
        return studentService.addCourseToStudent(id, course);
    }


    //PUT
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/{id}/courses/{courseId}")
    public void deleteCourseFromStudent(@PathVariable int id, @PathVariable int courseId) throws ChangeSetPersister.NotFoundException {
        studentService.deleteCourseFromStudent(id, courseId);
    }


}
