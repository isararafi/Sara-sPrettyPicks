package sara.sprettypicks;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// Assuming you have a Database class that manages connections
import sara.sprettypicks.Database;

/**
 * Represents a review submitted by a customer for a product.
 */
public class Reviewclass {
    private int productId;
    private String customerEmail;
    private String reviewText;

    // Constructor
    public Reviewclass(int productId, String customerEmail, String reviewText) {
        this.productId = productId;
        this.customerEmail = customerEmail;
        this.reviewText = reviewText;
    }

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getReviewText() {
        return reviewText;
    }

    // Method to submit a review
    
}
