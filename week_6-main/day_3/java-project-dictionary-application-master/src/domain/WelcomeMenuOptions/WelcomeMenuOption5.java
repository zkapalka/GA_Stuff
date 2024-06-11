package domain.WelcomeMenuOptions;

import domain.WordConstructor.Word;

import java.util.List;
import java.util.Scanner;

public class WelcomeMenuOption5 {
    public static void searchForWordsBySubstring(List<Word> dictionaryList, Scanner scanner) {
        System.out.println();
        System.out.println("Please enter the substring you would like to search for: ");
        String substring = scanner.nextLine().trim().toLowerCase();

        // Use stream to filter and print words that contain the given substring
        dictionaryList.stream()
                .filter(wordObject -> wordObject.getWord().toLowerCase().contains(substring))
                .forEach(wordObject -> {
                    System.out.println();
                    System.out.println("Word: " + wordObject.getWord());
                    System.out.println("Definition: " + wordObject.getDefinition());
                    System.out.println("Part of Speech: " + wordObject.getPart_of_speech());
                    System.out.println("Example Usage: " + wordObject.getExample_usage());
                });

        // If no matching entry is found, print a message
        if (!dictionaryList.stream().anyMatch(wordObject -> wordObject.getWord().toLowerCase().contains(substring))) {
            System.out.println("No word containing the substring \"" + substring + "\" was found in the dictionary.");
        }

        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();
    }
}