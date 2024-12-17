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
    private String customerUsername;
    private String reviewText;
    private String experience;

    public Reviewclass(int productId, String customerUsername, String reviewText, String experience) {
        this.productId = productId;
        this.customerUsername = customerUsername;
        this.reviewText = reviewText;
        this.experience = experience;
    }

    // Existing getters
    public int getProductId() { return productId; }
    public String getCustomerUsername() { return customerUsername; }
    public String getReviewText() { return reviewText; }
    public String getExperience() { return experience; } // New getter
}

