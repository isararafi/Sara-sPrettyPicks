package sara.sprettypicks;


import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.SQLIntegrityConstraintViolationException;
 // This is necessary for Statement.RETURN_GENERATED_KEYS


public class orders {
public int storeOrderInDatabase(String userName, double totalBill, String shippingAddress) {
    int orderId = -1; // Default to -1 to indicate not found

    // SQL query to check if a pending order exists for the given username
    String checkQuery = "SELECT order_id FROM orders WHERE user_name = ? AND order_status = 'Pending'";

    // SQL query to insert a new order
    String insertQuery = "INSERT INTO orders (user_name, total_amount, shipping_address, order_status) VALUES (?, ?, ?, ?)";

    // SQL query to update the total amount and shipping address of an existing order (if needed)
    String updateQuery = "UPDATE orders SET total_amount = ?, shipping_address = ? WHERE order_id = ?";

    try (Connection conn = Database.getInstance().connect()) {
        if (conn == null) {
            throw new SQLException("Failed to connect to the database.");
        }

        // Step 1: Check if there is an existing pending order for this user
        try (PreparedStatement checkPs = conn.prepareStatement(checkQuery)) {
            checkPs.setString(1, userName);
            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next()) {
                    // An existing pending order found, update the existing order
                    orderId = rs.getInt("order_id");

                    // Update the order with the new total bill and shipping address
                    try (PreparedStatement updatePs = conn.prepareStatement(updateQuery)) {
                        updatePs.setDouble(1, totalBill);
                        updatePs.setString(2, shippingAddress);
                        updatePs.setInt(3, orderId);
                        updatePs.executeUpdate();
                    }
                } else {
                    // No existing pending order, insert a new order
                    try (PreparedStatement insertPs = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                        insertPs.setString(1, userName);
                        insertPs.setDouble(2, totalBill);
                        insertPs.setString(3, shippingAddress);
                        insertPs.setString(4, "Pending"); // Set the order status to 'Pending'

                        int affectedRows = insertPs.executeUpdate();
                        if (affectedRows > 0) {
                            // Retrieve the generated order ID
                            try (ResultSet generatedKeys = insertPs.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    orderId = generatedKeys.getInt(1); // Get the order ID
                                }
                            }
                        } else {
                            System.out.println("Order insertion failed, no rows affected.");
                        }
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace for debugging
    }

    return orderId; // Return the order ID (either from existing or newly created order)
}


public boolean storeOrderItemsInDatabase(int orderId, List<CartItem> cartItems) {
    // Queries
    String checkQuery = "SELECT quantity FROM order_items WHERE order_id = ? AND product_id = ?";
    String updateQuery = "UPDATE order_items SET quantity = quantity + ?, price = ? WHERE order_id = ? AND product_id = ?";
    String insertQuery = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

    try (Connection conn = Database.getInstance().connect()) {
        if (conn == null) {
            throw new SQLException("Failed to connect to the database.");
        }

        // Iterate through the cart items to process them
        for (CartItem item : cartItems) {
            boolean exists = false;

            // Fetch the price of the product
            double price = item.getPrice(); // Assuming getPrice() is available in CartItem

            // Check if the product already exists in the order
            try (PreparedStatement checkPs = conn.prepareStatement(checkQuery)) {
                checkPs.setInt(1, orderId);
                checkPs.setInt(2, item.getProductId());

                try (ResultSet rs = checkPs.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                    }
                }
            }

            // If the product exists, update the quantity and price
            if (exists) {
                try (PreparedStatement updatePs = conn.prepareStatement(updateQuery)) {
                    updatePs.setInt(1, item.getQuantity()); // Add the quantity
                    updatePs.setDouble(2, price); // Update the price
                    updatePs.setInt(3, orderId);
                    updatePs.setInt(4, item.getProductId());

                    updatePs.executeUpdate(); // Execute the update query
                }
            } else {
                // Insert a new product with its quantity and price
                try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                    insertPs.setInt(1, orderId);
                    insertPs.setInt(2, item.getProductId());
                    insertPs.setInt(3, item.getQuantity());
                    insertPs.setDouble(4, price); // Insert the price

                    insertPs.executeUpdate(); // Execute the insert query
                }
            }
        }

        return true; // All items processed successfully
    } catch (SQLException e) {
        e.printStackTrace(); // Log the SQL error
        return false; // Return false to indicate failure
    }
}


   public void addOrderItem(int orderId, int productId, int quantity) {
    String checkQuery = "SELECT quantity FROM order_items WHERE order_id = ? AND product_id = ?";
    String updateQuery = "UPDATE order_items SET quantity = quantity + ? WHERE order_id = ? AND product_id = ?";
    String insertQuery = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";

    try (Connection conn = Database.getInstance().connect()) {
        if (conn == null) {
            throw new SQLException("Failed to connect to the database.");
        }

        boolean exists = false;

        // Check if the order item already exists
        try (PreparedStatement checkPs = conn.prepareStatement(checkQuery)) {
            checkPs.setInt(1, orderId);
            checkPs.setInt(2, productId);

            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next()) {
                    exists = true;
                }
            }
        }

        if (exists) {
            // Update the existing quantity
            try (PreparedStatement updatePs = conn.prepareStatement(updateQuery)) {
                updatePs.setInt(1, quantity);
                updatePs.setInt(2, orderId);
                updatePs.setInt(3, productId);

                updatePs.executeUpdate();
            }
        } else {
            // Insert a new order item
            try (PreparedStatement insertPs = conn.prepareStatement(insertQuery)) {
                insertPs.setInt(1, orderId);
                insertPs.setInt(2, productId);
                insertPs.setInt(3, quantity);

                insertPs.executeUpdate();
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
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
