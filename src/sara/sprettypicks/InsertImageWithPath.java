package sara.sprettypicks;

import java.sql.*;
import java.io.*;
import javax.swing.*; // For ImageIcon and JFrame
import java.awt.*;    // For Dimension
import java.util.logging.Level;
import java.util.logging.Logger;
import sara.sprettypicks.Database;
import sara.sprettypicks.SessionManager;

public class InsertImageWithPath extends JFrame {

    private static JPanel mainPanel; // Main panel to display products
    public static JLabel cartLabel; // Label to display the cart count
    private static int cartItemCount = 0; // To keep track of items in the cart

    public InsertImageWithPath() {
         
          cartLabel = new JLabel("Cart (0 items)");
      
        
        //createSearchableProductDisplay();
       //displayAllProducts();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public static void main(String[] args) {

        // Insert Product Data
//        insertProductImage(1, "tumbler", "bottle", "bottles", 13.99, 12, "C:\\SARA University Data\\FIFTH SEMESTER\\SCD LAB\\tumbler.jpg");
//        insertProductImage(2, "blue silicon case", "phone cases", "a pretty case phone case mobile cover", 123.99, 12, "C:\\initialshopping\\phonecase2.jpg");
//        insertProductImage(3, "shiny case", "phone cases", "a pretty case phone case mobile cover", 12.99, 12, "C:\\initialshopping\\phonecase1.jpg");
//        insertProductImage(4, "wavy case", "phone cases", "a pretty case phone case mobile cover", 19.99, 22, "C:\\initialshopping\\phonecase3.jpg");
//        insertProductImage(5, "purple case", "phone cases", "a pretty case phone case mobile cover", 19.99, 12, "C:\\initialshopping\\phonecase6.jpg");
//        insertProductImage(6, "sea case", "phone cases", "a pretty case phone case mobile cover", 10.99, 12, "C:\\initialshopping\\phonecase9.jpg");
//        insertProductImage(7, "purple cream", "skin care", "a soothing cream skin product", 11.99, 34, "C:\\initialshopping\\skin0.jpg");
//        insertProductImage(8, "honey and lemon cream", "skin care", "a soothing cream skin product", 12.99, 34, "C:\\initialshopping\\skin1.jpg");
//        insertProductImage(9, "neem wash", "skin care", "a soothing face wash skin product", 13.99, 20, "C:\\initialshopping\\skin3.jpg");
//        insertProductImage(10, "pink face wash", "skin care", "a soothing face wash skin product", 17.00, 34, "C:\\initialshopping\\skin5.jpg");
//        insertProductImage(11, "cetaphil wash", "skin care", "a soothing face wash skin product", 11.99, 10, "C:\\initialshopping\\skin6.jpg");
//        insertProductImage(12, "red serum", "skin care", "a nourishing serum skin product", 23.99, 34, "C:\\initialshopping\\skin10.jpg");
//        insertProductImage(13, "Pink scarf", "hijabs", "a full coverage hijab scarf", 90.22, 90, "C:\\initialshopping\\hijab1.jpg");
//        insertProductImage(14, "Green royal", "hijabs", "a full coverage hijab scarf", 21.99, 10, "C:\\initialshopping\\hijab4.jpg");
//        insertProductImage(15, "Grey scarf", "hijabs", "a soft hijab", 11.99, 45, "C:\\initialshopping\\hijab5.jpg");
//        insertProductImage(34, "aloevera ", "skin care", "a soothing face wash skin product", 14.99, 34, "C:\\initialshopping\\skin14.jpg");
//        insertProductImage(35, "clear serum ", "skin care", "a soothing face wash skin product", 14.99, 34, "C:\\initialshopping\\skin11.jpg");
//         insertProductImage(17, "lavender case ", "phone case", "a pretty case phone case mobile cover", 19.99, 34, "C:\\initialshopping\\phonecase5.jpg");
//          insertProductImage(18, "tumbler set of 3", "bottle", "bottles", 13.99, 12, "C:\\initialshopping\\tumbler1.jpg");
//          insertProductImage(19, "named tumbler", "bottle", "bottles tumbler bottle", 13.99, 12, "C:\\initialshopping\\tumbler2.jpg");
          
          
        // Create GUI with search functionality
        ////////////////////////////////
        createSearchableProductDisplay();
       // displayAllProducts();
    }
  

