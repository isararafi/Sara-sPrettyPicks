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
        insertProductImage(2, "blue silicon case", "phone cases", "a pretty case", 123.99, 12, "C:\\initialshopping\\phonecase1.jpg");
        insertProductImage(3, "shiny case", "phone cases", "a pretty case", 12.99, 12, "C:\\initialshopping\\phonecase2.jpg");
        insertProductImage(4, "wavy case", "phone cases", "a pretty case", 19.99, 22, "C:\\initialshopping\\phonecase3.jpg");
        insertProductImage(5, "purple case", "phone cases", "a pretty case", 19.99, 12, "C:\\initialshopping\\phonecase6.jpg");
        insertProductImage(6, "sea case", "phone cases", "a pretty case", 10.99, 12, "C:\\initialshopping\\phonecase9.jpg");
        insertProductImage(7, "purple cream", "skin care", "a soothing cream", 11.99, 34, "C:\\initialshopping\\skin0.jpg");
        insertProductImage(8, "honey and lemon cream", "skin care", "a soothing cream", 12.99, 34, "C:\\initialshopping\\skin1.jpg");
        insertProductImage(9, "neem wash", "skin care", "a soothing face wash", 13.99, 20, "C:\\initialshopping\\skin3.jpg");
        insertProductImage(10, "pink face wash", "skin care", "a soothing face wash", 17.00, 34, "C:\\initialshopping\\skin5.jpg");
        insertProductImage(11, "cetaphil wash", "skin care", "a soothing face wash", 11.99, 10, "C:\\initialshopping\\skin6.jpg");
        insertProductImage(12, "red serum", "skin care", "a nourishing serum", 23.99, 34, "C:\\initialshopping\\skin10.jpg");
        
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
    public static void createSearchableProductDisplay() {
        // Create a JFrame for the product display
        JFrame frame = new JFrame("Product Search");
        frame.setLayout(new BorderLayout());

        // Create a JPanel for the search bar
        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(15); // Input field for search
        JButton searchButton = new JButton("Search");

        // Add action listener to the search button
        searchButton.addActionListener(e -> {
            String category = searchField.getText().trim();
            // If the search field is empty, display all products
            if (category.isEmpty()) {
                displayAllProducts();
            } else {
                displayProducts(category);
            }
        });

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        frame.add(searchPanel, BorderLayout.NORTH);

        // Initialize the main panel for product display
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2)); // Adjust grid layout as needed (2 columns)
        frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER); // Add scrollable area for products

        frame.setSize(new Dimension(800, 600)); // Adjust as needed
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit

        displayAllProducts(); // Display all products by default
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
                    bottomPanel.setLayout(new FlowLayout()); // Horizontal layout
                    bottomPanel.add(priceLabel);
                    bottomPanel.add(quantityLabel);
                    bottomPanel.add(increaseButton);
                    bottomPanel.add(addToCartButton);

                    // Add bottom panel to the product panel
                    productPanel.add(bottomPanel, BorderLayout.SOUTH);

                    // Add product panel to the main panel
                    mainPanel.add(productPanel);
                }
            }

            mainPanel.revalidate(); // Refresh the main panel
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
public static void displayProducts(String category) {
    try {
        // Clear the main panel before displaying new products
        mainPanel.removeAll();

        // Check if the category is empty
        if (category.isEmpty()) {
            displayAllProducts(); // Call the method to display all products
            return; // Exit the method early
        }

        // Get the database connection
        Database db = Database.getInstance();
        Connection conn = db.connect();

        // Query to retrieve products by category from the database
        String query = "SELECT product_id, image, price, quantity FROM products WHERE category = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, category);

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
                bottomPanel.setLayout(new FlowLayout()); // Horizontal layout
                bottomPanel.add(priceLabel);
                bottomPanel.add(quantityLabel);
                bottomPanel.add(increaseButton);
                bottomPanel.add(addToCartButton);

                // Add bottom panel to the product panel
                productPanel.add(bottomPanel, BorderLayout.SOUTH);

                // Add product panel to the main panel
                mainPanel.add(productPanel);
            }
        }

        mainPanel.revalidate(); // Refresh the main panel
        mainPanel.repaint(); // Repaint to update UI

        // Close resources
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}

}