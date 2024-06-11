package sectionone;

public class Person {

    String name;
    int age;
    String email_address;
    String profession = "Profession";

    //Constructor
    public Person () {
        this("", 0, "");
    }


    public Person(String name, int age, String email_address) {
        this.name = name;
        this.age = age;
        this.email_address = email_address;
    }

    //METHODS

    public void speak(){
        System.out.println("Hello");
    }

    public void speak(String s1) {
        System.out.println(s1);
    }

    void printProfession() {
        System.out.println("My profession is..." + profession);
    }

    public void walk() {
        System.out.println("I am embarking on a most brisk mosey to further enhance my physical vigor");
    }

    public void flex() {
        System.out.println("Please observe me flexing my muscles to demonstrate my physical vigor");
    }

}