   // Ensure the method is inside the class with access to the JFrame and other components

// Method to update the cart item count
 static void updateCartItemCount(JLabel cartLabel) {
    if (cartLabel == null) {
        System.out.println("cartLabel is null. Please check initialization.");
        return;
    }

    String userEmail = SessionManager.getLoggedInUserEmail();
    String userName=SessionManager.getLoggedInUserName();
    if (userEmail == null || userEmail.isEmpty()) {
        System.out.println("User email is null or empty. Cannot fetch cart items.");
        return;
    }

    Database db = Database.getInstance();
    java.util.List<CartItem> cartItems = db.getCartItemsByUsername(userName);

    int totalQuantity = 0;
    for (CartItem item : cartItems) {
        totalQuantity += item.getQuantity();
    }
//cartLabel.setText(totalQuantity + " items");
    System.out.println("Total items in cart: " + totalQuantity); // Debugging output
   // cartLabel.setText(totalQuantity + " items");
   
       cartLabel.setText(totalQuantity + " items");
    
}








    // Method to insert product image
    private static void insertProductImage(int productId, String name, String category, String description, double price, int quantity, String imagePath) {
        try {
            // Get the database connection
            Database db = Database.getInstance();
            Connection conn = db.connect();

            // Prepare the SQL query with placeholders
            String query = "INSERT INTO products (product_id, name, category, description, price, quantity, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            // Set the product data in the query
            stmt.setInt(1, productId);
            stmt.setString(2, name);
            stmt.setString(3, category);
            stmt.setString(4, description);
            stmt.setDouble(5, price);
            stmt.setInt(6, quantity);

            // Read the image file as binary data
            FileInputStream fis = new FileInputStream(imagePath);
            stmt.setBinaryStream(7, fis, (int) new File(imagePath).length());

            // Execute the insert statement
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

            // Close resources
            fis.close();
            stmt.close();
            conn.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // Method to create a searchable product display
// Declare the mainPanel globally, so it can be accessed inside other methods


public static JFrame createSearchableProductDisplay() {
    // Create a JFrame for the product display
    JFrame frame = new JFrame("Product Search");
    frame.setLayout(new BorderLayout());

    // Create the top bar (search and cart panel)
    JPanel topBar = new JPanel();
    topBar.setLayout(new BorderLayout());
    topBar.setBackground(new Color(200, 200, 240));

    // Create the search panel
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
    searchPanel.setBackground(new Color(200, 200, 240));

    JTextField searchField = new JTextField(15);
    searchField.setPreferredSize(new Dimension(200, 30)); // Input field for search
    JButton searchButton = new JButton("Search");
    searchButton.setBackground(new Color(255, 140, 0)); // Darker orange background color
    searchButton.setForeground(Color.BLACK); // Black text color
    searchButton.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font style with size 14
    searchButton.setPreferredSize(new Dimension(100, 30));

    // Add action listener to the search button
    searchButton.addActionListener(e -> {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            displayAllProducts(); // Display all products when search field is empty
        } else {
            displayProducts(keyword); // Display filtered products based on search keyword
        }
    });

    searchPanel.add(searchField);
    searchPanel.add(searchButton);

    // Create the cart panel
    JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
    cartPanel.setBackground(new Color(200, 200, 240));

    // Load and scale the cart icon
    ImageIcon originalCartIcon = new ImageIcon("C:\\initialshopping\\cart.png"); // Replace with your cart icon path
    Image scaledCartImage = originalCartIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Adjust size
    ImageIcon scaledCartIcon = new ImageIcon(scaledCartImage);

    // Create the cart icon and label
    JLabel cartIcon = new JLabel(scaledCartIcon); 

    // Create a label for the cart count (number of items in cart)
    //*********************8
   // JLabel cartLabel = new JLabel("0 items"); // Default text (0 items)
    cartLabel.setFont(new Font("Arial", Font.PLAIN, 12));

    // Add both the cart icon and cart label to the cart panel side by side
    cartPanel.add(cartIcon);
    cartPanel.add(cartLabel);
cartIcon.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            showcartitems ob = new showcartitems();
            ob.cart(); // Trigger the cart display
            updateCartItemCount(cartLabel); // Update cart count when cart icon is clicked
        }
    });

    // Add search and cart panels to the top bar
    topBar.add(searchPanel, BorderLayout.CENTER);
    topBar.add(cartPanel, BorderLayout.EAST);

    // Add the top bar to the frame
    frame.add(topBar, BorderLayout.NORTH);

    // Initialize the main panel for product display with GridLayout
    mainPanel = new JPanel(); // Initialize the mainPanel here
    mainPanel.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columns with gaps between
    mainPanel.setBackground(new Color(230, 230, 250));

    // Add a scroll pane to the main panel for product display
    frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER);

    // Adjust frame properties
    frame.setSize(new Dimension(800, 600));
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setVisible(true);

    // Ensure displayAllProducts() is only called once when setting up the UI
    displayAllProducts(); // Display all products by default

    return frame; // Return the frame for further customization if needed
}


