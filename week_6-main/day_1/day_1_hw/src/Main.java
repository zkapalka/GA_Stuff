import javax.swing.*;
import java.io.FileNotFoundException;

import static fileoperations.CheckFileExistence.doesFileExist;
import static fileoperations.CredManager.getUserInput;

public class Main {
    public static void main(String[] args) {

//        // This will determine if the file in question exists.
//        System.out.println();
//        System.out.println("Checking the existence of the credentials.txt file");
//        String fileName = "credentials.txt";
//        try {
//            if (doesFileExist(fileName)) {
//                System.out.println("File '" + fileName + "' exists in the src directory.");
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        System.out.println();
//        //This will welcome the user and kick start the whole jazz
//        getUserInput("test");


//        JTextField textField = new JTextField(20);
//        JPanel panel = new JPanel();
//        panel.add(textField);
//        int result = JOptionPane.showConfirmDialog(null, panel, "Please enter a value", JOptionPane.OK_CANCEL_OPTION);
//        if (result == JOptionPane.OK_OPTION) {
//            String input = textField.getText();
//            String password_input = textField.getText();
//            System.out.println("User input: " + input);
//            System.out.println("Password: " + password_input);
//        }

        JTextField usernameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Do something with username and password
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

        }
    }}



