package codingchallengeandbonus;

public class Bonus {


    public static void main(String[] args) {


        //Calling Exercise 8
        System.out.println("Exercise 6: " + isEven(4));

        //Calling Exercise 9
        System.out.println("Exercise 7: " + getMax(1, 1337));

        //Calling Exercise 10
        String testString1 = "racecar";
        String testString2 = "hello";

        System.out.println("Is this word a palindrome? " + testString1 + " " + isPalindrome(testString1));
        System.out.println("Is this word a palindrome? " + testString2 + " " + isPalindrome(testString2));

        //Calling Exercise 11
        double fahrenheit1 = convertToFahrenheit(20);
        double fahrenheit2 = convertToFahrenheit(24.3);

        System.out.println("codingchallengeandbonus.Bonus Exercise 14: " + fahrenheit1 + " \u2109");
        System.out.println("codingchallengeandbonus.Bonus Exercise 14: " + fahrenheit2 + " \u2109");

        //Calling Exercise 12
        double celsius1 = convertToCelsius(50);
        double celsius2 = convertToCelsius(67.5);

        System.out.println("codingchallengeandbonus.Bonus Exercise 15: " + celsius1 + " \u2103");
        System.out.println("codingchallengeandbonus.Bonus Exercise 15: " + celsius2 + " \u2103");

        //Calling Exercise 15
        System.out.println("Exercise 15: " + reverseString("Kapalka Time"));


    }

    //METHODS

    //Exercise 8
    public static boolean isEven(int n1) {
        return n1 % 2 == 0;
    }

    //Exercise 9
    public static int getMax(int n1, int n2) {
        return Math.max(n1, n2);
    }

    //Bonus Exercise 10
    public static boolean isPalindrome (String s1){
        //remove space and convert to lowercase
        s1 = s1.replaceAll("\\s+", "").toLowerCase();

        StringBuilder reversedS1 = new StringBuilder(s1).reverse();
        return s1.equals(reversedS1.toString());
    }

    //Bonus Exercise 11
    public static double convertToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    //Bonus Exercise 12
    public static double convertToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    //Exercise 15
    public static String reverseString(String s1){
        StringBuilder reversedS1 = new StringBuilder(s1).reverse();
        return reversedS1.toString();
    }


}
