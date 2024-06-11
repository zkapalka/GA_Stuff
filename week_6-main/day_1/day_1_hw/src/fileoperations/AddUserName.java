package fileoperations;

import java.io.FileWriter;
import java.io.IOException;
import static fileoperations.CheckingUserName.checkingForUsername;

public class AddUserName extends CheckingUserName {

    //This will add username to the file
    public static void addingUserToFile(String username) throws IOException {

        if (checkingForUsername(username)) {
            System.out.println("Username '" + username + "' already exists. Please use a different username.");
            return;
        } else {
            FileWriter writer = new FileWriter("src/credentials.txt", true);
            writer.write(username + "\n");
            writer.close();
            System.out.println("Your username: " + username + " has been successfully added to the credentials file!");
        }
    }
}
