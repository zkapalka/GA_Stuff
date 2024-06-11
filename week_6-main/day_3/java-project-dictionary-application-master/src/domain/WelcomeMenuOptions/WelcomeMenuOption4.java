package domain.WelcomeMenuOptions;

import domain.WordConstructor.Word;

import java.util.List;
import java.util.Scanner;

public class WelcomeMenuOption4 {
    public static void searchForWordsBySuffix(List<Word> dictionaryList, Scanner scanner) {
        System.out.println();
        System.out.println("Please enter the suffix of the word that you would like to look for: ");
        String suffix = scanner.nextLine().trim().toLowerCase();

        // Use stream to filter and print words that end with the given suffix
        dictionaryList.stream()
                .filter(wordObject -> wordObject.getWord().toLowerCase().endsWith(suffix))
                .forEach(wordObject -> {
                    System.out.println();
                    System.out.println("Word: " + wordObject.getWord());
                    System.out.println("Definition: " + wordObject.getDefinition());
                    System.out.println("Part of Speech: " + wordObject.getPart_of_speech());
                    System.out.println("Example Usage: " + wordObject.getExample_usage());
                });

        // If no matching entry is found, print a message
        if (!dictionaryList.stream().anyMatch(wordObject -> wordObject.getWord().toLowerCase().endsWith(suffix))) {
            System.out.println("No word found that ends with \"" + suffix + "\" in the dictionary.");
        }

        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();;
    }
}
