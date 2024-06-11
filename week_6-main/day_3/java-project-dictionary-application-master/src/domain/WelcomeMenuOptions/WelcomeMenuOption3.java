package domain.WelcomeMenuOptions;

import domain.WordConstructor.Word;

import java.util.List;
import java.util.Scanner;

public class WelcomeMenuOption3 extends WelcomeMenuManager {

    public static void searchForWordsByPrefix(List<Word> dictionaryList, Scanner scanner) {
        System.out.println();
        System.out.println("Please enter the prefix of the word that you would like to look for: ");
        String prefix = scanner.nextLine().trim().toLowerCase();

        // Use stream to filter and print words that start with the given prefix
        dictionaryList.stream()
                .filter(wordObject -> wordObject.getWord().toLowerCase().startsWith(prefix))
                .forEach(wordObject -> {
                    System.out.println();
                    System.out.println("Word: " + wordObject.getWord());
                    System.out.println("Definition: " + wordObject.getDefinition());
                    System.out.println("Part of Speech: " + wordObject.getPart_of_speech());
                    System.out.println("Example Usage: " + wordObject.getExample_usage());
                });

        // If no matching entry is found, print a message
        if (!dictionaryList.stream().anyMatch(wordObject -> wordObject.getWord().toLowerCase().startsWith(prefix))) {
            System.out.println("No word found that starts with \"" + prefix + "\" in the dictionary.");
        }

        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();
    }
}