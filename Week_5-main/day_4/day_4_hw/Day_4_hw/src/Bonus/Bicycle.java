package Bonus;

public class Bicycle implements TransportMode{

    @Override
    public void travel() {
        System.out.println("Riding a bicycle, are you practicing for Tour de France?");
    }
}
