package sectionone;

public class Professor extends Person {

    private String facultyID;

    // Constructors
    public Professor (String name, int age, String email_address, String facultyID) {
        super(name, age, email_address);
        this.facultyID = facultyID;
    }

    public Professor() {
        this("", 0, "", "");
    }

    //METHODS
    @Override
    public void speak(){
        System.out.println("I am a professor, my frontal cortex is large");
    }




}
