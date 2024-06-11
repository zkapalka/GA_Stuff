package domain.WelcomeMenuOptions;

import domain.WordConstructor.Word;

import java.util.List;
import java.util.Scanner;

import static domain.dictionaryUtilities.DictionaryUtilities.updateDictionaryFile;

public class WelcomeMenuOption7 {
    public static void deleteWordFromDictionary(List<Word> dictionaryList, Scanner scanner) {
        System.out.println();
        System.out.println("Please enter the word you would like to delete: ");
        String word = scanner.nextLine().trim().toLowerCase();
        boolean found = false; // Flag to track if any matching entry is found

        // Filter the entries of the list to find all occurrences of the word
        for (Word wordObject : dictionaryList) {
            if (wordObject.getWord().equalsIgnoreCase(word)) { // Check if the word matches
                System.out.println();
                System.out.println("Word: " + wordObject.getWord());
                System.out.println("Definition: " + wordObject.getDefinition());
                System.out.println("Part of Speech: " + wordObject.getPart_of_speech());
                System.out.println("Example Usage: " + wordObject.getExample_usage());
                found = true; // Set the flag to true since at least one matching entry is found
            }
        }
        System.out.println();
        System.out.println("Please select either option for deletion");
        System.out.println("=============");
        System.out.println("1. Delete all instances of of " + word);
        System.out.println("2. Cancel your deletion request");

        int choice;

        //Ensures that the user input a number for options
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println();
            System.out.println("Invalid input. Please enter a number.");
            System.out.println("Press enter to continue");
            scanner.nextLine();
            return;
        }
        switch (choice) {
            case 1:
                dictionaryList.removeIf(wordObject -> wordObject.getWord().equalsIgnoreCase(word));
                System.out.println("All instances of " + word + " deleted");
                break;
            case 2:
                System.out.println();
                System.out.println("Canceling your deletion request");
                break;
            default:
                System.out.println("not a valid choice, please try again");
        }


        //Option 1 needs to be run outside the loop otherwise it will force an error
        updateDictionaryFile(dictionaryList);

        // If no matching entry is found, print a message
        if (!found) {
            System.out.println("Word \"" + word + "\" not found in the dictionary.");
        }

        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();
    }
}

