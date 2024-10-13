/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

/**
 *
 * @author sarar
 */
public class CartItem {
    private int productId;  
    private String productName; // Add product name
    private double price;
    private int quantity; // Include quantity

    // Constructor
    public CartItem(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName; // Getter for product name
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity; // Getter for quantity
    }
     
}
