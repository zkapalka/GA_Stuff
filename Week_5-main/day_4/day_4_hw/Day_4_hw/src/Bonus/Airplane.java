package Bonus;

public class Airplane implements TransportMode{
    @Override
    public void travel(){
        System.out.println("Flying by plane! Are you in first class or economy?");
    }
}
