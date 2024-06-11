package sectiontwo;

public class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("I am a graceful flier");
    }

    @Override
    public void swim(){
        System.out.println("I am graceful like a Zora underwater");
    }
}
