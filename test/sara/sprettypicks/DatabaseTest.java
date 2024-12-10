package sara.sprettypicks;

import org.junit.Test; // Import for JUnit 4
import static org.junit.Assert.*; // Import assertions for JUnit 4
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sarar
 */
public class DatabaseTest {

    @Test
    public void testDatabaseConnection() {
        Database dbInstance = Database.getInstance();
        try (Connection connection = dbInstance.connect()) {
            // Assert that the connection is not null
            assertNotNull("Connection should not be null.", connection);
            // Assert that the connection is valid within 2 seconds
            assertTrue("Connection should be valid.", connection.isValid(2));
            System.out.println("Connection with Database is Successful");
        } catch (SQLException e) {
            // If an exception occurs, fail the test
            fail("An exception occurred while connecting to the database: " + e.getMessage());
        }
    }
}
