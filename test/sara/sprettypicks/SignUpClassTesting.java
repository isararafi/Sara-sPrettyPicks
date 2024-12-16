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
    // Assume valid credentials in the database
    Database db = Database.getInstance();

    // Call the method with valid username and password
    boolean result = db.checkCustomerLogin("zara_123", "Zara_12345678");
        

    // Assert the result is true since the user exists
  assertTrue( "The method should return true for valid credentials.",result);
  System.out.println("User exist.");
}
 @Test
public void testValidAdminUsernameAndPassword() throws Exception {
    // Assume valid credentials for an admin user exist in the database
    Database db = Database.getInstance();

    // Call the method with valid username and password
    boolean result = db.checkAdminLogin("Sara_123", "Sara!12345678");

    // Assert the result is true since the admin credentials are correct
    assertTrue( "The method should return true for valid admin credentials.",result);
     System.out.println("Admin Exist");
}

    
    //*******************SIGNUP TESTCASES*************
  

//    @Test
//    public void testSignupWithExistingEmail() throws Exception {
//        // Assume the email 'john.doe@example.com' already exists in the database
//        Database db = Database.getInstance();
//
//        // Call the method with an existing email
//        try {
//            boolean result = db.signupCustomer("John", "Doe", "newuser1234", "john.doe@example.com", "password123");
//            // If the method doesn't throw an exception, assert that it should not succeed with an existing email
//             System.out.println("user existtt");
//            assertFalse("The method should return false if the email already exists.", result);
//        } catch (Exception e) {
//            // Expected exception thrown due to existing email
//            assertTrue("Exception should be thrown due to existing email.", true);
//        }
//    }
    
//    assertFalse expects the condition to be false.
//    In the case of an empty password (" "), if the signupCustomer
//    method returns false (which it should, assuming the method 
//    checks for valid passwords), the test will pass.

//    @Test
//    public void testEmptyPassword() throws Exception {
//        Database db = Database.getInstance();
//
//        // Call the method with an empty password
//        boolean result = db.signupCustomer("John", "Doe", "newuser123459", "newuser29@example.com", " ");
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

}
