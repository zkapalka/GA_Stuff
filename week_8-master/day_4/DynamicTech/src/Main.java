import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.*;

public class Main {
    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        mainMenu.displayMenu();
    }

//        Properties properties = new Properties();
//        try (InputStream input = new FileInputStream("Resources/Properties")) {
//            properties.load(input);
//
//            // Retrieve database connection properties
//            String url = properties.getProperty("db.url");
//            String username = properties.getProperty("db.username");
//            String password = properties.getProperty("db.password");
//
//            // Establish connection
//            Connection connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected to PostgreSQL database!");
//
//            // Perform database operations here...
//
//            // Close connection
//            connection.close();
//        } catch (IOException | SQLException e) {
//            System.err.println("Connection failed!");
//            e.printStackTrace();
//        }
//    }
}
