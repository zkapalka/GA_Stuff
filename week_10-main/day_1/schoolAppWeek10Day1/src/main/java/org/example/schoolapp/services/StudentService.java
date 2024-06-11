package org.example.schoolapp.services;

import org.example.schoolapp.models.Course;
import org.example.schoolapp.models.Student;
import org.example.schoolapp.models.Teacher;
import org.example.schoolapp.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Course> getCoursesOfStudent(int studentId) throws ChangeSetPersister.NotFoundException {
        return studentRepo.findById(studentId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new)
                .getCourses();
    }

    public Course getCourseDetailOfStudent(int studentId, int courseId) throws ChangeSetPersister.NotFoundException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        Optional<Course> optionalCourse = student.getCourses().stream()
                .filter(course -> course.getId() == courseId)
                .findFirst();

        return optionalCourse.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<Teacher> getTeachersOfStudentCourse(int studentId, int courseId) throws ChangeSetPersister.NotFoundException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        List<Teacher> teachers = new ArrayList<>();
        for (Course course : student.getCourses()) {
            if (course.getId() == courseId) {
                teachers.addAll(course.getTeachers());
                break;
            }
        }

        return teachers;
    }

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student addCourseToStudent(int studentId, Course course) throws ChangeSetPersister.NotFoundException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        student.getCourses().add(course); // Assuming getCourses() returns a List<Course>

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

    public void deleteCourseFromStudent(int studentId, int courseId) throws ChangeSetPersister.NotFoundException {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        student.getCourses().removeIf(course -> course.getId() == courseId);

        studentRepo.save(student);
    }
}

