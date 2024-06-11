package methodoverloading;

import java.text.DecimalFormat;

public class OverLoadingTest {

    public static void main(String[] args) {

        //Calling Exercise 7
        OverLoadingTest example = new OverLoadingTest();
        example.printArea(1.337);

    }

    //METHODS

    //Exercise 7

    //This calculate rectangle area
    public void printArea (int n1, int n2){
        System.out.println("The area of the rectangle is: " + (n1 * 2) + (n2 * 2));
    }

    //This calculate square area
    public void printArea (int n1){
        System.out.println("The area of the square is: " + (n1 * 4) );
    }
    //This calculate circle area and round to nearest 2 decimal places
    public void printArea (double n1){
        double n3 = 3.14 * (n1 * n1);
        DecimalFormat df = new DecimalFormat("#.##");
        double roundedNumber = Double.parseDouble(df.format(n3));
        System.out.println("The area of the circle is: " + roundedNumber );
    }
}
