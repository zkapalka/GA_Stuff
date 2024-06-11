package sectionone;

public class Student extends Person {

    String studentID;
    String profession = "student";

    //Constructor invoking Person super-class
    public Student(String name, int age, String email_address, String studentID){
        super(name, age, email_address);
        this.studentID = studentID;
    }

    public Student () {
        this("", 0, "", "");
    }

    //METHODS
    @Override
    public void speak(){

        System.out.println("Hello, I am a student");
        super.speak();
    }

    public void studentOpinion(){
        System.out.println("I am most studious");
    }

    public int study(int hours) {
        System.out.println( "I studied " + hours + " hours this past weekend");
        return hours;
    }

    @Override
    public void printProfession(){
        System.out.println("My profession is that of a " + profession);
        System.out.println("Superclass profession from Person class: " + super.profession);
    }
}
