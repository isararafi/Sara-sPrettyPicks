/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sara.sprettypicks;

import sara.sprettypicks.InsertImageWithPath;  // Adjust the package name as necessary

import com.sun.jdi.connect.spi.Connection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static sara.sprettypicks.InsertImageWithPath.displayAllProducts;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author sarar
 */
public class customerdashboardfacade extends javax.swing.JFrame {

    surprisecheckout checkoutProcess = new surprisecheckout();

    private String userName; // Store the user's email
    private int orderId; // Store the order ID
    private orders obj; // Create an instance of the Orders class

    public customerdashboardfacade(String userName) {
        UIManager.put("Button.background", Color.ORANGE);
        UIManager.put("Button.foreground", Color.BLACK); // Set text color to black
        UIManager.put("Button.focus", Color.ORANGE); // Optional: Customize the focus color if needed
        this.userName = userName; // Set the user's email
        this.obj = new orders(); // Instantiate the Orders class

        // Retrieve the order ID based on the user's email
        // this.orderId = obj.getOrderIdByUsername(userName);
        // Debugging output
        //System.out.println("Order ID for user " + userName + ": " + orderId);
        // Initialize components in the dashboard
        initComponents();
        setUserNameInTextField();
        
        // Start the server when the dashboard is created
        
//*****************************************************************************************
        // Inside the customer dashboard, you should have something like this:
// DefaultListModel to hold the notifications
        DefaultListModel<String> notificationListModel = new DefaultListModel<>();
        Database db = Database.getInstance();
// Fetch all notifications from the database
        List<String> notifications = db.getAllNotifications();

// Add the notifications to the DefaultListModel
        for (String notification : notifications) {
            notificationListModel.addElement(notification);
            //****************************************************************************
            // Load the image
// Load the original image
            ImageIcon originalIcon = new ImageIcon("C:\\initialshopping\\account.png");

// Resize the image to the desired dimensions
            Image resizedImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);

// Set the resized image as the button's icon
            accountinfo.setIcon(resizedIcon);

// Adjust button size to fit the resized icon with some padding for a cleaner look
            int iconWidth = resizedImage.getWidth(null);  // Get the width of the resized image
            int iconHeight = resizedImage.getHeight(null); // Get the height of the resized image
            accountinfo.setPreferredSize(new Dimension(iconWidth + 20, iconHeight + 30)); // Add more padding for clean spacing

// Align the icon and text within the button
            accountinfo.setHorizontalTextPosition(SwingConstants.CENTER); // Center the text horizontally
            accountinfo.setVerticalTextPosition(SwingConstants.BOTTOM);   // Place text below the icon

// Center the icon and text within the button
            accountinfo.setHorizontalAlignment(SwingConstants.CENTER);
            accountinfo.setVerticalAlignment(SwingConstants.CENTER);

// Optional: Set the button's border and background for improved appearance
            accountinfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds space around the button's contents
            accountinfo.setBackground(Color.WHITE); // Set a background color if necessary

        }

// Assuming `notificationList` is the JList that displays the notifications
        notificationList.setModel(notificationListModel);

    }
    
    // Method to start the server
    public  void startServer() {
        new Thread(() -> {
            try {
                // Initialize the server socket
                ServerSocket serverSocket = new ServerSocket(12345); // Use the same port as in the client
                System.out.println("Server started on port 12345...");

                // Accept incoming client connections
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    // Handle the client request in a separate thread (to avoid blocking the server)
                    new Thread(() -> handleClient(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error starting server: " + e.getMessage());
            }
        }).start(); // Start the server thread
    }

    // Method to handle client requests
    private void handleClient(Socket clientSocket) {
        try {
            // Create input and output streams to communicate with the client
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            // Read data from the client and send a response
            String message = input.readUTF();
            System.out.println("Received from client: " + message);
            output.writeUTF("Message received: " + message);

            // Close the connection
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error handling client: " + e.getMessage());
        }
    }
    //////////////////////////////////////////////////////////////

    private void setUserNameInTextField() {
        String username = SessionManager.getLoggedInUserName(); // Assuming you have a method to get the logged-in username.

        // Set the username in jTextField2
        //jTextField2.setText(username);
        customer.setText(username);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        browseproducts = new javax.swing.JButton();
        viewcart = new javax.swing.JButton();
        checkout = new javax.swing.JButton();
        createwishlist = new javax.swing.JButton();
        showwishlist = new javax.swing.JButton();
        findgift = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        reviews = new javax.swing.JButton();
        cancelorder = new javax.swing.JButton();
        deleteaccount = new javax.swing.JButton();
        vieworders = new javax.swing.JButton();
        frequentlyaskedquestions = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        notificationList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        customer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        accountinfo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1058, 2147483647));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jButton7.setText("Create Wish List");

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        jPanel2.setBackground(new java.awt.Color(255, 102, 255));

        jLabel4.setBackground(new java.awt.Color(255, 51, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("DASHBOARD");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        browseproducts.setBackground(new java.awt.Color(153, 204, 255));
        browseproducts.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        browseproducts.setText("Browse Products");
        browseproducts.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        browseproducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseproductsActionPerformed(evt);
            }
        });

        viewcart.setBackground(new java.awt.Color(153, 204, 255));
        viewcart.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        viewcart.setText("View Cart");
        viewcart.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        viewcart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewcartActionPerformed(evt);
            }
        });

        checkout.setBackground(new java.awt.Color(153, 204, 255));
        checkout.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        checkout.setText("CheckOut");
        checkout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutActionPerformed(evt);
            }
        });

        createwishlist.setBackground(new java.awt.Color(153, 204, 255));
        createwishlist.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        createwishlist.setText("Create WishLists");
        createwishlist.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        createwishlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createwishlistActionPerformed(evt);
            }
        });

        showwishlist.setBackground(new java.awt.Color(153, 204, 255));
        showwishlist.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        showwishlist.setText("Show Wishlists");
        showwishlist.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showwishlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showwishlistActionPerformed(evt);
            }
        });

        findgift.setBackground(new java.awt.Color(153, 204, 255));
        findgift.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        findgift.setText("Find Gift???");
        findgift.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        findgift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findgiftActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(153, 204, 255));
        jButton6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jButton6.setText("SURPRISE ME!!!");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        reviews.setBackground(new java.awt.Color(153, 204, 255));
        reviews.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        reviews.setText("Give Review");
        reviews.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        reviews.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewsActionPerformed(evt);
            }
        });

        cancelorder.setBackground(new java.awt.Color(153, 204, 255));
        cancelorder.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        cancelorder.setText("Cancel Order");
        cancelorder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cancelorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelorderActionPerformed(evt);
            }
        });

        deleteaccount.setBackground(new java.awt.Color(153, 204, 255));
        deleteaccount.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        deleteaccount.setText("Delete Account");
        deleteaccount.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteaccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteaccountActionPerformed(evt);
            }
        });

        vieworders.setBackground(new java.awt.Color(153, 204, 255));
        vieworders.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        vieworders.setText("view orders");
        vieworders.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        vieworders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewordersActionPerformed(evt);
            }
        });

        frequentlyaskedquestions.setBackground(new java.awt.Color(153, 204, 255));
        frequentlyaskedquestions.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        frequentlyaskedquestions.setText("Faqs");
        frequentlyaskedquestions.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        frequentlyaskedquestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frequentlyaskedquestionsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cancelorder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(deleteaccount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(vieworders, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(createwishlist, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(browseproducts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewcart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(showwishlist, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(findgift, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reviews, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(frequentlyaskedquestions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 51, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(browseproducts, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewcart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(frequentlyaskedquestions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createwishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showwishlist, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(findgift, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(reviews, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelorder, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteaccount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(vieworders, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
        );

        notificationList.setBackground(new java.awt.Color(255, 204, 204));
        notificationList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(notificationList);

        jScrollPane6.setViewportView(jScrollPane5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 983, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 589, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel2.setText("Sara's Pretty Picks");

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jButton1.setText("Sign Out");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        customer.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        customer.setForeground(new java.awt.Color(255, 0, 204));
        customer.setText("jLabel3");

        jLabel3.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 204));
        jLabel3.setText("WELCOME");

        accountinfo.setBackground(new java.awt.Color(0, 0, 0));
        accountinfo.setIcon(new javax.swing.ImageIcon("C:\\initialshopping\\account.png")); // NOI18N
        accountinfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountinfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(accountinfo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)
                                .addComponent(customer)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void viewcartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewcartActionPerformed


        showcartitems ob = new showcartitems();
        
        
        ob.cart(); // Assuming this method loads the cart items
        
    }//GEN-LAST:event_viewcartActionPerformed

    private void browseproductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseproductsActionPerformed
     // Create and set up the loading dialog
    JDialog loadingDialog = new JDialog();
    loadingDialog.setUndecorated(true); // Remove the default title bar

    // Set background panel with custom styling
    JPanel contentPanel = new JPanel();
   contentPanel.setBackground(new Color(70, 130, 180)); // Set background color (Steel Blue)

    contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add a black border

    // Add a label with custom font and color
    JLabel loadingLabel = new JLabel("Loading products, please wait...", SwingConstants.CENTER);
    loadingLabel.setForeground(Color.WHITE); // Set text color
    loadingLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set custom font

    // Load and scale the image
    ImageIcon originalIcon = new ImageIcon("C:\\Users\\sarar\\Desktop\\load.png");
    Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    loadingLabel.setIcon(scaledIcon);

    // Add the label to the content panel
    contentPanel.setLayout(new BorderLayout());
    contentPanel.add(loadingLabel, BorderLayout.CENTER);

    // Add the styled panel to the dialog
    loadingDialog.add(contentPanel);
    loadingDialog.setSize(350, 110); // Adjust size
    loadingDialog.setLocationRelativeTo(null); // Center it on the screen

    // Set it to be non-modal so it doesn't block other interactions
    loadingDialog.setModalityType(Dialog.ModalityType.MODELESS);

    // Create a SwingWorker to load products in the background
    SwingWorker<JFrame, Void> worker = new SwingWorker<JFrame, Void>() {
        @Override
        protected JFrame doInBackground() throws Exception {
            // Add an artificial delay to simulate loading time
            Thread.sleep(2000); // 2-second delay (you can adjust this as needed)

            // Create an instance of InsertImageWithPath
            InsertImageWithPath productsPage = new InsertImageWithPath();

            // Make sure to update the cart item count
            productsPage.showcartvalue(); // Updates the cart label with the latest item count

            // Call the method to display products (fetching product data)
            return productsPage.createSearchableProductDisplay();
        }

        @Override
        protected void done() {
            try {
                // Get the product display frame from the background thread
                JFrame browseProductsFrame = get();

                // Set the close operation for the Browse Products frame
                browseProductsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Make the frame visible on the EDT (main thread)
                SwingUtilities.invokeLater(() -> {
                    browseProductsFrame.setVisible(true);
                    browseProductsFrame.requestFocus(); // Ensure the frame gets focus
                });

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to load products: " + e.getMessage());
            } finally {
                // Close the loading dialog once the products are loaded
                SwingUtilities.invokeLater(() -> {
                    loadingDialog.dispose(); // Dispose the loading dialog after loading is complete
                });
            }
        }
    };

    // Show the loading dialog on EDT before executing the worker
    SwingUtilities.invokeLater(() -> {
        // Show the loading dialog
        loadingDialog.setVisible(true);
    });

    // Start the background worker to load products
    worker.execute();
    }//GEN-LAST:event_browseproductsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Close the current JFrame (Log Out)
        this.dispose();

        // Open the LoginForm JFrame
        loginformfacade loginForm = new loginformfacade(); // Assuming LoginForm is the name of your login JFrame class
        loginForm.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutActionPerformed

//surprisecheckout checkoutProcess = new surprisecheckout(); // Create a new instance to ensure no previous values
       
        checkoutProcess.checkout(); // Call checkout from EDT
  


    }//GEN-LAST:event_checkoutActionPerformed

    private void createwishlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createwishlistActionPerformed
        Database db = Database.getInstance();
        String wishlistName = JOptionPane.showInputDialog(this, "Enter the name for your wishlist:");

// Validate wishlist name
        if (wishlistName != null && !wishlistName.trim().isEmpty()) {
            String userEmail = SessionManager.getLoggedInUserEmail(); // Get logged-in user's email
            String userName = SessionManager.getLoggedInUserName();

            // Check if the wishlist can be created
            boolean wishlistCreated = db.createWishlist(userName, wishlistName);
            if (wishlistCreated) {
                JOptionPane.showMessageDialog(this, "Wishlist created successfully!");

                // Fetch products for selection
                List<String> productList = db.getAllProducts();

                // MultiSelectDialog logic integrated here
                JDialog dialog = new JDialog(this, "Select Products", true);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(400, 300); // Adjust size as needed

                // Panel to hold checkboxes in grid layout
                JPanel productPanel = new JPanel();
                int rows = (int) Math.ceil(productList.size() / 3.0); // Example: 3 columns
                productPanel.setLayout(new GridLayout(rows, 3, 10, 10)); // Rows, columns, hgap, vgap

                // Create and add checkboxes
                List<JCheckBox> checkBoxes = new ArrayList<>();
                for (String product : productList) {
                    JCheckBox checkBox = new JCheckBox(product);
                    checkBoxes.add(checkBox);
                    productPanel.add(checkBox);
                }

                // Add product panel to the dialog
                JScrollPane scrollPane = new JScrollPane(productPanel); // Add scroll if items overflow
                dialog.add(scrollPane, BorderLayout.CENTER);

                // Confirm and Cancel buttons
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                JButton confirmButton = new JButton("Confirm");
                confirmButton.setBackground(new Color(144, 238, 144)); // Light green
                // Change color of confirm button
                confirmButton.setForeground(Color.BLACK);

                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBackground(Color.RED); // Change color of cancel button
                cancelButton.setForeground(Color.BLACK);

                buttonPanel.add(confirmButton);
                buttonPanel.add(cancelButton);

                // Add button panel to dialog
                dialog.add(buttonPanel, BorderLayout.SOUTH);

                // Action listeners for buttons
                final boolean[] confirmed = {false};
                confirmButton.addActionListener(e -> {
                    confirmed[0] = true;
                    dialog.dispose();
                });
                cancelButton.addActionListener(e -> dialog.dispose());

                // Show the dialog
                dialog.setVisible(true);

                // Process selected products if confirmed
                if (confirmed[0]) {
                    List<String> selectedProducts = new ArrayList<>();
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            selectedProducts.add(checkBox.getText());
                        }
                    }

                    StringBuilder addedProducts = new StringBuilder(); // For accumulating added product names
                    StringBuilder errorProducts = new StringBuilder(); // For accumulating error messages

                    if (!selectedProducts.isEmpty()) {
                        for (String selectedProduct : selectedProducts) {
                            boolean productAdded = db.addProductToWishlist(userName, wishlistName, selectedProduct);
                            if (productAdded) {
                                addedProducts.append(selectedProduct).append(", "); // Add to successful list
                            } else {
                                errorProducts.append(selectedProduct).append(", "); // Add to error list
                            }
                        }

                        // Remove trailing comma and space
                        if (addedProducts.length() > 0) {
                            addedProducts.setLength(addedProducts.length() - 2);
                        }
                        if (errorProducts.length() > 0) {
                            errorProducts.setLength(errorProducts.length() - 2);
                        }

                        // Show a single message with all added products
                        String message = "Products added to wishlist: " + addedProducts.toString();
                        if (errorProducts.length() > 0) {
                            message += "\nError adding products: " + errorProducts.toString();
                        }
                        JOptionPane.showMessageDialog(this, message);
                    } else {
                        JOptionPane.showMessageDialog(this, "No products selected.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wishlist already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wishlist name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_createwishlistActionPerformed

    private void showwishlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showwishlistActionPerformed

        Database db = Database.getInstance();
        String userName = SessionManager.getLoggedInUserName(); // Get the logged-in user's email

        // Step 1: Fetch the wishlists for the user
        List<String> wishlistNames = db.getWishlistsByUser(userName);

        // Create a panel to hold the dropdown and action buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10)); // Organized in a grid layout with spacing

        // Step 2: Create a dropdown menu for wishlists
        JComboBox<String> wishlistDropdown = new JComboBox<>(wishlistNames.toArray(new String[0]));
        mainPanel.add(new JLabel("Select Wishlist:"));
        mainPanel.add(wishlistDropdown);

        // Step 3: Create buttons that operate on the selected wishlist
        JButton viewItemsButton = new JButton("View Items");
        JButton addItemButton = new JButton("Add Item");
        JButton deleteItemButton = new JButton("Delete Item");
        JButton deleteWishlistButton = new JButton("Delete Wishlist");

        // Set button background colors to light orange
        Color lightOrange = new Color(255, 200, 150);
        viewItemsButton.setBackground(lightOrange);
        addItemButton.setBackground(lightOrange);
        deleteItemButton.setBackground(lightOrange);
        deleteWishlistButton.setBackground(lightOrange);

        // Add buttons to the main panel
        mainPanel.add(viewItemsButton);
        mainPanel.add(addItemButton);
        mainPanel.add(deleteItemButton);
        mainPanel.add(deleteWishlistButton);

        // Step 4: Action to show items
        viewItemsButton.addActionListener(ev -> {
            String selectedWishlist = (String) wishlistDropdown.getSelectedItem();
            List<String> items = db.getItemsInWishlist(userName, selectedWishlist);

            // Display items in a JOptionPane
            StringBuilder itemList = new StringBuilder("Items in " + selectedWishlist + ":\n");
            if (items.isEmpty()) {
                itemList.append("No items found in this wishlist.");
            } else {
                for (String item : items) {
                    itemList.append(item).append("\n");
                }
            }
            JOptionPane.showMessageDialog(null, itemList.toString());
        });

        // Action to delete the wishlist
        deleteWishlistButton.addActionListener(ev -> {
            String selectedWishlist = (String) wishlistDropdown.getSelectedItem();
            int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this wishlist?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                boolean deleted = db.deleteWishlist(userName, selectedWishlist);
                if (deleted) {
                    JOptionPane.showMessageDialog(null, "Wishlist deleted successfully!");
                    // Refresh the dropdown
                    wishlistDropdown.setModel(new DefaultComboBoxModel<>(db.getWishlistsByUser(userName).toArray(new String[0])));
                } else {
                    JOptionPane.showMessageDialog(null, "Error deleting wishlist.");
                }
            }
        });

        // Action to add items to the wishlist
        addItemButton.addActionListener(ev -> {
            String selectedWishlist = (String) wishlistDropdown.getSelectedItem();
            List<String> productList = db.getAllProducts(); // Fetch all products for selection

            // Create a JPanel with GridLayout to display products in a grid
            JPanel gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(0, 4, 10, 10)); // 4 columns, dynamic rows, with spacing

            // Add each product as a button or label to the grid
            for (String product : productList) {
                JCheckBox productCheckbox = new JCheckBox(product); // Allow selection
                gridPanel.add(productCheckbox);
            }

            // Add the grid panel to a scroll pane for large lists
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            scrollPane.setPreferredSize(new Dimension(500, 300)); // Set preferred size

            // Display the panel in a dialog
            int option = JOptionPane.showConfirmDialog(
                    null, scrollPane, "Select Products to Add", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION) {
                // Collect selected products
                List<String> selectedProducts = new ArrayList<>();
                for (Component comp : gridPanel.getComponents()) {
                    if (comp instanceof JCheckBox checkbox && checkbox.isSelected()) {
                        selectedProducts.add(checkbox.getText());
                    }
                }

                if (!selectedProducts.isEmpty()) {
                    StringBuilder addedItems = new StringBuilder("Added to " + selectedWishlist + ":\n");
                    for (String selectedProduct : selectedProducts) {
                        boolean productAdded = db.addProductToWishlist(userName, selectedWishlist, selectedProduct);
                        if (productAdded) {
                            addedItems.append(selectedProduct).append("\n");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error adding product '" + selectedProduct + "' to wishlist.");
                        }
                    }
                    JOptionPane.showMessageDialog(null, addedItems.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No products selected.");
                }
            }
        });

        // Action to delete items from the wishlist
        deleteItemButton.addActionListener(ev -> {
            String selectedWishlist = (String) wishlistDropdown.getSelectedItem();
            List<String> itemsInWishlist = db.getItemsInWishlist(userName, selectedWishlist); // Fetch items in the selected wishlist

            // Create a JPanel with GridLayout to display items in a grid
            JPanel gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(0, 4, 3, 3)); // Smaller gap (3 pixels)
            // 4 columns, dynamic rows, with spacing

            // Add each item as a checkbox to the grid
            for (String item : itemsInWishlist) {
                JCheckBox itemCheckbox = new JCheckBox(item); // Allow selection
                gridPanel.add(itemCheckbox);
            }

            // Add the grid panel to a scroll pane for large lists
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            scrollPane.setPreferredSize(new Dimension(500, 300)); // Set preferred size

            // Display the panel in a dialog
            int option = JOptionPane.showConfirmDialog(
                    null, scrollPane, "Select Items to Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (option == JOptionPane.OK_OPTION) {
                // Collect selected items
                List<String> selectedItems = new ArrayList<>();
                for (Component comp : gridPanel.getComponents()) {
                    if (comp instanceof JCheckBox checkbox && checkbox.isSelected()) {
                        selectedItems.add(checkbox.getText());
                    }
                }

                if (!selectedItems.isEmpty()) {
                    StringBuilder removedItems = new StringBuilder("Removed from " + selectedWishlist + ":\n");
                    for (String selectedItem : selectedItems) {
                        boolean itemDeleted = db.deleteItemFromWishlist(userName, selectedWishlist, selectedItem);
                        if (itemDeleted) {
                            removedItems.append(selectedItem).append("\n");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error deleting item '" + selectedItem + "' from wishlist.");
                        }
                    }
                    JOptionPane.showMessageDialog(null, removedItems.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No items selected.");
                }
            }
        });

        // Show the panel in a dialog
        JOptionPane.showMessageDialog(null, mainPanel, "Manage Your Wishlists", JOptionPane.PLAIN_MESSAGE);

    }//GEN-LAST:event_showwishlistActionPerformed

    private void findgiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findgiftActionPerformed

        UIManager.put("OptionPane.okButtonText", "OK");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");
        UIManager.put("Button.background", new Color(255, 165, 0)); // Lighter orange
        // Default button background

        // Customize button colors
        UIManager.put("OptionPane.okButton", new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Empty action
            }
        }));
        UIManager.put("OptionPane.cancelButton", new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Empty action
            }
        }));
        String[] recipientOptions = {"Friend", "Father", "Sibling", "Mother", "Child"};
        String[] genderOptions = {"Male", "Female"};
        String[] ageGroupOptions = {"16-20", "21-25", "26-30"};

// Select recipient type
        String recipientType = (String) JOptionPane.showInputDialog(
                null,
                "Select the type of recipient:",
                "Find Gift",
                JOptionPane.QUESTION_MESSAGE,
                null,
                recipientOptions,
                recipientOptions[0]
        );

// Select gender
        String gender = (String) JOptionPane.showInputDialog(
                null,
                "Select the gender of the recipient:",
                "Find Gift",
                JOptionPane.QUESTION_MESSAGE,
                null,
                genderOptions,
                genderOptions[0]
        );

// Select age group
        String ageGroup = (String) JOptionPane.showInputDialog(
                null,
                "Select the age group of the recipient:",
                "Find Gift",
                JOptionPane.QUESTION_MESSAGE,
                null,
                ageGroupOptions,
                ageGroupOptions[0]
        );

// Validate the selection based on certain conditions
        if (recipientType != null && gender != null && ageGroup != null) {
            // Check for invalid combinations
            if ((recipientType.equals("Mother") && gender.equals("Male"))
                    || (recipientType.equals("Father") && gender.equals("Female"))
                    || (ageGroup.equals("16-20") && recipientType.equals("Child"))
                    || (ageGroup.equals("16-20") && (recipientType.equals("Father") || recipientType.equals("Mother")))) {

                JOptionPane.showMessageDialog(null, "Invalid selection: Please choose a valid combination of recipient type, gender, and age group.");
            } else {
                GiftFinder giftt = new GiftFinder();
                // Fetch gift options based on recipient type, gender, and age group
                List<Product> giftOptions = giftt.fetchGiftOptionsFromDatabase(recipientType, gender, ageGroup);

                if (giftOptions.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No gifts found for " + recipientType + " with gender " + gender + " and age group " + ageGroup);
                } else {
                    StringBuilder giftsMessage = new StringBuilder("Available gifts for " + recipientType + ":\n\n");
                    for (Product gift : giftOptions) {
                        giftsMessage.append(gift.getName())
                                .append(" - ")
                                .append(gift.getDescription())
                                .append(" ($")
                                .append(gift.getPrice())
                                .append(")\n");
                    }
                    JOptionPane.showMessageDialog(null, giftsMessage.toString());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selection canceled.");
        }

    }//GEN-LAST:event_findgiftActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // This would be in your event listener for the "Surprise Me" button
        Random rand = new Random();
        int discount = rand.nextInt(41) + 10; // Generates discount between 10% and 50%
        countdownclass obj = new countdownclass();

// Show the offer to the user
        JOptionPane.showMessageDialog(null, "Surprise! Get " + discount + "% off on your next purchase. Offer expires in 5 minutes!");

// Set the generated discount for the checkout process
//surprisecheckout ob=new surprisecheckout();
        checkoutProcess.setSurpriseDiscount(discount); // Set the discount for the checkout

// Start the countdown timer if applicable
        obj.startCountdown(discount);


    }//GEN-LAST:event_jButton6ActionPerformed

    private void reviewsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewsActionPerformed

        // TODO add your handling code here:
        reviews ob = new reviews();
        ob.setVisible(true);
    }//GEN-LAST:event_reviewsActionPerformed

    private void cancelorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelorderActionPerformed
       // Create an Orders object
orders ob = new orders();  // Class names should start with a capital letter

// Fetch the order ID using the username
int orderId = ob.getOrderIdByUsername(userName);

// Check if the order exists in the table
if (orderId == 0) {  // Assuming 0 indicates no order found
    JOptionPane.showMessageDialog(null, "No order found for the username: " + userName, "Error", JOptionPane.ERROR_MESSAGE);
    return; // Exit as there is no order to delete
}

// Fetch the order status
String orderStatus = ob.getOrderStatus(orderId);

// Check if orderStatus is null or empty (not yet placed)
if (orderStatus == null || orderStatus.isEmpty() || orderStatus.equals("Pending") || orderStatus.equals("Draft")) {
    JOptionPane.showMessageDialog(null, "The order has not been placed yet and cannot be canceled.", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Exit as the order is not eligible for cancellation
}

// Create an instance of ShowCartItems
showcartitems obj = new showcartitems();  // Class names should start with a capital letter

// Step 1: Fetch and display order details
String orderDetails = obj.getOrderDetails(orderId); // No need to pass connection if handled internally

// Display order details in a message box
JOptionPane.showMessageDialog(null, "Order Details: \n" + orderDetails, "Order Information", JOptionPane.INFORMATION_MESSAGE);

// Step 2: Check if the order can be canceled
if (orderStatus.equals("Shipped") || orderStatus.equals("Delivered")) {
    JOptionPane.showMessageDialog(null, "Order cannot be canceled as it has already been shipped or delivered.");
    return;
}

// Step 3: Confirm cancellation action
int confirmCancel = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this order?", "Cancel Order", JOptionPane.YES_NO_OPTION);
if (confirmCancel != JOptionPane.YES_OPTION) {
    return; // User chose not to cancel the order
}

// Step 4: Update the order status to "Cancelled"
boolean isCancelled = ob.updateOrderStatus(orderId, "Cancelled");
if (isCancelled) {
    JOptionPane.showMessageDialog(null, "Your order has been successfully canceled.");
} else {
    JOptionPane.showMessageDialog(null, "Error canceling order. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
}


        // Step 4: Optionally process refund if applicable
        // boolean refundSuccess = ob.processRefund(orderId);
        //  if (refundSuccess) {
//                System.out.println("Refund processed successfully.");
//                JOptionPane.showMessageDialog(null, "Refund processed successfully.");
//            } else {
//                System.out.println("Refund failed or not applicable.");
//                JOptionPane.showMessageDialog(null, "Refund processing failed.");
//            }
//       // } else {
//            JOptionPane.showMessageDialog(null, "Failed to cancel the order. Please try again.");
//        }

    }//GEN-LAST:event_cancelorderActionPerformed

    private void deleteaccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteaccountActionPerformed
        // Confirm if the user is sure about deleting the account
        int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete your account?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            // Perform account deletion
            Database db = Database.getInstance();

            // First, delete related data (cart, orders, order_items)
            boolean isRelatedDataDeleted = db.deleteUserRelatedData();

            if (isRelatedDataDeleted) {
                // Now delete the user account
                boolean isDeleted = db.deleteAccountFromDatabase();

                if (isDeleted) {
                    // Account deletion was successful
                    JOptionPane.showMessageDialog(this, "Your account has been successfully deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Close the current frame (customer dashboard)
                    this.dispose();

                    // Redirect the user to the login page
                    loginformfacade loginPage = new loginformfacade();  // Replace with your actual Login page class
                    loginPage.setVisible(true);
                } else {
                    // Account deletion failed
                    JOptionPane.showMessageDialog(this, "An error occurred while deleting your account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "An error occurred while deleting your related data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteaccountActionPerformed

    private void viewordersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewordersActionPerformed
        try (java.sql.Connection conn = Database.getInstance().connect()) {
            String username = SessionManager.getLoggedInUserName();

            // SQL query to fetch order details for the current user, grouping by order_id and product name.
            String sql = "SELECT o.order_id, p.name, oi.price, SUM(oi.quantity) AS total_quantity, o.order_date "
                    + "FROM orders o "
                    + "JOIN order_items oi ON o.order_id = oi.order_id "
                    + "JOIN products p ON oi.product_id = p.product_id "
                    + "WHERE o.user_name = ? AND o.order_status = 'completed' "
                    + "GROUP BY o.order_id, p.name, oi.price, o.order_date";

            PreparedStatement statusPs = conn.prepareStatement(sql);
            statusPs.setString(1, username);

            // Execute the query
            ResultSet rs = statusPs.executeQuery();

            // Prepare table model to display data
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Order ID");
            model.addColumn("Product Name");
            model.addColumn("Quantity");
            model.addColumn("Price");
            model.addColumn("Order Date");

            // Flag to check if we added a row
            boolean addedAtLeastOneRow = false;

            // Populate the table model with data from the result set
            while (rs.next()) {
                // Retrieve data
                int orderId = rs.getInt("order_id");
                String productName = rs.getString("name");
                int quantity = rs.getInt("total_quantity");  // Sum of quantities for each product
                double price = rs.getDouble("price");
                Timestamp orderDate = rs.getTimestamp("order_date");

                // Print data for debugging
                System.out.println("Order ID: " + orderId + ", Product: " + productName
                        + ", Quantity: " + quantity + ", Price: " + price + ", Order Date: " + orderDate);

                // Add row to table model
                model.addRow(new Object[]{
                    orderId, // Order ID
                    productName, // Product Name
                    quantity, // Total Quantity (summed)
                    price, // Price per Unit
                    orderDate // Order Date
                });

                addedAtLeastOneRow = true;
            }

            // Check if orders exist for the user
            if (!addedAtLeastOneRow) {
                JOptionPane.showMessageDialog(null, "No orders found.", "View Orders", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Create JTable and set the model
                JTable ordersTable = new JTable(model);

                // Adjust column widths
                TableColumnModel columnModel = ordersTable.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(100);  // Order ID
                columnModel.getColumn(1).setPreferredWidth(150); // Product Name
                columnModel.getColumn(2).setPreferredWidth(100); // Quantity
                columnModel.getColumn(3).setPreferredWidth(100); // Price
                columnModel.getColumn(4).setPreferredWidth(150); // Order Date

                // Display data in JTable
                JOptionPane.showMessageDialog(null, new JScrollPane(ordersTable), "Your Orders", JOptionPane.INFORMATION_MESSAGE);
            }

            // Close resources
            rs.close();
            statusPs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_viewordersActionPerformed

    private void accountinfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountinfoActionPerformed

        // Step 1: Retrieve the username from the session manager
        Database db = Database.getInstance();
        String oldusername = SessionManager.getLoggedInUserName();

        // Step 2: Fetch customer details from the database based on the username
        String[] customerInfo = db.getCustomerInfoByUsername(oldusername); // Fetch customer details as an array

        // Step 3: Create a dialog window to show and edit account info
        JDialog accountDialog = new JDialog();
        accountDialog.setTitle("View Account");
        accountDialog.setSize(400, 300); // Adjusted size for better view
        accountDialog.setLocationRelativeTo(null); // Center the dialog on the screen

        // Create a panel to hold the form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10)); // Added some gap between rows and columns

        // Username (editable now)
        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(customerInfo[0]);
        panel.add(usernameField);

        // First Name
        panel.add(new JLabel("First Name:"));
        JTextField firstNameField = new JTextField(customerInfo[1]); // first_name
        firstNameField.setPreferredSize(new Dimension(150, 25)); // Reduce width and padding
        panel.add(firstNameField);

        // Last Name
        panel.add(new JLabel("Last Name:"));
        JTextField lastNameField = new JTextField(customerInfo[2]); // last_name
        lastNameField.setPreferredSize(new Dimension(150, 25)); // Reduce width and padding
        panel.add(lastNameField);

        // Password
        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(customerInfo[3]); // password
        passwordField.setPreferredSize(new Dimension(150, 25)); // Reduce width and padding
        panel.add(passwordField);

        // Save button
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(Color.ORANGE); // Set Save button color to orange
        saveButton.setForeground(Color.BLACK); // Set text color to black for better contrast
        saveButton.setFocusPainted(false); // Remove focus border
        panel.add(new JLabel()); // Empty label to align the save button properly
        panel.add(saveButton);

      // Save button action listener
saveButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Step 4: Get the updated values from the text fields
        String currentUsername = oldusername;/* Get the current username (e.g., from a field or variable) */;
        String newUsername = usernameField.getText().trim(); // Username can be updated
        String newFirstName = firstNameField.getText().trim();
        String newLastName = lastNameField.getText().trim();
        String newPassword = new String(passwordField.getPassword()).trim();

        // Step 5: Validation before saving
        if (newUsername.isEmpty() || newFirstName.isEmpty() || newLastName.isEmpty() || newPassword.isEmpty()) {
            // Show error message if any field is empty
            JOptionPane.showMessageDialog(accountDialog, "All fields are required. Please fill them in.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Step 6: Check if the new username is unique (except for the current username)
        boolean updateSuccessful = false;

        // If the username is different, check uniqueness
        if (!newUsername.equals(currentUsername)) {
            updateSuccessful = db.updateCustomerInfo(currentUsername, newUsername, newFirstName, newLastName, newPassword);
        } else {
            // If the username is the same, directly update the account
            updateSuccessful = db.updateCustomerInfo(currentUsername, currentUsername, newFirstName, newLastName, newPassword);
        }

        // Step 7: Close the dialog
        if (updateSuccessful) {
            // Show success message
            JOptionPane.showMessageDialog(accountDialog, "Account updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Show error message
            JOptionPane.showMessageDialog(accountDialog, "Error updating account. Username may already exist or try again later.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Close the dialog regardless of success/failure
        accountDialog.dispose();
    }
});

// Add the panel to the dialog
accountDialog.add(panel);
accountDialog.setVisible(true);

    }//GEN-LAST:event_accountinfoActionPerformed

    private void frequentlyaskedquestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frequentlyaskedquestionsActionPerformed
   // Create an instance of FaqClient
    FaqClient ob = new FaqClient();
    
    // Set the FAQ frame to be visible
    ob.getFaqFrame().setVisible(true);
    
    // Set the FAQ frame close operation to DO_NOTHING_ON_CLOSE
    ob.getFaqFrame().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    
    // Add a window listener to handle window closing event
    ob.getFaqFrame().addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            // When the FAQ frame is closed, just hide it instead of closing it
            ob.getFaqFrame().setVisible(false);
        }
    });
    
    }//GEN-LAST:event_frequentlyaskedquestionsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(customerdashboardfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customerdashboardfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customerdashboardfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customerdashboardfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new customerdashboard().setVisible(true);
//            }
//        });
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new customerdashboardfacade().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountinfo;
    private javax.swing.JButton browseproducts;
    private javax.swing.JButton cancelorder;
    private javax.swing.JButton checkout;
    private javax.swing.JButton createwishlist;
    private javax.swing.JLabel customer;
    private javax.swing.JButton deleteaccount;
    private javax.swing.JButton findgift;
    private javax.swing.JButton frequentlyaskedquestions;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> notificationList;
    private javax.swing.JButton reviews;
    private javax.swing.JButton showwishlist;
    private javax.swing.JButton viewcart;
    private javax.swing.JButton vieworders;
    // End of variables declaration//GEN-END:variables
}
