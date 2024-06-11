package org.example.schoolapp.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teacher;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    public List<Teacher> getTeachers() {
        return teacher;
    }

    public void setTeachers(List<Teacher> teacher) {
        this.teacher = teacher;
    }

}