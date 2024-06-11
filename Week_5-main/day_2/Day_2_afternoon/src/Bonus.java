public class Bonus {

    public static void main(String[] args) {
        // Bonus

        // Question 1
        int[] numbers = {10, 20, 30, 40};
        int sum = numbers[0] + numbers[3]; // What is the value of sum?

        System.out.println(sum);

        // Question 2
        char[] chars = {'a', 'b', 'c', 'd', 'e'};
        char third = chars[2]; // What is the value of third?

        System.out.println(third);

        // Question 3
        int[] arr = new int[5];
        arr[0] = 10;
        arr[4] = 50; // What are the values in the array arr?

        System.out.println("Array elements:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        // Question 4
        String[] names = {"Alice", "Bob", "Charlie"};
        int length = names.length; // What is the value of length?

        System.out.println(length);

        // Question 5
        int[] nums = {1, 2, 3, 4, 5};
        boolean contains = false;
        for (int num : nums) { // What is this loop called
            if (num == 3) {
                contains = true;
                break;
            }
        } // What is the value of contains?

        System.out.println(contains);

        //Question 6
        // Define a method isEven that takes an integer as input and returns true if the number is even, and false otherwise.
        class EvenCheck {
            public static boolean isEven(int number) {
                if (number % 2 == 0) {
                    // Number is even
                    return true;
                } else {
                    // Number is odd
                    return false;
                }
            }


            //Question 7
            // Define a method getMax that takes two integers as input and returns the maximum of the two numbers.


            public static int getMax(int a, int b) {
                return Math.max(a, b);
            }

            //Question 8
            // Define a method reverseString that takes a string as input and returns the reverse of the string.


            public static String reverseWithStringBuilder(String str) {
                return new StringBuilder(str).reverse().toString();
            }
        }
    }
}

