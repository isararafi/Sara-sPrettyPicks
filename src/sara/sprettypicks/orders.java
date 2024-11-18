package sara.sprettypicks;



import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // This is necessary for Statement.RETURN_GENERATED_KEYS


public class orders {
public int storeOrderInDatabase(String userName, double totalBill, String shippingAddress) {
    int orderId = -1; // Default to -1 to indicate not found

    // SQL query to insert a new order
    String query = "INSERT INTO orders (user_name, total_amount, shipping_address, order_status) VALUES (?, ?, ?, ?)";

    try (Connection conn = Database.getInstance().connect()) {
        if (conn == null) {
            throw new SQLException("Failed to connect to the database.");
        }

        // Prepare the statement with the correct query to retrieve generated keys
        PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, userName);
        ps.setDouble(2, totalBill);
        ps.setString(3, shippingAddress);
        ps.setString(4, "Pending"); // Set the initial order status

        // Execute the insertion
        int affectedRows = ps.executeUpdate();
        if (affectedRows > 0) {
            // Retrieve the generated order ID
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1); // Get the order ID
                }
            }
        } else {
            System.out.println("Order insertion failed, no rows affected.");
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace for debugging
    }

    return orderId; // Return the order ID
}



    public void storeOrderItemsInDatabase(int orderId, List<CartItem> cartItems) throws SQLException {
        // Get the database connection
        Connection conn = Database.getInstance().connect(); // Use connect() method

        // SQL query to insert order items
        String query = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        
        // Prepare the statement
        PreparedStatement ps = conn.prepareStatement(query);

        // Loop through each cart item and set parameters
        for (CartItem item : cartItems) {
            ps.setInt(1, orderId);
            ps.setInt(2, item.getProductId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            ps.addBatch();  // Add to batch for batch execution
        }
        
        // Execute all insertions in one go
        ps.executeBatch();  
    }
    
 public String getOrderStatus(int orderId) {
    String orderStatus = null; // Initialize as null
    System.out.println("Fetching status for Order ID: " + orderId); // Debugging output

    try (Connection conn = Database.getInstance().connect()) {
        String query = "SELECT order_status FROM orders WHERE order_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, orderId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            orderStatus = rs.getString("order_status"); // Get the status from the result set
            if (orderStatus != null) {
                System.out.println("Order status found: " + orderStatus); // Debugging output
            } else {
                System.out.println("Order status is null for Order ID: " + orderId); // Debugging output
            }
        } else {
            System.out.println("No order found for ID: " + orderId); // Debugging output
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orderStatus; // Return the fetched status or null if not found
}








      // Method to get order ID by user email
   public int getOrderIdByUsername(String username) {
    int orderId = -1; // Default to -1 to indicate not found

    // Establish a connection to the database
    try (Connection conn = Database.getInstance().connect()) {
        if (conn == null) {
            System.out.println("Database connection failed.");
            return -1; // Return -1 if the connection failed
        }

        String query = "SELECT order_id FROM orders WHERE user_name = ? ORDER BY order_id DESC LIMIT 1"; // Get the latest order by username
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, username);

        // Execute the query
        ResultSet rs = ps.executeQuery();
        System.out.println("Executing query for username: " + username); // Log the query being executed

        // Check if an order exists for the given username
        if (rs.next()) {
            orderId = rs.getInt("order_id"); // Retrieve the order ID
            System.out.println("Found Order ID: " + orderId); // Log the retrieved order ID
        } else {
            System.out.println("No orders found for the user: " + username); // Handle case where no orders exist
        }
    } catch (SQLException e) {
        System.out.println("SQL error occurred.");
        e.printStackTrace(); // Print stack trace for debugging
    }

    return orderId; // Return the retrieved order ID
}


 public boolean updateOrderStatus(int orderId, String newStatus) {
        boolean isUpdated = false; // Default value

        // Establish database connection
        try (Connection conn = Database.getInstance().connect()) {
            String query = "UPDATE orders SET order_status = ? WHERE order_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, newStatus);
            ps.setInt(2, orderId);
            
            // Execute update
            int rowsAffected = ps.executeUpdate();
            isUpdated = rowsAffected > 0; // Check if the update was successful

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        
        return isUpdated; // Return true if updated, false otherwise
    }
 
  // Method to process a refund
    public boolean processRefund(int orderId) {
        boolean isRefunded = false; // Default value
        
        // Establish database connection
        try (Connection conn = Database.getInstance().connect()) {
            // Check the current order status
            String statusQuery = "SELECT order_status FROM orders WHERE order_id = ?";
            PreparedStatement statusPs = conn.prepareStatement(statusQuery);
            statusPs.setInt(1, orderId);
            ResultSet rs = statusPs.executeQuery();

            if (rs.next()) {
                String currentStatus = rs.getString("order_status");

                // Only process refund if order is in a refundable state (e.g., "Pending", "Paid")
                if (currentStatus.equals("Paid") || currentStatus.equals("Pending")) {
                    // Update the order status to "Refunded"
                    String updateQuery = "UPDATE orders SET order_status = ? WHERE order_id = ?";
                    PreparedStatement updatePs = conn.prepareStatement(updateQuery);
                    updatePs.setString(1, "Refunded");
                    updatePs.setInt(2, orderId);

                    // Execute update
                    int rowsAffected = updatePs.executeUpdate();
                    isRefunded = rowsAffected > 0; // Check if the update was successful
                } else {
                    System.out.println("Refund not applicable for order status: " + currentStatus);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
        
        return isRefunded; // Return true if refunded, false otherwise
    }
}
