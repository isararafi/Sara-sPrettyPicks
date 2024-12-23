package sara.sprettypicks;

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

        SwingWorker<Void, Void> checkoutWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    List<CartItem> cartItems = db.getCartItemsByUsername(Username);

                    if (cartItems == null || cartItems.isEmpty()) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Your cart is empty! Please add items before proceeding.", "Error", JOptionPane.ERROR_MESSAGE);
                        });
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

                    int applyDiscountResponse = JOptionPane.showConfirmDialog(null, cartDetails.toString() + "\nApply Surprise Discount?", "Checkout", JOptionPane.YES_NO_OPTION);

                    if (applyDiscountResponse == JOptionPane.YES_OPTION) {
                        totalBill = applyDiscount(totalBill, cartDetails);
                    }

                    String shippingAddress = JOptionPane.showInputDialog("Please enter your shipping address:");
                    if (shippingAddress == null || shippingAddress.trim().isEmpty()) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Shipping address is required. Please provide a valid address.", "Error", JOptionPane.ERROR_MESSAGE);
                        });
                        return null;
                    }

                    orders orderHandler = new orders();
                    int orderId = orderHandler.storeOrderInDatabase(Username, totalBill, shippingAddress);
                    if (orderId == -1) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Failed to place order. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
                        });
                        return null;
                    }

                    boolean itemsStored = orderHandler.storeOrderItemsInDatabase(orderId, cartItems);
                    if (!itemsStored) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Failed to store order items. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        });
                        return null;
                    }

                    handlePayment(totalBill, db, Username);

                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    });
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    if (!isCancelled()) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(null, "Checkout process complete! Thank you for your order.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Reset discount after completion
                clearDiscount();
            }
        };

        checkoutWorker.execute();

        JOptionPane.showMessageDialog(null, "Checkout is now processing in the background. Please wait until the process completes.");
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
            String message = !surpriseMeClicked ? "You need to click 'Surprise Me' to apply the discount." :
                            (LocalDateTime.now().isAfter(discountExpiryTime)) ? "Your surprise discount has expired." :
                            "You don’t have any discount available.";
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

    static void clearDiscount() {
        surpriseDiscount = 0;
        discountExpiryTime = null;
        surpriseMeClicked = false;
    }

    public double getSurpriseDiscount() {
        return surpriseDiscount;
    }
}
