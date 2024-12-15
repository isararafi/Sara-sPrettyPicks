/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sara.sprettypicks;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sarar
 */
public class LoginClassTesting {
    
    

     //Test Case 1: Username does not exist (returns null)
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
    
   @Test
public void testGetEmailByUsernameFound() throws SQLException {
    Database db = Database.getInstance();
    String username = "Amna_123"; // Username that exists in the database
    String expectedEmail = "amna102@gmail.com"; // Expected email for the username

    // Act: Call the method to get the email by username
    String email = db.getEmailByUsername(username);

    // Assert: Verify that the email matches the expected email for the user
    assertEquals("The email should match the expected email for the user", expectedEmail, email);
}

    @Test
    public void testCheckAdminLoginValid() throws SQLException {
        String username = "Sara_123";  // A valid admin username from the database
        String password = "Sara_12345678";  // The correct password for the username
Database db = Database.getInstance();
        // Act: Call the method to check the admin login
        boolean result = db.checkAdminLogin(username, password);

        // Assert: The result should be true as the username and password are valid
        assertTrue("Admin login should be successful with valid credentials", result);
    }
    
     @Test
    public void testCheckCustomerLogin_SuccessfulLogin() throws SQLException {
        // Arrange: Prepare the data for the test
        String username = "aleena123";
        String password = "Aleena_12345678";

       Database db=Database.getInstance();        // Act: Call the method you want to test
        boolean result = db.checkCustomerLogin(username, password);

        // Assert: Verify the result
        assertTrue("The login should be successful for a valid username and password.",result);
    }
}
