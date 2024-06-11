package domain.WelcomeMenuOptions;

import domain.WordConstructor.Word;

import java.util.List;
import java.util.Scanner;

import static domain.dictionaryUtilities.DictionaryUtilities.updateDictionaryFile;

public class WelcomeMenuOption6 {

    public static void addWordToDictionary(List<Word> dictionaryLIst, Scanner scanner) {
        System.out.println();
        System.out.println("Enter the word you want to add: ");
        String word = scanner.nextLine().trim().toLowerCase();

        System.out.println();
        System.out.println("Enter the definition you want to add: ");
        String definition = scanner.nextLine().trim().toLowerCase();

        System.out.println();
        System.out.println("Enter the part of speech you want to add: ");
        String partofspeech = scanner.nextLine().trim().toLowerCase();

        System.out.println();
        System.out.println("Enter the example usage you want to add: ");
        String exampleUsage = scanner.nextLine().trim().toLowerCase();

        Word newWord = new Word (word, definition, partofspeech, exampleUsage);
        //Add new word to variable
        dictionaryLIst.add(newWord);
        //Add word to dictionary.txt
        updateDictionaryFile(dictionaryLIst);

        System.out.println();
        System.out.println("Please press Enter to return to the main menu.");
        scanner.nextLine();
    }
}
