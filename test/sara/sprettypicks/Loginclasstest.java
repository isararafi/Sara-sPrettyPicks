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
public class Loginclasstest {
    
    public Loginclasstest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    Database db=Database.getInstance();
     @Test
    public void testSuccessfulCustomerLogin() {
        try {
            // Fetch valid customer credentials from the database
            Database db = Database.getInstance();
            String[] credentials = db.getValidCustomerCredentials();

            if (credentials[0] == null || credentials[1] == null) {
                db.storeTestResult("testSuccessfulCustomerLogin", "FAIL");
                return;
            }

            String username = credentials[0];
            String password = credentials[1];

            // Perform the login check using the fetched credentials
            boolean result = db.checkCustomerLogin(username, password);

            if (result) {
                db.storeTestResult("testSuccessfulCustomerLogin", "PASS");
            } else {
                db.storeTestResult("testSuccessfulCustomerLogin", "FAIL");
            }
        } catch (Exception e) {
            db.storeTestResult("testSuccessfulCustomerLogin", "ERROR");
        }
    }

    @Test
    public void testCheckAdminLoginValid() throws SQLException {
        String username = "Sara_123";
        String password = "Sara!12345678";
        Database db = Database.getInstance();

        boolean result = db.checkAdminLogin(username, password);

        // Assert: The result should be true as the username and password are valid
        if (result) {
            db.storeTestResult("testCheckAdminLoginValid", "PASS");
        } else {
            db.storeTestResult("testCheckAdminLoginValid", "FAIL");
        }
    }

    @Test
    public void testCheckCustomerLogin_SuccessfulLogin() throws SQLException {
        String username = "zara_123";
        String password = "Zara_12345678";

        Database db = Database.getInstance();
        boolean result = db.checkCustomerLogin(username, password);

        // Assert: Verify the result
        if (result) {
            db.storeTestResult("testCheckCustomerLogin_SuccessfulLogin", "PASS");
        } else {
            db.storeTestResult("testCheckCustomerLogin_SuccessfulLogin", "FAIL");
        }
    }

//    @Test
//    public void testGetEmailByUsernameNotFound() throws SQLException {
//        Database db = Database.getInstance();
//        String username = "rawan_123";  // A username that doesn't exist in the database
//
//        String email = db.getEmailByUsername(username);
//
//        // Print statement to see the value of the email retrieved
//        System.out.println("Email for username '" + username + "': " + email);
//
//        // Assert: Verify that the email is null for a non-existent user
//        assertNull("The email should be null for a non-existent user", email);
//
//        // Store the test result
//        db.storeTestResult("testGetEmailByUsernameNotFound", email == null ? "PASS" : "FAIL");
//    }
}
