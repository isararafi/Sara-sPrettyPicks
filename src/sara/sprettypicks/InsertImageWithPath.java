package sara.sprettypicks;
import java.sql.*;
import java.io.*;
import javax.swing.*; // For ImageIcon and JFrame
import java.awt.*;    // For Dimension
import sara.sprettypicks.Database;
import sara.sprettypicks.SessionManager;

public class InsertImageWithPath  extends JFrame{
    private static JPanel mainPanel; // Main panel to display products
    
public InsertImageWithPath() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }
    public static void main(String[] args) {
        
        // Insert Product Data
        insertProductImage(1, "tumbler", "bottle", "bottles", 13.99, 12, "C:\\SARA University Data\\FIFTH SEMESTER\\SCD LAB\\tumbler.jpg");
        insertProductImage(2, "blue silicon case", "phone cases", "a pretty case phone case mobile cover", 123.99, 12, "C:\\initialshopping\\phonecase1.jpg");
        insertProductImage(3, "shiny case", "phone cases", "a pretty case phone case mobile cover", 12.99, 12, "C:\\initialshopping\\phonecase2.jpg");
        insertProductImage(4, "wavy case", "phone cases", "a pretty case phone case mobile cover", 19.99, 22, "C:\\initialshopping\\phonecase3.jpg");
        insertProductImage(5, "purple case", "phone cases", "a pretty case phone case mobile cover", 19.99, 12, "C:\\initialshopping\\phonecase6.jpg");
        insertProductImage(6, "sea case", "phone cases", "a pretty case phone case mobile cover", 10.99, 12, "C:\\initialshopping\\phonecase9.jpg");
        insertProductImage(7, "purple cream", "skin care", "a soothing cream skin product", 11.99, 34, "C:\\initialshopping\\skin0.jpg");
        insertProductImage(8, "honey and lemon cream", "skin care", "a soothing cream skin product", 12.99, 34, "C:\\initialshopping\\skin1.jpg");
        insertProductImage(9, "neem wash", "skin care", "a soothing face wash skin product", 13.99, 20, "C:\\initialshopping\\skin3.jpg");
        insertProductImage(10, "pink face wash", "skin care", "a soothing face wash skin product", 17.00, 34, "C:\\initialshopping\\skin5.jpg");
        insertProductImage(11, "cetaphil wash", "skin care", "a soothing face wash skin product", 11.99, 10, "C:\\initialshopping\\skin6.jpg");
        insertProductImage(12, "red serum", "skin care", "a nourishing serum skin product", 23.99, 34, "C:\\initialshopping\\skin10.jpg");
        insertProductImage(13,"Pink scarf", "hijabs", "a full coverage hijab scarf", 90.22, 90, "C:\\initialshopping\\hijab1.jpg");
        insertProductImage(14, "Green royal", "hijabs", "a full coverage hijab scarf", 21.99, 10, "C:\\initialshopping\\hijab4.jpg");
//        insertProductImage(15, "Grey scarf", "hijabs", "a soft hijab", 11.99, 45, "C:\\initialshopping\\hijab5.jpg");
        //insertProductImage(16, "Yellow scarf", "hijabs", "a soft hijab", 14.99, 34, "C:\\initialshopping\\hijab11.jpg");
        
        
        // Create GUI with search functionality
        createSearchableProductDisplay();
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
   public static JFrame createSearchableProductDisplay() {
    // Create a JFrame for the product display
    JFrame frame = new JFrame("Product Search");
    frame.setLayout(new BorderLayout());

    // Create a JPanel for the search bar
    JPanel searchPanel = new JPanel();
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
        String keyword = searchField.getText().trim(); // Get the text from the search field

        // If the search field is empty, display all products
        if (keyword.isEmpty()) {
            displayAllProducts();
        } else {
            displayProducts(keyword); // Pass the keyword to displayProducts
        }
    });

    searchPanel.add(searchField);
    searchPanel.add(searchButton);
    frame.add(searchPanel, BorderLayout.NORTH);

    // Initialize the main panel for product display
    mainPanel = new JPanel();
    mainPanel.setLayout(new GridLayout(0, 3)); // Adjust grid layout as needed (3 columns)
    mainPanel.setBackground(new Color(230, 230, 250));
    frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER); // Add scrollable area for products

    // Adjust frame properties
    frame.setSize(new Dimension(800, 600)); // Adjust as needed
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose only this frame when closed
    frame.setVisible(true);

    displayAllProducts(); // Display all products by default

    return frame; // Return the frame for further customization if needed
}

    // Method to retrieve and display all product images
    public static void displayAllProducts() {
        try {
            // Clear the main panel before displaying new products
            mainPanel.removeAll();

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
                    int quantityAvailable = rs.getInt("quantity"); // Total quantity available

                    // Create a panel for each product
                    JPanel productPanel = new JPanel();
                    productPanel.setLayout(new BorderLayout());
                    productPanel.setBackground(new Color(230, 230, 250)); 

                    // Create and add image label
                    JLabel imageLabel = new JLabel(imageIcon);
                    productPanel.add(imageLabel, BorderLayout.CENTER);

                    // Create labels and buttons for price, quantity, and actions
                    JLabel priceLabel = new JLabel("Price: $" + price);
                    JLabel quantityLabel = new JLabel("Quantity: 0");

                    // Button to increase quantity
                    JButton increaseButton = new JButton("+");
                   increaseButton.setBackground(new Color(255, 165, 0)); // Set background color to orange
increaseButton.setForeground(Color.BLACK); // Set text color to white
increaseButton.setBorderPainted(false); // Remove border
increaseButton.setFocusPainted(false); // Remove focus outline
increaseButton.setFont(new Font("Arial", Font.BOLD, 12)); // Set font size to 12 for a smaller appearance


JButton decreaseButton = new JButton("-");
decreaseButton.setBackground(new Color(255, 140, 0)); // Set background color to orange
decreaseButton.setForeground(Color.BLACK); // Set text color to black
decreaseButton.setBorderPainted(false); // Remove border
decreaseButton.setFocusPainted(false); // Remove focus outline
decreaseButton.setFont(new Font("Arial", Font.BOLD, 12)); // Set font size
decreaseButton.setPreferredSize(new Dimension(30, 20)); // Set button size
decreaseButton.setMargin(new Insets(2, 5, 2, 5)); // Set margin for top, left, bottom, right


// Set a smaller preferred size
increaseButton.setPreferredSize(new Dimension(30, 20)); // Set button size

// Reduce padding
increaseButton.setMargin(new Insets(2, 5, 2, 5)); // Set margin for top, left, bottom, right
                    increaseButton.addActionListener(e -> {
                        int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                        if (currentQuantity < quantityAvailable) { // Check against available quantity
                            currentQuantity++;
                            quantityLabel.setText("Quantity: " + currentQuantity);
                        }
                    });
                    
//                    JButton decreaseButton = new JButton("-");

               
               decreaseButton.addActionListener(e -> {
    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
    if (currentQuantity > 0) {
        currentQuantity--;
        quantityLabel.setText("Quantity: " + currentQuantity);
    }
});


                    // Button to add to cart
                    JButton addToCartButton = new JButton("Add to Cart");
                    addToCartButton.setBackground(new Color(255, 165, 0)); // Set background color to orange
                    addToCartButton.setForeground(Color.BLACK); // Set text color to white
                    addToCartButton.setBorderPainted(false); // Remove border
                    addToCartButton.setFocusPainted(false); // Remove focus outline
                    addToCartButton.setFont(new Font("Arial", Font.BOLD, 12)); // Set font size to 12 for a smaller appearance

                    // Set a preferred size for the button
                    addToCartButton.setPreferredSize(new Dimension(100, 20)); // Set button size

                    // Reduce padding
                    addToCartButton.setMargin(new Insets(5, 10, 5, 10)); // Set margin for top, left, bottom, right
                                        addToCartButton.addActionListener(e -> {
                        int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                        String useremail = SessionManager.getLoggedInUserEmail();
                        if (useremail != null && !useremail.isEmpty()) {
                            // Add item to cart
                            db.addItemToCart(useremail, productId, currentQuantity, price);
                            JOptionPane.showMessageDialog(null, "Product added to cart successfully! Quantity: " + currentQuantity);
                        } else {
                            JOptionPane.showMessageDialog(null, "You need to log in to add products to your cart.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    });

                    // Create a panel for the bottom section with price, quantity, and buttons
                    JPanel bottomPanel = new JPanel();
//                    bottomPanel.setLayout(new FlowLayout()); // Horizontal layout
//                    bottomPanel.add(priceLabel);
//                    bottomPanel.add(quantityLabel);
//                    bottomPanel.add(increaseButton);
//                    bottomPanel.add(addToCartButton);
                    
                    // Add the decrease button
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

            mainPanel.revalidate(); // Refresh the main panel
            mainPanel.setBackground(new Color(230, 230, 250)); 
            mainPanel.repaint(); // Repaint to update UI

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                int quantityAvailable = rs.getInt("quantity"); // Total quantity available

                // Create a panel for each product
                JPanel productPanel = new JPanel();
                productPanel.setBackground(new Color(230, 230, 250)); // Light purple background color
                productPanel.setLayout(new BorderLayout());

                // Create and add image label
                JLabel imageLabel = new JLabel(imageIcon);
                productPanel.add(imageLabel, BorderLayout.CENTER);

                // Create labels and buttons for price, quantity, and actions
                JLabel priceLabel = new JLabel("Price: $" + price);
                JLabel quantityLabel = new JLabel("Quantity: 1");

                // Button to increase quantity
                JButton increaseButton = new JButton("+");
                increaseButton.addActionListener(e -> {
                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                    if (currentQuantity < quantityAvailable) { // Check against available quantity
                        currentQuantity++;
                        quantityLabel.setText("Quantity: " + currentQuantity);
                    }
                });
JButton decreaseButton = new JButton("-");

               
                decreaseButton.addActionListener(e -> {
                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                    if (currentQuantity > 1) {
                        currentQuantity--;
                        quantityLabel.setText("Quantity: " + currentQuantity);
                    }
                });

                // Button to add to cart
                JButton addToCartButton = new JButton("Add to Cart");
                addToCartButton.addActionListener(e -> {
                    int currentQuantity = Integer.parseInt(quantityLabel.getText().split(": ")[1]);
                    String useremail = SessionManager.getLoggedInUserEmail();
                    if (useremail != null && !useremail.isEmpty()) {
                        // Add item to cart
                        db.addItemToCart(useremail, productId, currentQuantity, price);
                        JOptionPane.showMessageDialog(null, "Product added to cart successfully! Quantity: " + currentQuantity);
                    } else {
                        JOptionPane.showMessageDialog(null, "You need to log in to add products to your cart.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });

                // Create a panel for the bottom section with price, quantity, and buttons
                JPanel bottomPanel = new JPanel();
               bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Center alignment with spacing

                bottomPanel.add(priceLabel);
                bottomPanel.add(decreaseButton);
                bottomPanel.add(quantityLabel);
                bottomPanel.add(increaseButton);
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