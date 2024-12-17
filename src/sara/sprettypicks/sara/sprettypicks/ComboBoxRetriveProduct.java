

package sara.sprettypicks;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class ComboBoxRetriveProduct {

    // Method to get the product ID from the database based on the selected product name
    public int getProductIdFromSelectedItem(String selectedProduct) {
        // Check if the selected product is null or empty
        if (selectedProduct == null || selectedProduct.isEmpty()) {
            System.out.println("Selected product is null or empty.");
            return -1; // Return -1 if no product is selected
        }

        // Normalize the selected product (trim spaces)
        String normalizedProduct = selectedProduct.trim();

        // Database query to retrieve the product ID
        String query = "SELECT product_id FROM products WHERE name = ?";

        // Create an instance of your Database class to connect to the database
         Database db = Database.getInstance();
        try (Connection conn = db.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the product name in the SQL query
            pstmt.setString(1, normalizedProduct);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Check if the result set contains a product ID
            if (rs.next()) {
                // Return the product ID
                return rs.getInt("product_id");
            } else {
                // If no matching product was found
                System.out.println("No product found with the name: " + normalizedProduct);
                return -1;
            }

        } catch (SQLException ex) {
            // Handle SQL exceptions
            System.err.println("Error retrieving product ID: " + ex.getMessage());
            return -1;
        }
    }
    
    public void populateProductComboBox(JComboBox<String> productComboBox) {
        // SQL query to get all product names from the 'products' table
        Database db = Database.getInstance();
        String query = "SELECT name FROM products";

        try (Connection conn = db.connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Clear the combo box before adding items (if needed)
            productComboBox.removeAllItems();

            // Loop through the result set and add each product name to the combo box
            while (rs.next()) {
                String productName = rs.getString("name");
                productComboBox.addItem(productName); // Add product name to the combo box
            }

        } catch (SQLException ex) {
            System.err.println("Error retrieving products: " + ex.getMessage());
        }
    }
}
