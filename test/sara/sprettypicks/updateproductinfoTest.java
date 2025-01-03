package sara.sprettypicks;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;

public class updateproductinfoTest {

   @Test
public void testUpdateProductEmptyFields() throws SQLException {
    // Arrange
    Database db = Database.getInstance();
    int invalidProductId = 0; // Invalid product ID (zero or negative is invalid)
    double productPrice = 0.0; // Zero price
    int productQuantity = 0; // Zero quantity

    // Act
    boolean result = db.updateProductInDatabase(invalidProductId, productPrice, productQuantity);

    // Assert
    assertFalse("Product update should fail for invalid or zero productId.", result);
    
    // Store result in database
    if (!result) {
        db.storeTestResult("testUpdateProductEmptyFields", "PASS");
    } else {
        db.storeTestResult("testUpdateProductEmptyFields", "FAIL");
    }
}


  @Test
public void testUpdateProductValidData() throws SQLException {
    // Arrange
    Database db = Database.getInstance();
    int productId = 4;
    double productPrice = 49.99;
    int productQuantity = 80;

    // Act
    boolean result = db.updateProductInDatabase(productId, productPrice, productQuantity);

    // Store test result in database
    if (result) {
        db.storeTestResult("testUpdateProductValidData", "PASS");
    } else {
        db.storeTestResult("testUpdateProductValidData", "FAIL");
    }

    // Assert
    assertTrue("Product update should succeed for valid data.", result);
}

}
