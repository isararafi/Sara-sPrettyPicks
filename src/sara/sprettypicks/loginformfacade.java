/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sara.sprettypicks;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;



/**
 *
 * @author sarar
 */
public class loginformfacade extends javax.swing.JFrame {

   
    public loginformfacade() {
         UIManager.put("Button.background", Color.ORANGE);
        UIManager.put("Button.foreground", Color.BLACK); // Set text color to black
        UIManager.put("Button.focus", Color.ORANGE);
        initComponents();
         ImageIcon eyeIcon = new ImageIcon("C:\\initialshopping\\eye.png"); // Load the image
Image scaledImage = eyeIcon.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH); // Scale to a smaller size (16x16)
ImageIcon smallIcon = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image
button.setIcon(smallIcon); // Set the smaller icon on the button
   

    }
     

  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernamefield = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        customerradio = new javax.swing.JRadioButton();
        adminradio = new javax.swing.JRadioButton();
        loginbutton = new javax.swing.JButton();
        signupbutton = new javax.swing.JButton();
        passwordfield = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        resetpassword = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        button = new javax.swing.JButton();

        jLabel2.setText("LOGIN PAGE");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Login Page");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("User Name");

        usernamefield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        usernamefield.setCaretColor(new java.awt.Color(102, 102, 255));
        usernamefield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernamefieldActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Password");

        customerradio.setBackground(new java.awt.Color(204, 204, 255));
        customerradio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        customerradio.setText("Customer");
        customerradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerradioActionPerformed(evt);
            }
        });

        adminradio.setBackground(new java.awt.Color(204, 204, 255));
        adminradio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        adminradio.setText("Admin");
        adminradio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminradioActionPerformed(evt);
            }
        });

        loginbutton.setBackground(new java.awt.Color(255, 153, 51));
        loginbutton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loginbutton.setText("LOGIN");
        loginbutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        loginbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbuttonActionPerformed(evt);
            }
        });

        signupbutton.setBackground(new java.awt.Color(51, 153, 255));
        signupbutton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        signupbutton.setForeground(new java.awt.Color(255, 255, 255));
        signupbutton.setText("New To Pretty picks?   Sign up");
        signupbutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        signupbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupbuttonActionPerformed(evt);
            }
        });

        passwordfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Forgot Password?");

        resetpassword.setBackground(new java.awt.Color(51, 153, 255));
        resetpassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        resetpassword.setForeground(new java.awt.Color(255, 255, 255));
        resetpassword.setText("Reset Password");
        resetpassword.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resetpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetpasswordActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\SARA University Data\\FIFTH SEMESTER\\SCD LAB\\download.png")); // NOI18N

        jLabel7.setFont(new java.awt.Font("Vivaldi", 1, 48)); // NOI18N
        jLabel7.setText("Sara's");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(223, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );

        button.setBackground(new java.awt.Color(255, 204, 51));
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resetpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(signupbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(passwordfield, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(customerradio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(adminradio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(loginbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(usernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addGap(31, 31, 31)
                        .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(jLabel3)))
                .addContainerGap(407, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3)
                        .addGap(46, 46, 46)
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28)
                        .addComponent(usernamefield, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordfield, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adminradio)
                            .addComponent(customerradio))
                        .addGap(35, 35, 35)
                        .addComponent(loginbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(signupbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resetpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customerradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerradioActionPerformed

    }//GEN-LAST:event_customerradioActionPerformed

    private void adminradioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminradioActionPerformed
  
    }//GEN-LAST:event_adminradioActionPerformed

    private void signupbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupbuttonActionPerformed
// Inside your login frame's signup button action

// Check if both radio buttons are selected
if (customerradio.isSelected() && adminradio.isSelected()) {
    // Show a message dialog if both radio buttons are selected
    JOptionPane.showMessageDialog(this, "Please select only one category (Customer or Admin).", "Selection Error", JOptionPane.WARNING_MESSAGE);
    return; // Exit the method if both radio buttons are selected
}

// Check if no category is selected
if (!customerradio.isSelected() && !adminradio.isSelected()) {
    // Show a message dialog if no category is selected
    JOptionPane.showMessageDialog(this, "Please select a category (Customer or Admin).", "Selection Required", JOptionPane.WARNING_MESSAGE);
    return; // Exit the method if no category is selected
}

// Define a variable to hold the role
String role;

// Check which radio button is selected
if (adminradio.isSelected()) {
    role = "admin";
} else {
    role = "customer";
}

// Proceed to open the signup form if a valid category is selected
Signupformfacade signup = new Signupformfacade(role);
signup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure only the signup frame closes when done
signup.setVisible(true);
this.dispose();


    }//GEN-LAST:event_signupbuttonActionPerformed

    private void loginbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbuttonActionPerformed
      // Get username and password from fields
String username = usernamefield.getText();
String password = new String(passwordfield.getPassword());

// Check if username and password are empty
if (username.isEmpty() || password.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please enter both username and password.");
    return;
}

// Check if both radio buttons are selected
if (customerradio.isSelected() && adminradio.isSelected()) {
    JOptionPane.showMessageDialog(this, "Please select only one user type.");
    return;
}

// Check if no radio button is selected
if (!customerradio.isSelected() && !adminradio.isSelected()) {
    JOptionPane.showMessageDialog(this, "Please select a user type.");
    return;
}

Database db = Database.getInstance(); // Singleton pattern -- reference variable

// Check which radio button is selected
if (customerradio.isSelected()) {
    // Check login credentials for a customer
    boolean isLoginSuccessful = db.checkCustomerLogin(username, password);
    if (isLoginSuccessful) {
        JOptionPane.showMessageDialog(null, "Successful login");

        // Retrieve email based on username and store it in SessionManager
        String email = null;
        try {
            email = db.getEmailByUsername(username); // Assuming `getEmailByUsername` is a method in Database to fetch email by username
        } catch (SQLException ex) {
            Logger.getLogger(loginformfacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (email != null) {
            SessionManager.setLoggedInUserEmail(email); // Store email in session
            SessionManager.setLoggedInUserName(username); // Store username in session

            // Navigate to the customer dashboard
            customerdashboardfacade customerDashboard = new customerdashboardfacade(username);

            // Ensure no unnecessary messages in customerDashboard initialization
            customerDashboard.setVisible(true);
            this.dispose(); // Close the login frame
        } else {
            JOptionPane.showMessageDialog(null, "Error retrieving email. Please contact support.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Username or password is incorrect. Please try again.");
    }
} else if (adminradio.isSelected()) {
    // Check login credentials for an admin
    boolean isLoginSuccessful = db.checkAdminLogin(username, password);
    if (isLoginSuccessful) {
        JOptionPane.showMessageDialog(null, "Successful login");

        // Set the logged-in admin's username in the session
        SessionManager.setLoggedInUserName(username);

        // Open the admin dashboard
        Admindashboardfacade adminDashboard = new Admindashboardfacade();
        adminDashboard.setVisible(true);

        // Close the login frame
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(null, "Username or password is incorrect. Please try again.");
    }
}

    }//GEN-LAST:event_loginbuttonActionPerformed

    private void resetpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetpasswordActionPerformed
        // Get the user’s email or username (assuming you have a JTextField for this)
 Database db = Database.getInstance();
 
String username = JOptionPane.showInputDialog("Enter your username:");

// If the user cancels or doesn't provide an input, return early
if (username == null || username.trim().isEmpty()) {
    JOptionPane.showMessageDialog(this, "Username is required", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Verify if the user exists (optional, depends on your implementation)
boolean userExists = db.checkIfUserExists(username);
if (!userExists) {
    JOptionPane.showMessageDialog(this, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Prompt the user to enter a new password
String newPassword = JOptionPane.showInputDialog("Enter your new password:");

try {
    // Check if the user cancels or doesn't provide a new password
    if (newPassword == null || newPassword.trim().isEmpty()) {
        /*IllegalArgumentException is a built-in exception class
        in Java that indicates that a method has
        been passed an illegal or inappropriate argument.*/
        throw new IllegalArgumentException("Password cannot be empty");
    }
String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W])[A-Za-z\\d\\W]{8,}$";

    // Update the password in the database
    boolean isPasswordReset = db.resetPassword(username, newPassword);

    // Notify the user if the password reset was successful
    if (isPasswordReset) {
        JOptionPane.showMessageDialog(this, "Password has been reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Failed to reset the password", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (IllegalArgumentException e) {
    // Show error message for invalid password
    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception e) {
    // Handle any other exceptions
    JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_resetpasswordActionPerformed

    private void usernamefieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernamefieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernamefieldActionPerformed
private boolean isPasswordVisible = false; 
    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
      // TODO add your handling code here:
if (isPasswordVisible) {
    // Hide the password with bold round dots
    passwordfield.setEchoChar('\u2022'); // Unicode character for a bold dot (●)
    isPasswordVisible = false;
} else {
    // Show the password as plain text
    passwordfield.setEchoChar((char) 0); // Display characters as plain text
    isPasswordVisible = true;
}

    }//GEN-LAST:event_buttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loginformfacade().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton adminradio;
    private javax.swing.JButton button;
    private javax.swing.JRadioButton customerradio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginbutton;
    private javax.swing.JPasswordField passwordfield;
    private javax.swing.JButton resetpassword;
    private javax.swing.JButton signupbutton;
    private javax.swing.JTextField usernamefield;
    // End of variables declaration//GEN-END:variables
}
