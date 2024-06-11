import Bonus.Airplane;
import Bonus.Bicycle;
import Bonus.Car;
import Bonus.TransportMode;
import sectionone.GraduateStudent;
import sectionone.Person;
import sectionone.Professor;
import sectionone.Student;
import sectiontwo.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person();
        Person person2 = new Student("Zach", 12, "bob@gmail.com", "");
        Person person3 = new GraduateStudent("Vladmir", 1337, "mudkipz@gmail.com", "123456789", "");

        //For #7
        System.out.println("Exercise #7");
        person1.speak();
        person2.speak();
        person3.speak();
        System.out.println();

        //For #8 Upcasting
        Student person4 = new Student("", 0, "", "");
        Person upCastedPerson = person4;
        System.out.println("Exercise #8 Upcasting");
        upCastedPerson.speak();
        //upCastedPerson.study(); testing to verify this was upcasted

        //For #8 Downcasting
        System.out.println();
        Person person5 = new Student("", 1, "", "");
        Student student1 = (Student) person5;
        System.out.println("Exercise #8 Downcasting");
        student1.speak();
        student1.studentOpinion(); //Testing to show that this was downcasted

        System.out.println();
        person3.speak();
        System.out.println();

        //For #2 Intermediate, SuperClass reference
        Person bob1 = new Student();
        Person bob2 = new GraduateStudent();
        Person bob3 = new Professor();

        System.out.println("#2 Intermediate");
        polymorphismDemo(bob1);
        polymorphismDemo(bob2);
        polymorphismDemo(bob3);

        //Interemediate #4
        /*
        This study method only can be called by an object that was made with the student class. It's
        different than overriding because this method is specifically within the student class.
         */
        System.out.println();
        System.out.println("Interemediate #4");
        student1.study(3);

        //Intermediate #5
        GraduateStudent gradStudent = new GraduateStudent();
        Person joeCool = gradStudent; //reference to person
        System.out.println();
        System.out.println("Intermediate #5");
        joeCool.speak(); // Invokes the graduate speak and not the Person speak

        //Intermediate #6
        Student tommyKnocker = new Student();
        System.out.println();
        System.out.println("Intermediate #6");
        tommyKnocker.printProfession();

        //Intermediate #7
        Student frank = new Student();
        GraduateStudent sue = new GraduateStudent();
        System.out.println();
        System.out.println("Intermediate #7");
        frank.walk();
        sue.walk();

        //Advanced #1
        Person[] personArray = new Person[3];

        personArray[0] = new Student();
        personArray[1] = new GraduateStudent();
        personArray[2] = new Professor();
        System.out.println();
        System.out.println("Advanced #1");
        for (Person x: personArray) {
            x.speak();
        }

        //Advanced #2
        GraduateStudent notFlexFink = new GraduateStudent();
        System.out.println();
        System.out.println("Advanced #2");
        notFlexFink.flex();

        //Section 2 Exercise 1
        Circle circle1 = new Circle();
        Rectangle rectangle1 = new Rectangle();
        System.out.println();
        System.out.println("Section 2 Exercise 1");
        circle1.draw();
        rectangle1.draw();

        //Section 2 Exercise 2
        Pizza pizza1 = new Pizza();
        Apple apple1 = new Apple();
        System.out.println();
        System.out.println("Section 2 Exercise 2");
        pizza1.eat();
        apple1.eat();

        //Section 2 Exercise 3
        Duck darkWingDuck = new Duck();
        System.out.println();
        System.out.println("Section 2 Exercise 3");
        darkWingDuck.swim();
        darkWingDuck.fly();

        //Bonus
        Scanner scanner = new Scanner(System.in);


        System.out.println();
        System.out.println("Bonus");
        System.out.println("Choose how you want to travel by! Please select a number: Car(1), Bike(2), Airplane(3)");
        System.out.println();
        int choice = scanner.nextInt();

        TransportMode scannerInputObject;

        //Based on user choice, create an instance of the selected class
        switch (choice) {
            case 1:
                scannerInputObject = new Car();
                break;
            case 2:
                scannerInputObject = new Bicycle();
                break;
            case 3:
                scannerInputObject = new Airplane();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        scannerInputObject.travel();

        scanner.close();








    }

    //METHODS

    public static void polymorphismDemo(Person person){
        person.speak();
    }
}
