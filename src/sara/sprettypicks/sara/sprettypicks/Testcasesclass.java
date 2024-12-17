package sara.sprettypicks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Testcasesclass {

    private JTable testCaseTable; // This will be set via the constructor

    // Constructor that accepts the JTable from the main GUI
    public Testcasesclass(JTable testCaseTable) {
        this.testCaseTable = testCaseTable;
    }

    // Method to display test cases when called (e.g., on a button click)
    public void displayTestCases() {
        // Get the test cases from the database
        String[][] testCases = fetchTestCasesFromDatabase();

        // Set the column names for the JTable
        String[] columnNames = {"Test Case Name", "Result"};

        // Create a DefaultTableModel with the data and column names
        DefaultTableModel model = new DefaultTableModel(testCases, columnNames);

        // Set the model for your JTable
        testCaseTable.setModel(model);
    }

    // Method to fetch test cases from the database
    private String[][] fetchTestCasesFromDatabase() {
    String[][] testCases = new String[0][0];  // Placeholder for the test case data
    String query = "SELECT test_case_name, result FROM test_case_results";

    try (Connection conn = Database.getInstance().connect();  
         // Create a statement with scrollable result set
         Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = stmt.executeQuery(query)) {

        // Get the number of rows from the result set
        rs.last();  // Moves the cursor to the last row to get row count
        int rowCount = rs.getRow();  // Get row count
        rs.beforeFirst();  // Move the cursor back to the first row

        // Initialize the 2D array to hold the test case data
        testCases = new String[rowCount][2];
        int rowIndex = 0;

        // Loop through the result set and populate the 2D array
        while (rs.next()) {
            testCases[rowIndex][0] = rs.getString("test_case_name"); // Test case name
            testCases[rowIndex][1] = rs.getString("result"); // Test case result
            rowIndex++;
        }

    } catch (SQLException e) {
        e.printStackTrace();  // Handle exceptions properly
    }

    return testCases;
}

}
