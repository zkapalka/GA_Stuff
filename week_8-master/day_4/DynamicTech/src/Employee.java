import java.sql.*;
public class Employee {
    private int employeeID;
    private String fullName;
    private int departmentID;

    // Constructor
    public Employee(int employeeID, String fullName, int departmentID) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.departmentID = departmentID;
    }

    // Getters
    public int getEmployeeID() {
        return employeeID;
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    // Setters (Optional, if you need to modify attributes)
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    // Other methods can be added as needed

    @Override
    public String toString() {
        return "Employee ID: " + employeeID + ", FullName: " + fullName + ", DepartmentID: " + departmentID;
    }

    public static Employee getEmployeeByName(String name) throws SQLException {
        // Database connection parameters
        String url = "jdbc:postgresql://localhost:5432/yourDatabase";
        String username = "yourUsername";
        String password = "yourPassword";

        // SQL query to retrieve employee by name
        String sql = "SELECT * FROM Employee WHERE FullName = ?";

        try (
                // Establish database connection
                Connection connection = DriverManager.getConnection(url, username, password);
                // Create prepared statement
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            // Set the name parameter in the prepared statement
            statement.setString(1, name);

            // Execute query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Check if a record was found
                if (resultSet.next()) {
                    // Retrieve values from the result set
                    int employeeID = resultSet.getInt("EmployeeID");
                    String fullName = resultSet.getString("FullName");
                    int departmentID = resultSet.getInt("DepartmentID");

                    // Create and return Employee object
                    return new Employee(employeeID, fullName, departmentID);
                } else {
                    // No record found with the given name
                    return null;
                }
            }
        }
    }
}
