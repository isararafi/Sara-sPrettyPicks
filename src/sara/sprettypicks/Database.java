package sara.sprettypicks;

/**
 *
 * @author sarar
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


import java.util.ArrayList;



public class Database {
//static Database db = new Database();
    // Database connection details
    // Private static instance
    private static Database db;
    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlineshopping"; // Replace with your DB URL
    private static final String DB_USER = "root"; // Replace with your DB username
    private static final String DB_PASSWORD = "56528"; // Replace with your DB password

    // Private constructor to prevent instantiation
    private Database() {
        // Optionally initialize connection-related setup here
    }

    // Static method to get the single instance of the class
    public static Database getInstance() {
        if (db == null) {
            synchronized (Database.class) { // Thread safety in case of multi-threaded access
                if (db == null) {
                    db = new Database();
                }
            }
        }
        return db;
    }

    // Method to connect to the database
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to check customer login
    public boolean checkCustomerLogin(String username, String password) {
    String query = "SELECT * FROM customers WHERE cuser_name = ? AND password = ?"; // SQL query

    // Try-with-resources to automatically close resources
    try (Connection conn = this.connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the query parameters
        stmt.setString(1, username); // Use name instead of email
        stmt.setString(2, password); // Use hashed password in production

        // Execute the query and process the result set
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next(); // Returns true if a record is found
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
        return false;
    }
}


    // Method to check admin login
    public  boolean checkAdminLogin(String username, String password) {
        String query = "SELECT * FROM admin WHERE auser_name = ? AND password = ?"; // SQL query

    // Try-with-resources to automatically close resources
    try (Connection conn = this.connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the query parameters
        stmt.setString(1, username); // Use name instead of email
        stmt.setString(2, password); // Use hashed password in production

        // Execute the query and process the result set
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next(); // Returns true if a record is found
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
        return false;
    }
    }

    // Method for customer signup
   public boolean signupCustomer(String name, String username, String email, String password) {
    // SQL query updated to include the correct columns
    String query = "INSERT INTO customers (first_name, cuser_name, email, password) VALUES (?, ?, ?, ?)";

    // Try-with-resources to automatically close resources
    try (Connection conn = this.connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the query parameters
        stmt.setString(1, name);      // Set the first_name
        stmt.setString(2, username); // Set the cuser_name
        stmt.setString(3, email);    // Set the email
        stmt.setString(4, password); // Ideally, hash the password before storing it

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Returns true if a record was inserted
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
        return false;
    }
}


    // Method for admin signup
   public boolean signupAdmin(String email, String password, String name, String username) {
    // Updated SQL query for inserting admin, using the new column names
    String query = "INSERT INTO admin (email, password, admin_name, auser_name) VALUES (?, ?, ?, ?)"; 

    // Try-with-resources to automatically close resources
    try (Connection conn = this.connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the query parameters
        stmt.setString(1, email);     // Set the email
        stmt.setString(2, password);  // Set the password
        stmt.setString(3, name);      // Set the name (stored in admin_name)
        stmt.setString(4, username);  // Set the username (stored in auser_name)

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Returns true if a record was inserted
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception
        return false;
    }
}


  public void addItemToCart(String username, int productId, int quantity, double price) {
    try {
        // Check if the username exists in the customers table
        String checkUserQuery = "SELECT cuser_name FROM customers WHERE cuser_name = ?";
        PreparedStatement checkUserStmt = connect().prepareStatement(checkUserQuery);
        checkUserStmt.setString(1, username);
        ResultSet userRs = checkUserStmt.executeQuery();

        if (userRs.next()) {
            // User exists, proceed with adding to the cart

            // First, check if the product is already in the cart
            String checkCartQuery = "SELECT quantity FROM cart WHERE user_name = ? AND product_id = ?";
            PreparedStatement checkCartStmt = connect().prepareStatement(checkCartQuery);
            checkCartStmt.setString(1, username);
            checkCartStmt.setInt(2, productId);
            ResultSet cartRs = checkCartStmt.executeQuery();

            if (cartRs.next()) {
                // Product exists in the cart, update its quantity
                int existingQuantity = cartRs.getInt("quantity");
                int newQuantity = existingQuantity + quantity; // Update the quantity based on the new quantity

                // Update the quantity in the cart
                String updateCartQuery = "UPDATE cart SET quantity = ? WHERE user_name = ? AND product_id = ?";
                PreparedStatement updateCartStmt = connect().prepareStatement(updateCartQuery);
                updateCartStmt.setInt(1, newQuantity);
                updateCartStmt.setString(2, username);
                updateCartStmt.setInt(3, productId);
                updateCartStmt.executeUpdate();
                System.out.println("Updated quantity in the cart.");
            } else {
                // Product does not exist in the cart, insert it
                String insertCartQuery = "INSERT INTO cart (user_name, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                PreparedStatement insertCartStmt = connect().prepareStatement(insertCartQuery);
                insertCartStmt.setString(1, username);
                insertCartStmt.setInt(2, productId);
                insertCartStmt.setInt(3, quantity);
                insertCartStmt.setDouble(4, price);
                insertCartStmt.executeUpdate();
                System.out.println("Item added to the cart.");
            }

            // Now, add the item to the order_items table
            // Get the order_id of the user's current pending order, or create a new order
            int orderId = createOrderIfNeeded(username);

            if (orderId != -1) {
                String insertOrderItemsQuery = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
                PreparedStatement insertOrderItemsStmt = connect().prepareStatement(insertOrderItemsQuery);
                insertOrderItemsStmt.setInt(1, orderId);  // Insert the order_id
                insertOrderItemsStmt.setInt(2, productId);
                insertOrderItemsStmt.setInt(3, quantity);  // Insert the quantity
                insertOrderItemsStmt.setDouble(4, price);
                insertOrderItemsStmt.executeUpdate();
                System.out.println("Item added to order_items with order_id: " + orderId);
            }
        } else {
            // Handle case where the username doesn't exist
            System.out.println("Username does not exist in the customers table.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private int createOrderIfNeeded(String username) {
    int orderId = -1;

    // Check if there is an existing order with status "Pending" for the user
    String query = "SELECT order_id FROM orders WHERE user_name = ? AND order_status = 'Pending'";

    try (Connection conn = Database.getInstance().connect()) {
        if (conn == null) {
            throw new SQLException("Failed to connect to the database.");
        }

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // If a pending order exists, return its order_id
            orderId = rs.getInt("order_id");
        } else {
            // If no pending order exists, create a new order
            String insertOrderQuery = "INSERT INTO orders (user_name, order_status) VALUES (?, 'Pending')";
            PreparedStatement insertOrderStmt = conn.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            insertOrderStmt.setString(1, username);
            insertOrderStmt.executeUpdate();

            // Retrieve the generated order_id
            ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
                System.out.println("Created new order with order_id: " + orderId);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orderId;
}






    public List<CartItem> getCartItemsByname(String email) {
    List<CartItem> cartItems = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Get a connection to the database
        conn = connect(); // Ensure you have the right connection method

        // SQL query to get cart items for the specific user, joining with products table
       String sql = "SELECT c.product_id, p.name, c.quantity, p.price " +
             "FROM cart c " +
             "JOIN products p ON c.product_id = p.product_id " +
             "JOIN customers cust ON c.user_email = cust.email " + // Join with customers table using email
             "WHERE c.user_email = ?"; // Filter by user_email


        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        rs = pstmt.executeQuery();

        while (rs.next()) {
            // Assuming your CartItem has a constructor that takes these parameters
            CartItem item = new CartItem(
                rs.getInt("product_id"),
                rs.getString("name"), // Get product name from the result set
                rs.getInt("quantity"),        // Get quantity from the result set
                rs.getDouble("price")         // Get price from the result set
            );
            cartItems.add(item);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return cartItems;
}
 public String getEmailByUsername(String username) throws SQLException {
    String email = null; // Initialize the email variable
    String query = "SELECT email FROM customers WHERE cuser_name = ?"; // SQL query

    // Ensure the connection is established
    try (Connection connection = db.connect(); // Use db.connect() directly
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, username); // Set the username parameter
        ResultSet resultSet = statement.executeQuery(); // Execute the query

        // Check if a result was returned
        if (resultSet.next()) {
            email = resultSet.getString("email"); // Get the email from the result set
        }
    } catch (SQLException e) {
        // Print detailed error message
        System.err.println("SQL Exception: " + e.getMessage());
        throw e; // Propagate the exception to the caller
    }

    return email; // Return the email or null if not found
}

 
// Remove a specific item from the cart
public void removeItemFromCart(String username, int productId) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = connect(); // Ensure you're using the correct connection method
        String sql = "DELETE FROM cart WHERE user_name = ? AND product_id = ?"; // Adjust SQL query accordingly
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setInt(2, productId);

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Item removed from cart successfully.");
        } else {
            System.out.println("No item found for removal.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



// Clear the entire cart for the user
public void clearCart(String username) {
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        // Get a connection to the database
       conn = Database.this.connect();

        // SQL query to clear all items from the cart for the logged-in user
        String sql = "DELETE FROM cart WHERE user_name = ?";

        // Prepare the statement
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);

        // Execute the query
        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Cart cleared successfully.");
        } else {
            System.out.println("Cart is already empty.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the resources
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

public double getProductPriceById(String productId) {
    double price = 0.0;
    // Logic to fetch the price from your database using the productId
    // For example:
    try {
        // Assuming you have a connection to your database
        String query = "SELECT price FROM products WHERE product_id = ?";
        PreparedStatement preparedStatement = connect().prepareStatement(query);
        preparedStatement.setString(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            price = resultSet.getDouble("price");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return price;
}


public int getItemQuantityInCart(String email, int productId) {
    int quantity = 0;
    // Logic to retrieve quantity from the database based on email and productId
    // Assuming you have a cart_items table or similar
    String query = "SELECT quantity FROM cart WHERE email = ? AND product_id = ?";
    try (PreparedStatement pstmt = connect().prepareStatement(query)) {
        pstmt.setString(1, email);
        pstmt.setInt(2, productId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            quantity = rs.getInt("quantity");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return quantity;
}

public boolean checkIfUserExists(String email) {
    // SQL query to find a user by email or username
    String query = "SELECT COUNT(*) FROM customers WHERE email = ? ";
    
    // Try-with-resources to handle database connection and statement automatically
    try (Connection conn = connect(); // Method to get your database connection
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        // Set the email or username in the query
        stmt.setString(1, email);  // For email
        

        // Execute the query and get the result
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1); // Get the count result
                return count > 0; // If count > 0, user exists
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Log or handle SQL exception
    }

    // If the user does not exist or an error occurs, return false
    return false;
}

public boolean resetPassword(String email, String newPassword) {
    // SQL query to update the password for the user with the given email or username
    String query = "UPDATE customers SET password = ? WHERE email = ? ";
    
    // Try-with-resources to handle database connection and statement automatically
    try (Connection conn = connect(); // Method to get your database connection
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the new password and the email/username in the query
        stmt.setString(1, newPassword);       // The new password (parameter 1)
        stmt.setString(2, email);   // The email (parameter 2)
      

        // Execute the update query
        int rowsAffected = stmt.executeUpdate(); 
        
        // If rowsAffected is greater than 0, that means the password was updated
        return rowsAffected > 0;

    } catch (SQLException e) {
        e.printStackTrace(); // Log or handle SQL exception
    }

    // If an error occurs, return false
    return false;
}



public boolean savePayment(String userName, double totalBill, double paymentAmount) throws SQLException {
    String query = "INSERT INTO billing_info (user_name, total_bill, payment_amount, payment_status) VALUES (?, ?, ?, ?)";
    
    try (Connection conn = connect(); 
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        // Set values for the SQL statement
        stmt.setString(1, userName);          // Set user email
        stmt.setDouble(2, totalBill);          // Set total bill amount
        stmt.setDouble(3, paymentAmount);      // Set payment amount
        stmt.setString(4, "Completed");        // Set payment status (could be "Pending" or "Completed")

        // Execute the update query
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;               // Return true if the insertion was successful

    } catch (SQLException e) {
        e.printStackTrace();
        return false;                          // Return false if there is an error
    }

}

 


public boolean createWishlist(String userEmail, String wishlistName) {
    // Check if the wishlist already exists
    String checkWishlistQuery = "SELECT COUNT(*) FROM wishlists WHERE user_email = ? AND wishlist_name = ?";
    
    try (Connection conn = connect();
         PreparedStatement checkStmt = conn.prepareStatement(checkWishlistQuery)) {
         
        checkStmt.setString(1, userEmail);
        checkStmt.setString(2, wishlistName);
        
        try (ResultSet rs = checkStmt.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                return false; // Wishlist already exists
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Error checking wishlist existence
    }

    // Proceed to create the wishlist
    String insertWishlistQuery = "INSERT INTO wishlists (user_email, wishlist_name) VALUES (?, ?)";

    try (Connection conn = connect();
         PreparedStatement insertStmt = conn.prepareStatement(insertWishlistQuery)) {
         
        insertStmt.setString(1, userEmail);
        insertStmt.setString(2, wishlistName);
        insertStmt.executeUpdate();
        return true; // Wishlist created successfully
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Error creating wishlist
    }
}

 public  boolean addProductToWishlist(String userEmail, String wishlistName, String productName) {
        String productIdQuery = "SELECT product_id FROM products WHERE name = ?";
        String productInsertQuery = "INSERT INTO wishlist_items (wishlist_id, product_id) " +
                                     "VALUES ((SELECT id FROM wishlists WHERE user_email = ? AND wishlist_name = ?), ?)";

        try (Connection conn = this.connect()) {
            // Get the product ID based on the selected product name
            int productId = 0;
            try (PreparedStatement productIdStmt = conn.prepareStatement(productIdQuery)) {
                productIdStmt.setString(1, productName);
                try (ResultSet productIdRs = productIdStmt.executeQuery()) {
                    if (productIdRs.next()) {
                        productId = productIdRs.getInt("product_id");
                    } else {
                        return false; // Product not found
                    }
                }
            }

            // Insert the product into the wishlist_items table
            try (PreparedStatement productPstmt = conn.prepareStatement(productInsertQuery)) {
                productPstmt.setString(1, userEmail);
                productPstmt.setString(2, wishlistName);
                productPstmt.setInt(3, productId);
                productPstmt.executeUpdate();
            }
            return true; // Product added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error adding product to wishlist
        }
 }
 
  public  List<String> getAllProducts() {
        List<String> productList = new ArrayList<>();
        String productQuery = "SELECT name FROM products"; // Adjust based on your products table

        try (Connection conn = this.connect();
             PreparedStatement productStmt = conn.prepareStatement(productQuery);
             ResultSet rs = productStmt.executeQuery()) {
             
            while (rs.next()) {
                productList.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
  
  
            
public List<String> getItemsInWishlist(String userEmail, String wishlistName) {
    List<String> items = new ArrayList<>();
    String query = "SELECT p.name " +
                   "FROM wishlist_items wi " +
                   "JOIN products p ON wi.product_id = p.product_id " +
                   "JOIN wishlists w ON wi.wishlist_id = w.id " +
                   "WHERE w.user_email = ? AND w.wishlist_name = ?;";

    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, userEmail);
        stmt.setString(2, wishlistName);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            items.add(rs.getString("name"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle exceptions as needed
    }
    return items;
}
  
  
  public boolean deleteWishlist(String userEmail, String wishlistName) {
        String query = "DELETE FROM wishlists WHERE user_email = ? AND wishlist_name = ?";
        
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            // Set parameters
            pstmt.setString(1, userEmail);
            pstmt.setString(2, wishlistName);
            
            // Execute the delete statement
            int rowsAffected = pstmt.executeUpdate();
            
            // Return true if a row was deleted, false otherwise
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception in production
            return false; // Return false if there was an error
        }
    }
  
  
 public boolean deleteItemFromWishlist(String userEmail, String wishlistName, String productName) {
    String query = "DELETE wi " +
                   "FROM wishlist_items wi " +
                   "JOIN wishlists w ON wi.wishlist_id = w.id " +
                   "WHERE w.user_email = ? AND w.wishlist_name = ? AND wi.product_id = ( " +
                   "    SELECT p.product_id FROM products p WHERE p.name = ? " +
                   ");";

    // Use a boolean to track success
    boolean isDeleted = false;

    // Prepare statement and set parameters
    try (PreparedStatement pstmt = this.connect().prepareStatement(query)) {
        pstmt.setString(1, userEmail);      // Set user email
        pstmt.setString(2, wishlistName);    // Set wishlist name
        pstmt.setString(3, productName);     // Set product name

        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Item deleted successfully.");
            isDeleted = true; // Mark as deleted
        } else {
            System.out.println("No matching item found to delete.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isDeleted; // Return the deletion status
}


  
  
  public List<String> getWishlistsByUser(String userEmail) {
    List<String> wishlists = new ArrayList<>();
    String query = "SELECT wishlist_name FROM wishlists WHERE user_email = ?"; // Adjust table and column names accordingly

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
         
        pstmt.setString(1, userEmail);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            String wishlistName = rs.getString("wishlist_name"); // Adjust column name accordingly
            wishlists.add(wishlistName);
        }

    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception in production
    }
    
    return wishlists;
}
 public String getGiftSuggestions(String relationship, String gender, String ageGroup) {
        StringBuilder giftSuggestion = new StringBuilder();

        // SQL query to fetch gift suggestions
        String query = "SELECT p.name, p.category, p.description " +
                       "FROM gift_suggestions gs " +
                       "JOIN products p ON gs.product_id = p.product_id " +
                       "WHERE gs.relationship = ? AND gs.gender = ? AND gs.age_group = ?";

        try (Connection conn = connect(); // Ensure connect() returns a valid Connection
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, relationship);
            stmt.setString(2, gender);
            stmt.setString(3, ageGroup);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                String description = rs.getString("description");
                giftSuggestion.append("Product Name: ").append(name)
                              .append("\nCategory: ").append(category)
                              .append("\nDescription: ").append(description)
                              .append("\n\n");
            }

            if (giftSuggestion.length() == 0) {
                giftSuggestion.append("No gifts found for the selected criteria.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            giftSuggestion.append("Error retrieving gift suggestions: ").append(e.getMessage());
        }

        return giftSuggestion.toString();
    }
 
 public class Product {
    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}
 
 public boolean checkEmailExists(String email) {
    String query = "SELECT * FROM customers WHERE email = ?";
    try (Connection conn = this.connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next(); // Returns true if an email record is found
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
 
     public List<CartItem> getCartItemsByUsername(String username) {
    List<CartItem> cartItems = new ArrayList<>();
    String query = "SELECT p.product_id, p.name, c.quantity, p.price " +
                   "FROM cart c " +
                   "JOIN products p ON c.product_id = p.product_id " +
                   "WHERE c.user_name = ?";
    
    try (Connection conn = db.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, username); // Set the username parameter
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int productId = rs.getInt("product_id");
            String productName = rs.getString("name");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            
            // Create a CartItem object and add it to the list
            CartItem item = new CartItem(productId, productName, quantity, price);
            cartItems.add(item);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace for debugging
    }
    return cartItems; // Return the list of cart items
}
}