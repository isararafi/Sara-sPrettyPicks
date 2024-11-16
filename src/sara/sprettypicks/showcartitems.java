/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sarar
 */
import javax.swing.JOptionPane;
import java.util.List;

public class showcartitems {

    // Assuming this is the method that gets called to show the cart
    public void cart() {
        // Get database instance
        Database db = Database.getInstance(); // Singleton reference variable

        // Get logged-in user's email
        String userEmail = SessionManager.getLoggedInUserEmail(); // Use the method to get the user's email
        String username = SessionManager.getLoggedInUserName(); // You can keep this if you want the first name

        // Fetch the cart items for the logged-in user
        List<CartItem> cartItems = db.getCartItemsByuseremail(userEmail);  // Correct method name

        // Prepare data to display
        StringBuilder cartDetails = new StringBuilder("Cart Items for " + username + ":\n\n");

        if (cartItems == null || cartItems.isEmpty()) {  // Handle null or empty cart
            JOptionPane.showMessageDialog(null, "Your cart is empty.");
        } else {
            // Display each cart item with details
            int itemNumber = 1;
            double totalCartPrice = 0; // Initialize total cart price

            for (CartItem item : cartItems) {
                String productId = String.valueOf(item.getProductId());
                String productName = item.getProductName(); // Assuming this method exists in CartItem
                int quantity = item.getQuantity();
                double price = item.getPrice();

                // Calculate total price for the current item
                double totalPriceForItem = price * quantity;

                cartDetails.append(itemNumber++)
                        .append(". Product ID: ").append(productId)
                        .append(", Product Name: ").append(productName)
                        .append(", Quantity: ").append(quantity)
                        .append(", Price per Unit: $").append(String.format("%.2f", price))
                        .append(", Total Price: $").append(String.format("%.2f", totalPriceForItem))
                        .append("\n");

                totalCartPrice += totalPriceForItem; // Add to total cart price
            }

            // Show the total cart price
            cartDetails.append("\nTotal Cart Price: $").append(String.format("%.2f", totalCartPrice)).append("\n");

            cartDetails.append("\nOptions:\n1. Clear specific item\n2. Clear entire cart\n");

            // Show the cart items and ask for user input
            String input = JOptionPane.showInputDialog(null, cartDetails.toString() + "\nEnter your option (1 or 2):");

            // Handle user input for clearing specific item or entire cart
            if (input != null) {
                if (input.equals("1")) {
                    // Clear specific item
                    String itemNumberStr = JOptionPane.showInputDialog(null, "Enter the item number to clear:");
                    if (itemNumberStr != null) {
                        try {
                            int itemNumberToClear = Integer.parseInt(itemNumberStr);
                            if (itemNumberToClear >= 1 && itemNumberToClear < itemNumber) {
                                // Get the product ID for the selected item number
                                int productIdToRemove = cartItems.get(itemNumberToClear - 1).getProductId(); // Adjusted to get product ID directly from CartItem
                                db.removeItemFromCart(userEmail, productIdToRemove); // Remove the item
                                JOptionPane.showMessageDialog(null, "Item removed successfully.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid item number.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                        }
                    }
                } else if (input.equals("2")) {
                    // Clear entire cart
                    db.clearCart(userEmail); // Clears the entire cart for this user
                    JOptionPane.showMessageDialog(null, "Cart cleared successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid option.");
                }
            }
        }
    }
}
