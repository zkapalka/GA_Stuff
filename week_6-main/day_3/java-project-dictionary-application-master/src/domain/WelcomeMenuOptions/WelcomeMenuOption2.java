package domain.WelcomeMenuOptions;
import domain.WordConstructor.Word;

import java.util.List;
import java.util.Scanner;

public class WelcomeMenuOption2 extends WelcomeMenuManager {

    public static void searchForWordsByDefinition(List<Word> dictionaryList, Scanner scanner) {
        System.out.println();
        System.out.println("Please enter the definition you would like to search for: ");
        String definition = scanner.nextLine().trim().toLowerCase();

        // Use stream to filter and print matching words by definition
        dictionaryList.stream()
                .filter(wordObject -> wordObject.getDefinition().toLowerCase().contains(definition))
                .forEach(wordObject -> {
                    System.out.println();
                    System.out.println("Word: " + wordObject.getWord());
                    System.out.println("Definition: " + wordObject.getDefinition());
                    System.out.println("Part of Speech: " + wordObject.getPart_of_speech());
                    System.out.println("Example Usage: " + wordObject.getExample_usage());
                });

        // If no matching entry is found, print a message
        if (!dictionaryList.stream().anyMatch(wordObject -> wordObject.getDefinition().toLowerCase().contains(definition))) {
            System.out.println("No word found with the definition \"" + definition + "\" in the dictionary.");
        }

        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();
    }
}
