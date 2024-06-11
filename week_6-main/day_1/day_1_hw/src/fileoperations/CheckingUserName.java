package fileoperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckingUserName {

    public static boolean checkingForUsername(String username){
        try {
            File file = new File("src/credentials.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(username)){
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error!! File not found");
            e.printStackTrace();
        }
        System.out.println("Nothing, :(");
        return false;
    }
}
