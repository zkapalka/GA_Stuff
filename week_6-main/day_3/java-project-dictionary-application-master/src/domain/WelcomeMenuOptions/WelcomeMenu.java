package domain.WelcomeMenuOptions;

import domain.WordConstructor.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static domain.dictionaryUtilities.DictionaryUtilities.convertDictionaryToListVariable;
import static domain.WelcomeMenuOptions.WelcomeMenuOption1.searchForWords;
import static domain.WelcomeMenuOptions.WelcomeMenuOption2.searchForWordsByDefinition;
import static domain.WelcomeMenuOptions.WelcomeMenuOption3.searchForWordsByPrefix;
import static domain.WelcomeMenuOptions.WelcomeMenuOption4.searchForWordsBySuffix;
import static domain.WelcomeMenuOptions.WelcomeMenuOption5.searchForWordsBySubstring;
import static domain.WelcomeMenuOptions.WelcomeMenuOption6.addWordToDictionary;
import static domain.WelcomeMenuOptions.WelcomeMenuOption7.deleteWordFromDictionary;
import static domain.WelcomeMenuOptions.WelcomeMenuOption8.addChoicetoHistory;
import static domain.WelcomeMenuOptions.WelcomeMenuOption8.displayHistory;
import static domain.WelcomeMenuOptions.WelcomeMenuOption9.displayCreatorInfo;

public class WelcomeMenu {
    //VARIABLES
    private static Scanner scanner = new Scanner(System.in);
    public static final List<String> inputHistory = new ArrayList<>();

    //METHOD

    //The welcome menu that will open when this program is ran
    public static void printWelcomeMenu() {

        //Init the variable that will hold the list variable from the dictionary.txt file
        List <Word> dictionaryList;
        {
            try {
                dictionaryList = convertDictionaryToListVariable();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        while (true) {
        System.out.println("****************************************************");
        System.out.println("Welcome To the Kapalka Dictionary App!");
        System.out.println();
        System.out.println("1. Find a word(s)");
        System.out.println("2. Find words by definition");
        System.out.println("3. Find all words that start with -");
        System.out.println("4. Find all words that end with -");
        System.out.println("5. Find all words containing -");
        System.out.println("6. Add a word");
        System.out.println("7. Delete a word");
        System.out.println("8. History");
        System.out.println("9. Creator");
        System.out.println("10. Exit");
        System.out.println("****************************************************");

        System.out.println("Please make your selection");

        //Store number pressed
        int choice;

        //Ensures that the user input a number for options
        try {
                choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input. Please enter a number.");
                System.out.println("Press enter to continue");
                scanner.nextLine();
                continue;
        }

        //Runs the relevant method when the number is picked
        switch (choice) {
            case 1:
                searchForWords(dictionaryList, scanner);
                break;
            case 2:
                searchForWordsByDefinition(dictionaryList, scanner);
                break;
            case 3:
                searchForWordsByPrefix(dictionaryList, scanner);
                break;
            case 4:
                searchForWordsBySuffix(dictionaryList, scanner);
                break;
            case 5:
                searchForWordsBySubstring(dictionaryList, scanner);
                break;
            case 7:
                deleteWordFromDictionary(dictionaryList, scanner);
                break;
            case 6:
                addWordToDictionary(dictionaryList, scanner);
                break;
            case 8:
                displayHistory();
                break;
            case 9:
                displayCreatorInfo();
                break;
            case 10:
                System.out.println("Quitting the program, Smell ya later!");
                System.exit(0);
            default:
                System.out.println("Not a valid choice, try again.");
                System.out.println("Press Enter to continue");
                scanner.nextLine();
            }

            //Add input to inputHistory for option 8
            //Added after switch since I want to make sure that displayHistory will output correctly
            addChoicetoHistory(choice);
        }
    }
}