public void showcartvalue(){
    System.out.println("Showcartvalue called");
    String userName=SessionManager.getLoggedInUserName();
    if(userName!=null && !userName.isEmpty()){
         updateCartItemCount(cartLabel);
    }
         else{
                 cartLabel.setText("Cart (0 items)");
                 }
    
}

public static void displayAllProducts() {
    try {
        // Clear the main panel before displaying new products
        if (mainPanel != null) {
            mainPanel.removeAll(); // Safely call removeAll on mainPanel
        }

        // Get the database connection
        Database db = Database.getInstance();
        Connection conn = db.connect();

        // Query to retrieve all products from the database
        String query = "SELECT product_id, image, price, quantity FROM products";
        PreparedStatement stmt = conn.prepareStatement(query);

        // Execute the query
        ResultSet rs = stmt.executeQuery();

        // Loop through each product
        while (rs.next()) {
            int productId = rs.getInt("product_id");
            Blob blob = rs.getBlob("image");

            // Check if the blob is null
            if (blob != null) {
                byte[] imageData = blob.getBytes(1, (int) blob.length());
                ImageIcon imageIcon = new ImageIcon(imageData);

                double price = rs.getDouble("price");
               int quantityAvailable = rs.getInt("quantity");

                // Create a panel for each product
                JPanel productPanel = new JPanel();
                productPanel.setLayout(new BorderLayout());
                productPanel.setBackground(new Color(230, 230, 250));

                // Create and add image label
                JLabel imageLabel = new JLabel(imageIcon);
                productPanel.add(imageLabel, BorderLayout.CENTER);

                // Create labels and buttons for price, quantity, and actions
                JPanel bottomPanel = new JPanel();
                bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                bottomPanel.setBackground(new Color(230, 230, 250));

                JLabel priceLabel = new JLabel("Price: $" + price);
                JLabel quantityLabel = new JLabel("Quantity: 0");

                JButton increaseButton = new JButton("+");
               increaseButton.setBackground(new Color(255, 165, 0));
                increaseButton.setForeground(Color.BLACK);
                increaseButton.setBorderPainted(false);
                increaseButton.setFocusPainted(false);
                increaseButton.setFont(new Font("Arial", Font.BOLD, 12));
                increaseButton.setPreferredSize(new Dimension(30, 20));
                increaseButton.setMargin(new Insets(2, 5, 2, 5));
                increaseButton.addActionListener(e -> {
                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                    if (currentQuantity < quantityAvailable) {
                        currentQuantity++;
                        quantityLabel.setText("Quantity: " + currentQuantity);
                    }
                });

                JButton decreaseButton = new JButton("-");
                  decreaseButton.setBackground(new Color(255, 140, 0));
                decreaseButton.setForeground(Color.BLACK);
                decreaseButton.setBorderPainted(false);
                decreaseButton.setFocusPainted(false);
                decreaseButton.setFont(new Font("Arial", Font.BOLD, 12));
                decreaseButton.setPreferredSize(new Dimension(30, 20));
                decreaseButton.setMargin(new Insets(2, 5, 2, 5));
                decreaseButton.addActionListener(e -> {
                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                    if (currentQuantity > 0) {
                        currentQuantity--;
                        quantityLabel.setText("Quantity: " + currentQuantity);
                    }
                });

                JButton addToCartButton = new JButton("Add to Cart");
                 addToCartButton.setBackground(new Color(255, 165, 0));
                addToCartButton.setForeground(Color.BLACK);
                addToCartButton.setBorderPainted(false);
                addToCartButton.setFocusPainted(false);
                addToCartButton.setFont(new Font("Arial", Font.BOLD, 12));
                addToCartButton.setPreferredSize(new Dimension(100, 20));
                addToCartButton.setMargin(new Insets(5, 10, 5, 10));
              addToCartButton.addActionListener(e -> {
    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);

    if (currentQuantity <= 0) {
        JOptionPane.showMessageDialog(null, "Invalid quantity. Please select a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
      //  String useremail = SessionManager.getLoggedInUserEmail();
        String username=SessionManager.getLoggedInUserName();
        
        if (username != null && !username.isEmpty()) {
            db.addItemToCart(username, productId, currentQuantity, price);
            JOptionPane.showMessageDialog(null, "Product added to cart successfully! Quantity: " + currentQuantity);
            
            // Update the cart label with the latest item count
          updateCartItemCount(cartLabel);
        } else {
            JOptionPane.showMessageDialog(null, "You need to log in to add products to your cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});


                bottomPanel.add(priceLabel);
                bottomPanel.add(quantityLabel);
                bottomPanel.add(increaseButton);
                bottomPanel.add(decreaseButton);
                bottomPanel.add(addToCartButton);

                productPanel.add(bottomPanel, BorderLayout.SOUTH);

                // Add product panel to the main panel
                mainPanel.add(productPanel);
            }
        }

        // Refresh the main panel only once after all products are added
        mainPanel.revalidate();
        mainPanel.repaint();

        // Close resources
        rs.close();
        stmt.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}





//   public static void displayAllProducts() {
//       
//    try {
//        
//        // Clear the main panel before displaying new products
//        mainPanel.removeAll();
//
//        // Get the database connection
//        Database db = Database.getInstance();
//        Connection conn = db.connect();
//
//        // Query to retrieve all products from the database
//        String query = "SELECT product_id, image, price, quantity FROM products";
//        PreparedStatement stmt = conn.prepareStatement(query);
//
//        // Execute the query
//        ResultSet rs = stmt.executeQuery();
//
//        // Loop through each product
//        while (rs.next()) {
//            int productId = rs.getInt("product_id");
//            Blob blob = rs.getBlob("image");
//
//            // Check if the blob is null
//            if (blob != null) {
//                byte[] imageData = blob.getBytes(1, (int) blob.length());
//                ImageIcon imageIcon = new ImageIcon(imageData);
//
//                double price = rs.getDouble("price");
//                int quantityAvailable = rs.getInt("quantity"); // Total quantity available
//
//                // Create a panel for each product
//                JPanel productPanel = new JPanel();
//                productPanel.setLayout(new BorderLayout());
//                productPanel.setBackground(new Color(230, 230, 250));
//
//                // Create and add image label
//                JLabel imageLabel = new JLabel(imageIcon);
//                productPanel.add(imageLabel, BorderLayout.CENTER);
//
//                // Create labels and buttons for price, quantity, and actions
//                JLabel priceLabel = new JLabel("Price: $" + price);
//                JLabel quantityLabel = new JLabel("Quantity: 0");
//
//                JButton increaseButton = new JButton("+");
//                increaseButton.setBackground(new Color(255, 165, 0));
//                increaseButton.setForeground(Color.BLACK);
//                increaseButton.setBorderPainted(false);
//                increaseButton.setFocusPainted(false);
//                increaseButton.setFont(new Font("Arial", Font.BOLD, 12));
//                increaseButton.setPreferredSize(new Dimension(30, 20));
//                increaseButton.setMargin(new Insets(2, 5, 2, 5));
//                increaseButton.addActionListener(e -> {
//                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
//                    if (currentQuantity < quantityAvailable) {
//                        currentQuantity++;
//                        quantityLabel.setText("Quantity: " + currentQuantity);
//                    }
//                });
//
//                JButton decreaseButton = new JButton("-");
//                decreaseButton.setBackground(new Color(255, 140, 0));
//                decreaseButton.setForeground(Color.BLACK);
//                decreaseButton.setBorderPainted(false);
//                decreaseButton.setFocusPainted(false);
//                decreaseButton.setFont(new Font("Arial", Font.BOLD, 12));
//                decreaseButton.setPreferredSize(new Dimension(30, 20));
//                decreaseButton.setMargin(new Insets(2, 5, 2, 5));
//                decreaseButton.addActionListener(e -> {
//                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
//                    if (currentQuantity > 0) {
//                        currentQuantity--;
//                        quantityLabel.setText("Quantity: " + currentQuantity);
//                    }
//                });
//
//                JButton addToCartButton = new JButton("Add to Cart");
//                addToCartButton.setBackground(new Color(255, 165, 0));
//                addToCartButton.setForeground(Color.BLACK);
//                addToCartButton.setBorderPainted(false);
//                addToCartButton.setFocusPainted(false);
//                addToCartButton.setFont(new Font("Arial", Font.BOLD, 12));
//                addToCartButton.setPreferredSize(new Dimension(100, 20));
//                addToCartButton.setMargin(new Insets(5, 10, 5, 10));
//               addToCartButton.addActionListener(e -> {
//    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
//    
//    // Check if the current quantity is greater than 0
//    if (currentQuantity <= 0) {
//        // Display an error message if the quantity is invalid (less than or equal to 0)
//        JOptionPane.showMessageDialog(null, "Invalid quantity. Please select a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
//    } else {
//        String useremail = SessionManager.getLoggedInUserEmail();
//        
//        if (useremail != null && !useremail.isEmpty()) {
//            // Add item to the cart in the database or session if the user is logged in
//            db.addItemToCart(useremail, productId, currentQuantity, price);
//            
//            // Show the success message after adding to cart
//            JOptionPane.showMessageDialog(null, "Product added to cart successfully! Quantity: " + currentQuantity);
//            
//            // Update the cart item count after adding the item to the cart
//            updateCartItemCount(cartLabel);
//        } else {
//            // Show an error message if the user is not logged in
//            JOptionPane.showMessageDialog(null, "You need to log in to add products to your cart.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//});
//
//                // Create a panel for the bottom section with price, quantity, and buttons
//                JPanel bottomPanel = new JPanel();
//                bottomPanel.setBackground(new Color(230, 230, 250));
//                bottomPanel.add(priceLabel);
//                bottomPanel.add(quantityLabel);
//                bottomPanel.add(increaseButton);
//                bottomPanel.add(decreaseButton);
//                bottomPanel.add(addToCartButton);
//
//                // Add bottom panel to the product panel
//                productPanel.add(bottomPanel, BorderLayout.SOUTH);
//
//                // Add product panel to the main panel
//                mainPanel.add(productPanel);
//            }
//        }
//
//        // Refresh the main panel only once after all products are added
//        mainPanel.revalidate(); 
//        mainPanel.repaint(); 
//
//        // Close resources
//        rs.close();
//        stmt.close();
//        conn.close();
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}



    // Method to retrieve and display the product images based on category
    // Method to retrieve and display the product images based on category
    public static void displayProducts(String keyword) {
        try {
            // Clear the main panel before displaying new products
            mainPanel.removeAll();

            // Get the database connection
            Database db = Database.getInstance();
            Connection conn = db.connect();

            // Query to retrieve products matching the keyword in category or description
            String query = "SELECT product_id, image, price, quantity FROM products WHERE category LIKE ? OR description LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            String likeKeyword = "%" + keyword + "%"; // To allow for partial matches
            stmt.setString(1, likeKeyword);
            stmt.setString(2, likeKeyword);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Loop through each product
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                Blob blob = rs.getBlob("image");

                // Check if the blob is null
                if (blob != null) {
                    byte[] imageData = blob.getBytes(1, (int) blob.length());
                    ImageIcon imageIcon = new ImageIcon(imageData);

                    double price = rs.getDouble("price");
//                    int quantityAvailable = rs.getInt("quantity"); // Total quantity available

                    // Create a panel for each product
                    JPanel productPanel = new JPanel();
                    productPanel.setBackground(new Color(230, 230, 250)); // Light purple background color
                    productPanel.setLayout(new BorderLayout());

                    // Create and add image label
                    JLabel imageLabel = new JLabel(imageIcon);
                    productPanel.add(imageLabel, BorderLayout.CENTER);

                    // Create labels and buttons for price, quantity, and actions
                    JLabel priceLabel = new JLabel("Price: $" + price);
                    JLabel quantityLabel = new JLabel("Quantity: 0");
int quantityAvailable = rs.getInt("quantity");
                    // Button to increase quantity
                    JButton increaseButton = new JButton("+");
                     increaseButton.setBackground(new Color(255, 165, 0));
                increaseButton.setForeground(Color.BLACK);
                increaseButton.setBorderPainted(false);
                increaseButton.setFocusPainted(false);
                increaseButton.setFont(new Font("Arial", Font.BOLD, 12));
                increaseButton.setPreferredSize(new Dimension(30, 20));
                increaseButton.setMargin(new Insets(2, 5, 2, 5));
                   increaseButton.addActionListener(e -> {
                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                    if (currentQuantity < quantityAvailable) {
                        currentQuantity++;
                        quantityLabel.setText("Quantity: " + currentQuantity);
                    }
                });

                    JButton decreaseButton = new JButton("-");
 decreaseButton.setBackground(new Color(255, 140, 0));
                decreaseButton.setForeground(Color.BLACK);
                decreaseButton.setBorderPainted(false);
                decreaseButton.setFocusPainted(false);
                decreaseButton.setFont(new Font("Arial", Font.BOLD, 12));
                decreaseButton.setPreferredSize(new Dimension(30, 20));
                decreaseButton.setMargin(new Insets(2, 5, 2, 5));
                    decreaseButton.addActionListener(e -> {
                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                    if (currentQuantity > 0) {
                        currentQuantity--;
                        quantityLabel.setText("Quantity: " + currentQuantity);
                    }
                });

                    // Button to add to cart
                    JButton addToCartButton = new JButton("Add to Cart");
                addToCartButton.setBackground(new Color(255, 165, 0));
                addToCartButton.setForeground(Color.BLACK);
                addToCartButton.setBorderPainted(false);
                addToCartButton.setFocusPainted(false);
                addToCartButton.setFont(new Font("Arial", Font.BOLD, 12));
                addToCartButton.setPreferredSize(new Dimension(100, 20));
                addToCartButton.setMargin(new Insets(5, 10, 5, 10));
              addToCartButton.addActionListener(e -> {
    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);

    if (currentQuantity <= 0) {
        JOptionPane.showMessageDialog(null, "Invalid quantity. Please select a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        String useremail = SessionManager.getLoggedInUserEmail();
        
        if (useremail != null && !useremail.isEmpty()) {
            db.addItemToCart(useremail, productId, currentQuantity, price);
            JOptionPane.showMessageDialog(null, "Product added to cart successfully! Quantity: " + currentQuantity);
            
            // Update the cart label with the latest item count
          updateCartItemCount(cartLabel);
        } else {
            JOptionPane.showMessageDialog(null, "You need to log in to add products to your cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
});



                    // Create a panel for the bottom section with price, quantity, and buttons
                    JPanel bottomPanel = new JPanel();
                    bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Center alignment with spacing

                    bottomPanel.add(priceLabel);
                bottomPanel.add(quantityLabel);
                bottomPanel.add(increaseButton);
                bottomPanel.add(decreaseButton);
                bottomPanel.add(addToCartButton);

                    bottomPanel.setBackground(new Color(230, 230, 250)); // Light purple background color
 
              

                    // Add bottom panel to the product panel
                    productPanel.add(bottomPanel, BorderLayout.SOUTH);

                    // Add product panel to the main panel
                    mainPanel.add(productPanel);
                }
            }

            // Refresh the main panel to display the updated products
            mainPanel.revalidate();
            mainPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error displaying products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
