package org.example.schoolapp.repository;

import org.example.schoolapp.models.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepo {

    private static List<Student> students = Arrays.asList(new Student(1, "Ike", 18 ),
            new Student(2, "Hector", 18 ),
            new Student(3, "Eliwood", 17 ),
            new Student(4, "Guy", 18 ),
            new Student(5, "Valter", 19 )
    );

    public static List<Student> getStudents() {
        return students;
    }

    public static List<Student> addStudent(Student student) {
        students.add(student);
        return students;
    }


    public static void updateStudent (int id, Student student){
        students.stream().map(currentStudent -> {
            if (currentStudent.getId() == id) {
                currentStudent.setAge(student.getAge());
                currentStudent.setName(student.getName());
            }
            return currentStudent;
            }).collect(Collectors.toList());
        }

    public static void deleteStudentById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return;
            }
        }
        throw new RuntimeException("Student not found with id: " + id);
    }




    }



