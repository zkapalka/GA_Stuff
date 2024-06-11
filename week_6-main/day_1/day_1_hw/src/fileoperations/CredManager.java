package fileoperations;
import java.io.IOException;
import java.util.Scanner;

import static fileoperations.AddUserName.addingUserToFile;
import static fileoperations.CheckingUserName.checkingForUsername;

public class CredManager {

    //This allows the  user to input different choices and with each choice you can make this method call on other
    // methods from other classes
    public static String getUserInput(String prompt) {
        System.out.println("Welcome to Kapalka Time app! Please select one of those options below");
        System.out.println("Press 1 to log in. Press 2 to sign-up");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        //Checking conditions for userInput
        if (userInput.equals("1")) {
            System.out.println("Checking for user name");
            String username = scanner.nextLine();
            if (checkingForUsername(username)) {
                System.out.println("Success!");
            }
        } else if (userInput.equals("2")) {
            System.out.println("Please input your user name that you want to use");
            String username = scanner.nextLine();
            try {
                addingUserToFile(username);
            } catch (IOException e) {
                System.out.println("Error adding user name to file: " + e.getMessage());
            }
        } else {
            System.out.println("not valid input, please enter 1 or 2");
        }

        return userInput;
    }
}

