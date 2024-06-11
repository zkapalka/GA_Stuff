package domain.WelcomeMenuOptions;

import domain.WordConstructor.Word;

import java.util.List;
import java.util.Scanner;

public class WelcomeMenuOption1 extends WelcomeMenuManager {

    //For Option 1 in menu
    public static void searchForWords(List<Word> dictionaryList, Scanner scanner) {
        System.out.println();
        System.out.println("Please enter the word you would like to search for: ");
        String word = scanner.nextLine().trim().toLowerCase();

        // Use stream to filter and print matching words
        dictionaryList.stream()
                .filter(wordObject -> wordObject.getWord().equalsIgnoreCase(word))
                .forEach(wordObject -> {
                    System.out.println();
                    System.out.println("Word: " + wordObject.getWord());
                    System.out.println("Definition: " + wordObject.getDefinition());
                    System.out.println("Part of Speech: " + wordObject.getPart_of_speech());
                    System.out.println("Example Usage: " + wordObject.getExample_usage());
                });

        // If no matching entry is found, print a message
        if (!dictionaryList.stream().anyMatch(wordObject -> wordObject.getWord().equalsIgnoreCase(word))) {
            System.out.println("Word \"" + word + "\" not found in the dictionary.");
        }

        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();
    }
}

