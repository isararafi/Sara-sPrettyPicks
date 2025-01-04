/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author sarar
 */
import javax.swing.*;        // For JProgressBar, JOptionPane, JDialog, SwingWorker
import java.awt.*;           // For Layouts (e.g., BorderLayout)
import java.util.List;       // For List (to handle cart items)
import java.util.ArrayList;  // In case you are using an ArrayList for cart items
import java.awt.event.*;     // If you need to add any event listeners (e.g., ActionListener)

import javax.swing.JOptionPane;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import static sara.sprettypicks.InsertImageWithPath.cartLabel;
import static sara.sprettypicks.InsertImageWithPath.updateCartItemCount;

public class showcartitems {
List<CartItem> cartItems;
    // Assuming this is the method that gets called to show the cart
    public void cart() {
    // Create a progress bar
    JProgressBar progressBar = new JProgressBar();
    progressBar.setIndeterminate(true); // Indeterminate mode to show ongoing work
    progressBar.setString("Loading your cart items. Please wait...");
    progressBar.setStringPainted(true);

    // Create a modal dialog to display the progress bar
    JDialog progressDialog = new JDialog((Frame) null, "Loading", true);
    progressDialog.setLayout(new BorderLayout());
    progressDialog.add(progressBar, BorderLayout.CENTER);
    progressDialog.setSize(300, 100);
    progressDialog.setLocationRelativeTo(null);

    // Run the progress dialog in a separate thread
    SwingWorker<Void, Void> cartLoader = new SwingWorker<Void, Void>() {
        @Override
        protected Void doInBackground() throws Exception {
            // Simulate loading process with a slight delay
            Database db = Database.getInstance();
            String username = SessionManager.getLoggedInUserName();

            // Fetch cart items
            List<CartItem> cartItems = db.getCartItemsByUsername(username);
            Thread.sleep(2000); // Simulate delay

            if (cartItems == null || cartItems.isEmpty()) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(null, "Your cart is empty.");
                });
                return null;
            }

            // Prepare cart details
            StringBuilder cartDetails = new StringBuilder("Cart Items for " + username + ":\n\n");
            int itemNumber = 1;
            double totalCartPrice = 0;

            for (CartItem item : cartItems) {
                String productId = String.valueOf(item.getProductId());
                String productName = item.getProductName();
                int quantity = item.getQuantity();
                double price = item.getPrice();
                double totalPriceForItem = price * quantity;

                cartDetails.append(itemNumber++)
                        .append(". Product ID: ").append(productId)
                        .append(", Product Name: ").append(productName)
                        .append(", Quantity: ").append(quantity)
                        .append(", Price per Unit: $").append(String.format("%.2f", price))
                        .append(", Total Price: $").append(String.format("%.2f", totalPriceForItem))
                        .append("\n");

                totalCartPrice += totalPriceForItem;
            }

            cartDetails.append("\nTotal Cart Price: $").append(String.format("%.2f", totalCartPrice)).append("\n");
            cartDetails.append("\nOptions:\n1. Clear specific item\n2. Clear entire cart\n");

            SwingUtilities.invokeLater(() -> {
                // Display cart details to the user
                String input = JOptionPane.showInputDialog(null, cartDetails.toString() + "\nEnter your option (1 or 2):");

                if (input != null) {
                    if (input.equals("1")) {
                        String itemNumberStr = JOptionPane.showInputDialog(null, "Enter the item number to clear:");
                        if (itemNumberStr != null) {
                            try {
                                int itemNumberToClear = Integer.parseInt(itemNumberStr);
                                if (itemNumberToClear >= 1 && itemNumberToClear <= cartItems.size()) {
                                    int productIdToRemove = cartItems.get(itemNumberToClear - 1).getProductId();
                                    db.removeItemFromCart(username, productIdToRemove);
                                    JOptionPane.showMessageDialog(null, "Item removed successfully.");
                                    updateCartItemCount(cartLabel);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid item number.");
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                            }
                        }
                    } else if (input.equals("2")) {
                        db.clearCart(username);
                        JOptionPane.showMessageDialog(null, "Cart cleared successfully.");
                        updateCartItemCount(cartLabel);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid option.");
                    }
                }
            });

            return null;
        }

        @Override
        protected void done() {
            // Close the progress dialog once the task is complete
            progressDialog.dispose();
            System.out.println("Cart loading is complete.");
        }
    };

    // Show the progress dialog and execute the worker
    SwingUtilities.invokeLater(() -> {
        progressDialog.setVisible(true);
    });
    cartLoader.execute();
}

    
    public String getOrderDetails(int orderId) {
    StringBuilder details = new StringBuilder();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // Assuming Database.getInstance() gets the connection
        conn = Database.getInstance().connect();

        // SQL query to fetch order details and product name from the products table
        String orderQuery = "SELECT p.name AS product_name, oi.quantity, oi.price " +
                             "FROM order_items oi " +
                             "INNER JOIN products p ON oi.product_id = p.product_id " +
                             "WHERE oi.order_id = ?";

        ps = conn.prepareStatement(orderQuery);
        ps.setInt(1, orderId); // Set the orderId parameter

        // Execute the query
        rs = ps.executeQuery();

        // Check if the result set contains any rows
        if (!rs.next()) {
            return "No items found for this order.";
        }

        details.append("Order ID: " + orderId + "\n");
        details.append("Items in your order (Product Name | Quantity | Price | Total):\n");

        // Loop through the result set and append the order details horizontally
        do {
            String productName = rs.getString("product_name");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            double total = price * quantity;

            details.append(productName + " | ");
            details.append("Quantity: " + quantity + " | ");
            details.append("Price: $" + price + " | ");
            details.append("Total: $" + total + " | ");

        } while (rs.next()); // Continue until all items are retrieved

    } catch (SQLException e) {
        e.printStackTrace();
        details.append("Error fetching order details: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close(); // Close the connection
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    return details.toString();
}
    public boolean isCartEmpty() {
        return cartItems == null || cartItems.isEmpty(); // Ensure that it's not null and is empty
    }
}
    

