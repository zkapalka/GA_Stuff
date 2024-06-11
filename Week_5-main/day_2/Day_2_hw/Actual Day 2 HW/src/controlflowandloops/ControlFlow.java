package controlflowandloops;

public class ControlFlow {

    //Main call function
    public static void main(String[] args) {
        //Calling Exercise 1
        simpleIfElse();

        //Calling Exercise 2
        lotrFizzBuzz();

        //Calling Exercise 3
        sampleWhileLoop();

        //Calling Exercise 4
        printAllEven();

        //Calling Exercise 5
        testingSwitchStatement();


    }

    //METHODS

    //Exercise 1
    public static void simpleIfElse() {
        int number = 1;

        if (number == 0) {
            System.out.println("Exercise 1: Zero");
        }else if (number > 0) {
            System.out.println("Exercise 1: Positive");
        } else {
            System.out.println("Exercise 1: Negative");
        }
    }

    //Exercise 2
    public static void lotrFizzBuzz() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0 && i % 3 == 0 ) {
                System.out.println("Exercise 2: LOTR");
            } else if (i % 2 == 0) {
                System.out.println("Exercise 2: Smeagol");
            } else if (i % 3 == 0) {
                System.out.println("Exercise 2: Gandalf");
            } else {
                System.out.println("Exercise 2: " + i);
            }
        }
    }

    // Exercise 3
    public static void sampleWhileLoop() {
        int whileCounter = 1;
        int whileSum = 0;


        while (whileCounter <= 10){
            whileSum += whileCounter;
            whileCounter++;
        }
        System.out.println("Exercise 3: " + whileSum);
    }

    //Exercise 4
    public static void printAllEven(){
        int count = 1;

        do {
            if (count % 2 == 0) {
                System.out.println("Exercise 4: " + count);
            }
            count ++;

        } while (count <= 100);
    }

    //Exercise 5
    public static void testingSwitchStatement() {
        int monthNumber = 10;
        String monthString;

        switch (monthNumber) {
            case 1:
                monthString = "January, it's winter!";
                break;
            case 2:
                monthString = "February, it's winter! But still wet and depressing";
                break;
            case 3:
                monthString = "March, it's almost spring, but still a hint of winter!";
                break;
            case 4:
                monthString = "April, it's finally spring!!";
                break;
            case 5:
                monthString = "May, it's spring, but getting a bit warm!";
                break;
            case 6:
                monthString = "June, it's summer, but not too hot. YET!";
                break;
            case 7:
                monthString = "July, it's summer but it's getting uncomfortably hot!";
                break;
            case 8:
                monthString = "August, the end of summer! But still so hot that people can't play outside";
                break;
            case 9:
                monthString = "September, it's fall, but still cooling down!";
                break;
            case 10:
                monthString = "October, it's fall, time for some pumpkin spice latte!";
                break;
            case 11:
                monthString = "November, it's time for some dark chocolate pecan pie!";
                break;
            case 12:
                monthString = "December, winter, and hopefully some snow!";
                break;
            default:
                monthString = "Yo, bro, that wasn't a valid month number";
        }
        System.out.println("Exercise 5: " + monthString);
    }

}


