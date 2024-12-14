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
//TESTCASE WILL FAIL IF DATABASE URL IS INCORRECT OR If any exception occurs during the connection attempt (e.g., database server is down, incorrect credentials, network issues),
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
    
//    @Test
// public void testValidUsernameAndPassword() throws Exception {
//    // Assume valid credentials in the database
//    Database db = Database.getInstance();
//
//    // Call the method with valid username and password
//    boolean result = db.checkCustomerLogin("aleena123", "Aleena_12345678");
//        
//
//    // Assert the result is true since the user exists
//  assertTrue( "The method should return true for valid credentials.",result);
//  System.out.println("User exist.");
//}
// @Test
//public void testValidAdminUsernameAndPassword() throws Exception {
//    // Assume valid credentials for an admin user exist in the database
//    Database db = Database.getInstance();
//
//    // Call the method with valid username and password
//    boolean result = db.checkAdminLogin("Sara_123", "Sara_12345678");
//
//    // Assert the result is true since the admin credentials are correct
//    assertTrue( "The method should return true for valid admin credentials.",result);
//     System.out.println("Admin Exist");
//}

    
    //*******************SIGNUP TESTCASES*************
  

//@Test
//    public void testSignupWithExistingEmail() throws Exception {
//        // Assume the email 'john.doe@example.com' already exists in the database
//        Database db = Database.getInstance();
//
//        // Call the method with an existing email
//        try {
//            boolean result = db.signupCustomer("John", "Doe", "newuser123", "john.doe@example.com", "password123");
//            // If the method doesn't throw an exception, assert that it should not succeed with an existing email
//             System.out.println("user existtt");
//            assertFalse("The method should return false if the email already exists.", result);
//        } catch (Exception e) {
//            // Expected exception thrown due to existing email
//            assertTrue("Exception should be thrown due to existing email.", true);
//        }
//    }
    
    //assertFalse expects the condition to be false.
    //In the case of an empty password (" "), if the signupCustomer
    //method returns false (which it should, assuming the method 
    //checks for valid passwords), the test will pass.

//    @Test
//    public void testEmptyPassword() throws Exception {
//        Database db = Database.getInstance();
//
//        // Call the method with an empty password
//        boolean result = db.signupCustomer("John", "Doe", "newuser12345", "newuser29@example.com", " ");
//
//        // Assert the result is false since the password is empty
//        assertFalse("The password cannot be empty", result);
//    }

//    @Test
//public void testSignupAdminEmailExists() throws Exception {
//    Database db = Database.getInstance();
//
//    // Assume 'admin@example.com' already exists in the database
//    boolean result = db.signupAdmin("sara102@gmail.com", "newpassword123", "New Admin", "newadminuser");
//
//    // Assert that the result is false as the email already exists
//    assertFalse("Signup should fail if the email already exists", result);
//}

    // Test Case 1: Username does not exist (returns null)
//    @Test
//    public void testGetEmailByUsernameNotFound() throws SQLException {
//        Database db=Database.getInstance();
//        String username = "rawan123"; // A username that doesn't exist in the database
//
//        String email = db.getEmailByUsername(username);
//
//        // Verify that the email is null as the user does not exist
//        assertNull("The email should be null for a non-existent user", email);
//    }



}
