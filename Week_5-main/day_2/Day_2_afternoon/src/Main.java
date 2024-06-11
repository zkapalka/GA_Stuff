public class Main{

    public static void main(String[] args) {
        // Arithmetic operations

        // Question 1
        int i = 2;
        int j = i++ * 2; // Predict i and j values.

        System.out.println(j);

        // Question 2
        int x = 5;
        int y = 2 * ++x; // Predict x and y values.

        System.out.println(y);


        // Question 3
        int result = 12 % 5; // What is the result?

        System.out.println(result);

        // Question 4
        int a = 15;
        int b = a-- - 5; // Predict a and b values.

        System.out.println(b);

        // Question 5
        int c = 20;
        int d = --c + 3; // Predict c and d values.

        System.out.println(d);

        // Question 6
        // What is the result of 4 + 5 * 2?

        System.out.println(4 + 5 * 2);

        // Question 7
        // What is the result of (4 + 5) * 2?

        System.out.println((4+5) * 2);

        // Question 8
        // What is the result of 10 + -3 * 2?

        System.out.println(10 + -3 * 2);

        // Question 9
        // What is the result of "10" + 3 + 2?

        System.out.println("10" + 3 + 2);

        // Question 10
        // What is the result of 2 + 3 + "10"?

        System.out.println(2 + 3 + "10");

        // String manipulations

        // Question 11
        String str1 = "Hello, ";
        String str2 = "world!";
        String greeting = str1 + str2; // What is greeting?

        System.out.println(greeting);

        // Question 12
        String fullName = "John Doe";
        // What does fullName.substring(5) return?

        System.out.println(fullName.substring(5));

        // Question 13
        String text = "Java";
        // What does text.substring(1, 3) return?

        System.out.println(text.substring(1,3));

        // Question 14
        String book = "Java Programming";
        // What is the result of book.indexOf("Pro")?

        System.out.println(book.indexOf("Pro"));

        // Question 15
        String language = "JavaScript";
        // What is the result of language.indexOf("Java")?

        System.out.println(language.indexOf("Java"));

        // Question 16
        String filePath = "C:\\Users\\Public";
        // What does filePath.substring(3,8) return?

        System.out.println(filePath.substring(3,8));

        // Question 17
        String email = "user@example.com";
        // What is the result of email.indexOf('@')?

        System.out.println(email.indexOf("@"));

        // Question 18
        String sentence = "The quick brown fox";
        // What does sentence.substring(4, 9) return?

        System.out.println(sentence.substring(4,9));

        // Question 19
        String code = "ABC123";
        boolean startsWithABC = code.startsWith("ABC"); // What is the value of startsWithABC?

        System.out.println(startsWithABC);

        // Question 20
        String number = "123XYZ";
        boolean endsWithXYZ = number.endsWith("XYZ"); // What is the value of endsWithXYZ?

        System.out.println(number);

        // Question 21
        // What is the result of "Java" + 1 + 2 + 3?

        System.out.println("Java" + 1 + 2 + 3);

        // Question 22
        // What is the result of 1 + 2 + 3 + "Java"?

        System.out.println(1 + 2 + 3 + "Java");

        // Question 23
        String repeat = "ha";
        // How do you produce a string "hahaha" using repeat?

        System.out.println(repeat.repeat(3));

        // Question 24
        String replaceTest = "bat";
        // Replace 't' with 'r'. What is the result?

        System.out.println(replaceTest.replace('t', 'r'));

        // Question 25
        String trimExample = "   trim me  ";
        // How do you remove the leading and trailing spaces?

        System.out.println(trimExample.trim());

    }
}
