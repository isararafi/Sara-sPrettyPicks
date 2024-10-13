package sara.sprettypicks;

import java.awt.HeadlessException;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sara.sprettypicks.CartItem;
import sara.sprettypicks.Database;
import sara.sprettypicks.SessionManager;

public class surprisecheckout {
     static double surpriseDiscount;
     static LocalDateTime discountExpiryTime;
     static boolean surpriseMeClicked; // Flag to check if the user clicked "Surprise Me"

    // Default constructor
    public surprisecheckout() {
        clearDiscount(); // Initialize discount to 0 and flag to false
    }

    // Method to set the discount and start the expiry timer
    public void setSurpriseDiscount(double discount) {
        surpriseDiscount = discount; // Set the discount value
        surpriseMeClicked = true; // Mark that Surprise Me was clicked
        discountExpiryTime = LocalDateTime.now().plusMinutes(5); // Set expiry time
    }

    // Method to handle the checkout process
    public void checkout() {
        Database db = new Database();  // Assuming you have a database connection setup
        String userEmail = SessionManager.getLoggedInUserEmail();  // Method to get current logged-in user's email

        try {
            // Fetch all items in the user's cart from the database
            List<CartItem> cartItems = db.getCartItemsByEmail(userEmail);

            if (cartItems == null || cartItems.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculate the total bill
            double totalBill = 0;
            StringBuilder cartDetails = new StringBuilder("Your Cart:\n");

            for (CartItem item : cartItems) {
                if (item.getProductId() != 0 && item.getPrice() > 0 && item.getQuantity() > 0) {
                    double itemTotalPrice = item.getPrice() * item.getQuantity();
                    cartDetails.append("Product ID: ").append(item.getProductId())
                               .append(", Product Name: ").append(item.getProductName())
                               .append(", Quantity: ").append(item.getQuantity())
                               .append(", Price per Unit: $").append(String.format("%.2f", item.getPrice()))
                               .append(", Total Price: $").append(String.format("%.2f", itemTotalPrice))
                               .append("\n");
                    totalBill += itemTotalPrice;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid item in cart.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Display cart details and ask if the user wants to apply a discount
            int applyDiscountResponse = JOptionPane.showConfirmDialog(null, cartDetails.toString() + "\nApply Surprise Discount?", "Checkout", JOptionPane.YES_NO_OPTION);

            // If the user clicks "Yes" to apply discount
            if (applyDiscountResponse == JOptionPane.YES_OPTION) {
                applyDiscount(totalBill, cartDetails);
            } else {
                cartDetails.append("\nTotal Bill: $").append(String.format("%.2f", totalBill));
                JOptionPane.showMessageDialog(null, cartDetails.toString(), "Checkout", JOptionPane.INFORMATION_MESSAGE);
                // Handle payment without discount
                handlePayment(totalBill, db, userEmail);
            }

        } catch (HeadlessException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

public void applyDiscount(double totalBill, StringBuilder cartDetails) {
    // Log the state of variables before checking the condition
    System.out.println("surpriseMeClicked: " + surpriseMeClicked);
    System.out.println("discountExpiryTime: " + discountExpiryTime);
    System.out.println("Current Time: " + LocalDateTime.now());

    // Check if the discount is valid
    if (surpriseMeClicked && discountExpiryTime != null && LocalDateTime.now().isBefore(discountExpiryTime)) {
        double discountAmount = totalBill * (surpriseDiscount / 100);
        totalBill -= discountAmount;

        // Append discount information to the cart details
        cartDetails.append("\nSurprise Discount Applied: -$").append(String.format("%.2f", discountAmount));
        cartDetails.append("\nTotal Bill After Discount: $").append(String.format("%.2f", totalBill));

        JOptionPane.showMessageDialog(null, cartDetails.toString(), "Discount Applied", JOptionPane.INFORMATION_MESSAGE);
        try {
            handlePayment(totalBill, new Database(), SessionManager.getLoggedInUserEmail());
        } catch (SQLException ex) {
            Logger.getLogger(surprisecheckout.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Sorry, you don’t have any discount available.", "No Discount", JOptionPane.ERROR_MESSAGE);
    }
}


    // Method to handle payment processing
    public void handlePayment(double totalBill, Database db, String userEmail) throws SQLException {
        // Prompt the user to enter the payment amount, showing the total amount
        String paymentInput = JOptionPane.showInputDialog("Enter the amount to pay:\nTotal Amount: $" + String.format("%.2f", totalBill));

        if (paymentInput == null || paymentInput.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Payment is required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double paymentAmount;
        try {
            paymentAmount = Double.parseDouble(paymentInput);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid payment amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Proceed with payment handling
        if (paymentAmount >= totalBill) {
            double change = paymentAmount - totalBill;
            boolean paymentSaved = db.savePayment(userEmail, totalBill, paymentAmount);

            if (paymentSaved) {
                String message = "Payment successful!";
                if (change > 0) {
                    message += " Your change is: $" + String.format("%.2f", change);
                }
                JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save payment details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Payment unsuccessful! The amount is less than the total bill. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to clear the discount and reset flags
    static void clearDiscount() {
        surpriseDiscount = 0; // Reset discount
        discountExpiryTime = null; // Reset expiry time
        surpriseMeClicked = false; // Reset the flag
    }

    public double getSurpriseDiscount() {
        return surpriseDiscount;
    }
}
