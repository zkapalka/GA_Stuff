package domain.WelcomeMenuOptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static domain.WelcomeMenuOptions.WelcomeMenu.inputHistory;

public class WelcomeMenuOption8 extends WelcomeMenuManager {




    //For Option 8 in menu
    public static void displayHistory() {
        if (inputHistory.size() == 0) {
            System.out.println();
            System.out.println("You do not have any input history yet!!");
        } else {
            System.out.println();
            System.out.println("Input history: ");
            for (int i = 0; i < inputHistory.size(); i++) {
                LocalDateTime timestamp = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedTimestamp = timestamp.format(formatter);

                System.out.println((i + 1)+".) " +  inputHistory.get(i));
            }
        }
        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();
    }

    public static void addChoicetoHistory(int choice) {
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);

        inputHistory.add(formattedTimestamp + " - Option " + choice);
    }
}
