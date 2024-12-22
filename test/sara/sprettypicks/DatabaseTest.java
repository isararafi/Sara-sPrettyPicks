package sara.sprettypicks;

import org.junit.Test; // Import for JUnit 4 Test annotation
import static org.junit.Assert.*; // Import assertions for JUnit 4 (assertTrue, assertFalse, etc.)
import java.sql.Connection; // Import for SQL Connection
import java.sql.SQLException; // Import for SQL exceptions



/**
 *
 * @author sarar
 */
public class DatabaseTest {
//TESTCASE WILL FAIL IF DATABASE URL IS INCORRECT OR If any exception occurs during the 
//connection attempt (e.g., database server is down, incorrect credentials, network issues)
    @Test
  public void testDatabaseConnection() {
    Database dbInstance = Database.getInstance();
    String testName = "testDatabaseConnection";
    String result;
    String message;

    try (Connection connection = dbInstance.connect()) {
        // Assert that the connection is not null
        assertNotNull("Connection should not be null.", connection);
        // Assert that the connection is valid within 2 seconds
        assertTrue("Connection should be valid.", connection.isValid(2));
        
        result = "SUCCESS";
        message = "Connection with Database is Successful";
        System.out.println(message);
    } catch (SQLException e) {
        // If an exception occurs, record the failure
        result = "FAILURE";
        message = "An exception occurred while connecting to the database: " + e.getMessage();
        fail(message);
    }

    // Store the test result in the database
    dbInstance.storeTestResult("Database Connection","PASS");
}

    
   



}
