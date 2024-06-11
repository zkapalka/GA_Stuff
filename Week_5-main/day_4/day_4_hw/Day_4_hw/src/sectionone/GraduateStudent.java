package sectionone;

public class GraduateStudent extends Student {

    String studyTopic;

    //Constructor
    public GraduateStudent(String name, int age, String email_address, String studentID, String studyTopic) {
        super(name, age, email_address, studentID);
        this.studyTopic = studyTopic;
    }

    public GraduateStudent () {
        this("", 0, "", "", "");
    }


    public void speak(){
        System.out.println("Hello, I am a graduate student");
    }

    @Override
    public void flex() {
        System.out.println("This is calling from the Person class below" );
        super.flex();
        System.out.println("I am a graduate student that has lost most of my physical vigor");
    }
}
