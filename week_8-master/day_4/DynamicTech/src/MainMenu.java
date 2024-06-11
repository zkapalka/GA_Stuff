import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            try {
                switch (choice) {
                    case 1:
                        searchEmployeeByName();
                        break;
                    case 2:
                        // Add employee functionality
                        break;
                    case 3:
                        // Update employee functionality
                        break;
                    case 4:
                        // Delete employee functionality
                        break;
                    case 5:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void printMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Search Employee by Name");
        System.out.println("2. Create Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void searchEmployeeByName() throws SQLException {
        System.out.print("Enter the name of the employee: ");
        String name = scanner.nextLine();

        // Call the getEmployeeByName method with the provided name
        // Employee employee = Employee.getEmployeeByName(name);

        // Display the retrieved employee information
        // if (employee != null) {
        //     System.out.println("Employee found:");
        //     System.out.println("Employee ID: " + employee.getEmployeeID());
        //     System.out.println("Full Name: " + employee.getFullName());
        //     System.out.println("Department ID: " + employee.getDepartmentID());
        // } else {
        //     System.out.println("Employee not found.");
        // }
    }
}
