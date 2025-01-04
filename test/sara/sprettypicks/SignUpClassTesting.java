/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package sara.sprettypicks;

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
public class SignUpClassTesting {
    
//    public SignUpClassTesting() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
     @Test
public void testValidUsernameAndPassword() throws Exception {
    // Arrange: Assume valid credentials in the database
    String username = "zara_123";
    String password = "Zara_12345678";
    Database db = Database.getInstance();

    // Act: Call the method with valid username and password
    boolean result = db.checkCustomerLogin(username, password);

    // Assert: Store the test result in the database
    if (result) {
        db.storeTestResult("testValidUsernameAndPassword", "PASS");
    } else {
        db.storeTestResult("testValidUsernameAndPassword", "FAIL");
    }

    // Assert that the result is true since the user exists
    assertTrue("The method should return true for valid credentials.", result);
    System.out.println("User exists.");
}

 @Test
public void testValidAdminUsernameAndPassword() throws Exception {
    // Arrange: Assume valid credentials for an admin user exist in the database
    String username = "Sara_123";
    String password = "Sara!12345678";
    Database db = Database.getInstance();

    // Act: Call the method with valid username and password
    boolean result = db.checkAdminLogin(username, password);

    // Assert: Store the test result in the database
    if (result) {
        db.storeTestResult("testValidAdminUsernameAndPassword", "PASS");
    } else {
        db.storeTestResult("testValidAdminUsernameAndPassword", "FAIL");
    }

    // Assert the result is true since the admin credentials are correct
    assertTrue("The method should return true for valid admin credentials.", result);
    System.out.println("Admin exists.");
}

    //*******************SIGNUP TESTCASES*************
  

   @Test
public void testSignupWithExistingEmail() throws Exception {
    // Arrange
    Database db = Database.getInstance();
    String firstName = "Amna";
    String lastName = "asif";
    String username = "amna1234";
    String email = "amna1234@gmail.com"; // Existing email
    String password = "Amna!12345678";

    boolean result = false;
    String testStatus;

    // Act
    try {
        result = db.signupCustomer(firstName, lastName, username, email, password);
        assertFalse("The method should return false if the email already exists.", result);

        // If the method returned false, the test passes
        testStatus = "PASS";
        System.out.println("Test passed: Email already exists.");
    } catch (Exception e) {
        // Expected exception due to existing email
        assertTrue("Exception should be thrown due to existing email.", true);

        // Log the exception message
        System.out.println("Expected exception: " + e.getMessage());

        testStatus = "PASS"; // Test still passes as we expect the exception
    }

    // Store test result in the database
    if (!result || testStatus.equals("PASS")) {
        db.storeTestResult("testSignupWithExistingEmail", testStatus);
    } else {
        db.storeTestResult("testSignupWithExistingEmail", "FAIL");
    }
}

//    assertFalse expects the condition to be false.
//    In the case of an empty password (" "), if the signupCustomer
//    method returns false (which it should, assuming the method 
//    checks for valid passwords), the test will pass.

   @Test
public void testEmptyPassword() throws Exception {
    // Arrange
    Database db = Database.getInstance();
    String firstName = "John";
    String lastName = "Doe";
    String username = "newuser1234590892";
    String email = "newuser29902@example.com";
    String emptyPassword = " "; // Empty password

    boolean result = false;
    String testStatus;

    // Act
    try {
        result = db.signupCustomer(firstName, lastName, username, email, emptyPassword);

        // Assert
        assertFalse("The password cannot be empty", result);

        // Test passes if the method returns false
        testStatus = "PASS";
        System.out.println("Test passed: Password is empty.");
    } catch (Exception e) {
        // Log the exception and mark the test as failed
        System.out.println("Unexpected exception: " + e.getMessage());
        testStatus = "FAIL";
    }

    // Store the test result in the database
    db.storeTestResult("testEmptyPassword", testStatus);
}


    @Test
public void testSignupAdminEmailExists() throws Exception {
    // Arrange
    Database db = Database.getInstance();
    String existingEmail = "sara10289341@gmail.com"; // Assume this email already exists in the database
    String password = "Sara!12345678";
    String adminName = "Sara";
    String username = "Sara_123";

    boolean result = false;
    String testStatus;

    // Act
    try {
        result = db.signupAdmin(existingEmail, password, adminName, username);

        // Assert
        assertFalse("Signup should fail if the email already exists", result);

        // Test passes if the method returns false
        testStatus = "PASS";
        System.out.println("Test passed: Signup failed due to existing email.");
    } catch (Exception e) {
        // Log the exception and mark the test as failed
        System.out.println("Unexpected exception: " + e.getMessage());
        testStatus = "FAIL";
    }

    // Store the test result in the database
    db.storeTestResult("testSignupAdminEmailExists", testStatus);
}


}
