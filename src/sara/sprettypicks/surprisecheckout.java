
package sara.sprettypicks;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import sara.sprettypicks.CartItem;
import sara.sprettypicks.Database;
import sara.sprettypicks.SessionManager;
import sara.sprettypicks.orders;

public class surprisecheckout {

    static Database db = Database.getInstance(); // Singleton instance of Database
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
    //String email = SessionManager.getLoggedInUserEmail(); // Method to get current logged-in user's email
    String Username=SessionManager.getLoggedInUserName();

    try {
        // Fetch all items in the user's cart from the database
        List<CartItem> cartItems = db.getCartItemsByUsername(Username);

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

        if (applyDiscountResponse == JOptionPane.YES_OPTION) {
            applyDiscount(totalBill, cartDetails);
        } else {
            cartDetails.append("\nTotal Bill: $").append(String.format("%.2f", totalBill));
            JOptionPane.showMessageDialog(null, cartDetails.toString(), "Checkout", JOptionPane.INFORMATION_MESSAGE);
            
            // Step 1: Prompt for shipping address
            String shippingAddress = JOptionPane.showInputDialog("Please enter your shipping address:");
            if (shippingAddress == null || shippingAddress.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Shipping address is required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Step 2: Insert Order into the orders table, including the shipping address
            orders ob = new orders();
            int orderId = ob.storeOrderInDatabase(Username, totalBill, shippingAddress); // Modify this method to accept shipping address

            // Step 3: Store cart items in the order_items table
            ob.storeOrderItemsInDatabase(orderId, cartItems);

            // Step 4: Handle payment without discount
            handlePayment(totalBill, db, Username);
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
                handlePayment(totalBill, db, SessionManager.getLoggedInUserEmail());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Payment failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, you don’t have any discount available.", "No Discount", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to handle payment processing
    public void handlePayment(double totalBill, Database db, String userName) throws SQLException {
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
            boolean paymentSaved = db.savePayment(userName, totalBill, paymentAmount);

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
