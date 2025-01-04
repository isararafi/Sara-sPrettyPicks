package sara.sprettypicks;

import java.awt.BorderLayout;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import sara.sprettypicks.CartItem;
import sara.sprettypicks.Database;
import sara.sprettypicks.SessionManager;
import sara.sprettypicks.orders;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class surprisecheckout {

    static Database db = Database.getInstance();
    static double surpriseDiscount;
    static LocalDateTime discountExpiryTime;
    static boolean surpriseMeClicked;

    public surprisecheckout() {
        clearDiscount(); // Initialize discount and flag
    }

    public void setSurpriseDiscount(double discount) {
        surpriseDiscount = discount;
        surpriseMeClicked = true;
        discountExpiryTime = LocalDateTime.now().plusMinutes(5); // Set expiry time
    }

    public void checkout() {
        String Username = SessionManager.getLoggedInUserName();

        // Create a JFrame with a progress bar
        JFrame progressFrame = new JFrame("Processing Checkout");
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Indeterminate mode to indicate progress
        progressFrame.add(progressBar, BorderLayout.CENTER);
        progressFrame.setSize(400, 100);
        progressFrame.setLocationRelativeTo(null);
        progressFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        progressFrame.setVisible(true);

        SwingWorker<Void, String> checkoutWorker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    // Step 1: Fetch Cart Items
                    List<CartItem> cartItems = db.getCartItemsByUsername(Username);
                    if (cartItems == null || cartItems.isEmpty()) {
                        publish("Your cart is empty! Please add items before proceeding.");
                        return null; // Stop further processing
                    }

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
                        }
                    }

                    // Step 2: Apply Discount
                    publish("Displaying cart details...");
                    int applyDiscountResponse = JOptionPane.showConfirmDialog(null, cartDetails.toString() + "\nApply Surprise Discount?", "Checkout", JOptionPane.YES_NO_OPTION);

                    if (applyDiscountResponse == JOptionPane.YES_OPTION) {
                        totalBill = applyDiscount(totalBill, cartDetails);
                    }

                    // Step 3: Enter Shipping Address
                    publish("Requesting shipping address...");
                    String shippingAddress = JOptionPane.showInputDialog("Please enter your shipping address:");
                    if (shippingAddress == null || shippingAddress.trim().isEmpty()) {
                        publish("Shipping address is required. Please provide a valid address.");
                        return null;
                    }

                    // Step 4: Store Order in Database
                    publish("Storing order details...");
                    orders orderHandler = new orders();
                    int orderId = orderHandler.storeOrderInDatabase(Username, totalBill, shippingAddress);
                    if (orderId == -1) {
                        publish("Failed to place order. Please try again later.");
                        return null;
                    }

                    // Step 5: Store Order Items
                    publish("Storing order items...");
                    boolean itemsStored = orderHandler.storeOrderItemsInDatabase(orderId, cartItems);
                    if (!itemsStored) {
                        publish("Failed to store order items. Please try again.");
                        return null;
                    }

                    // Step 6: Handle Payment
                    publish("Processing payment...");
                    handlePayment(totalBill, db, Username);

                } catch (Exception e) {
                    publish("Unexpected Error: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                // Update progress frame with the latest message
                String latestMessage = chunks.get(chunks.size() - 1);
                progressFrame.setTitle(latestMessage);
            }

            @Override
            protected void done() {
                try {
                    progressFrame.dispose(); // Close the progress bar window
                    if (!isCancelled()) {
                        JOptionPane.showMessageDialog(null, "Checkout process complete! Thank you for your order.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Reset discount after completion
                clearDiscount();
            }
        };

        checkoutWorker.execute();
    }

    public double applyDiscount(double totalBill, StringBuilder cartDetails) {
        double discountAmount = 0;
        if (surpriseMeClicked && discountExpiryTime != null && LocalDateTime.now().isBefore(discountExpiryTime)) {
            discountAmount = totalBill * (surpriseDiscount / 100);
            totalBill -= discountAmount;

            cartDetails.append("\nSurprise Discount Applied: -$").append(String.format("%.2f", discountAmount));
            cartDetails.append("\nTotal Bill After Discount: $").append(String.format("%.2f", totalBill));

            JOptionPane.showMessageDialog(null, cartDetails.toString(), "Discount Applied", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String message = !surpriseMeClicked ? "You need to click 'Surprise Me' to apply the discount."
                    : (LocalDateTime.now().isAfter(discountExpiryTime)) ? "Your surprise discount has expired."
                    : "You don’t have any discount available.";
            JOptionPane.showMessageDialog(null, message, "No Discount", JOptionPane.ERROR_MESSAGE);
        }

        return discountAmount;
    }

    public void handlePayment(double totalBill, Database db, String userName) throws SQLException {
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

        if (paymentAmount >= totalBill) {
            double change = paymentAmount - totalBill;
            boolean paymentSaved = db.savePayment(userName, totalBill, paymentAmount);

            if (paymentSaved) {
                // Update order status in the database
                boolean statusUpdated = updateOrderStatusToCompleted(db, userName);

                if (statusUpdated) {
                    String message = "Payment successful and order status updated to 'Completed'!";
                    if (change > 0) {
                        message += " Your change is: $" + String.format("%.2f", change);
                    }
                    JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Payment saved, but failed to update order status. Please check.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save payment details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Payment unsuccessful! The amount is less than the total bill. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean updateOrderStatusToCompleted(Database db, String userName) {
        PreparedStatement stmt = null;
        try {
            // Establish a database connection
            Connection connection = db.connect();

            // SQL query to update order_status for the user's pending orders
            String updateQuery = "UPDATE orders SET order_status = 'Completed' WHERE user_name = ? AND order_status = 'Pending'";
            stmt = connection.prepareStatement(updateQuery);
            stmt.setString(1, userName);

            int rowsUpdated = stmt.executeUpdate();

            // If rows were updated, return true
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Ensure resources are closed properly
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static void clearDiscount() {
        surpriseDiscount = 0;
        discountExpiryTime = null;
        surpriseMeClicked = false;
    }

    public double getSurpriseDiscount() {
        return surpriseDiscount;
    }
}
