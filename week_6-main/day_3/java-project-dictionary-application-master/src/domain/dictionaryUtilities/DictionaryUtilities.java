package domain.dictionaryUtilities;

import domain.WordConstructor.Word;

import java.io.*;
import java.util.*;

public class DictionaryUtilities {

    //DECLARING VARIABLES
    private static final String FILE_NAME = "dictionary.txt";
    private static final String FILE_DIRECTORY = "lib";

    //METHODS

    //Pulling txt file into a list variable and returning that variable
    //This method will use the doesFileExist method to make sure that the file exists before running
    public static List<Word> convertDictionaryToListVariable() throws IOException {
        List<Word> dictionaryList = new ArrayList<>();
        if (doesFileExist()) {
            String filePath = FILE_DIRECTORY + File.separator + FILE_NAME;
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 4) {
                    String word = parts[0].trim();
                    String definition = parts[1].trim();
                    String part_of_speech = parts[2].trim();
                    String example_usage = parts[3].trim();
                    Word wordObject = new Word(word, definition, part_of_speech, example_usage);
                    dictionaryList.add(wordObject);
                } else {
                    System.err.println("Skipping line: " + line + " (insufficient fields)");
                }
            }
            reader.close();
        } else {
            System.err.println("Dictionary file does not exist.");
        }
        return dictionaryList;
    }

    public static void updateDictionaryFile(List<Word> dictionaryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DIRECTORY + "/" + FILE_NAME))) {
            for (Word word : dictionaryList) {
                writer.write(word.getWord() + " | " + word.getDefinition() + " | " + word.getPart_of_speech() + " | " + word.getExample_usage());
                writer.newLine();
            }
            System.out.println("Dictionary file updated successfully.");
        } catch (IOException e) {
            System.err.println("Error updating dictionary file: " + e.getMessage());
        }
    }

    //Checking to see if dictionary file exists
    public static boolean doesFileExist () throws FileNotFoundException {
        File file = new File (FILE_DIRECTORY, FILE_NAME);
        if (file.exists()){
            return true;
        }
        else {
            throw new FileNotFoundException("File does not exist, sorry");
        }
    }
}
