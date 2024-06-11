package org.example.schoolapp.Courses;
public class Course {
    private int id;
    private String name;
    private String instructor;
    private int credits;

    public Course() {
    }

    public Course(int id, String name, String instructor, int credits) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructor='" + instructor + '\'' +
                ", credits=" + credits +
                '}';
    }
}

