package org.example.schoolapp.repository;

import org.example.schoolapp.models.Teacher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherRepo {
    private static List<Teacher> teachers = Arrays.asList(new Teacher(1, "Oswin", 45 ),
            new Teacher(2, "Rath", 28 ),
            new Teacher(3, "Priscilla", 29 ),
            new Teacher(4, "Jessica", 34 ),
            new Teacher(5, "Athos", 999 )
    );

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static List<Teacher> addTeacher(Teacher teacher) {
        teachers.add(teacher);
        return teachers;
    }

    public static void updateTeacher(int id, Teacher teacher) {
        teachers.stream().map(currentTeacher -> {
            if (currentTeacher.getId() == id) {
                currentTeacher.setAge(teacher.getAge());
                currentTeacher.setName(teacher.getName());
            }
            return currentTeacher;
        }).collect(Collectors.toList());
    }

    public static void deleteTeacherById(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.remove(i);
                return;
            }
        }
        throw new RuntimeException("Teacher not found with id: " + id);
    }

}
