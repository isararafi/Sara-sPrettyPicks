/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sara.sprettypicks;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sarar
 */
    /* extends keyword indicates that Signupformfacade 
      is a subclass of the JFrame class. This is an example 
      of inheritance in Java. By extending JFrame, Signupformfacade
      inherits all the properties and behaviors (methods)
       of the JFrame class, which is the foundational class for creating a window in Swing.*/
public class Signupformfacade extends javax.swing.JFrame {
    

    private String role; // Variable to store the role (admin/customer)

    // Constructor to accept role
    public Signupformfacade(String role) {
        this.role = role; // Store the passed role (admin/customer)
        initComponents();
          ImageIcon eyeIcon = new ImageIcon("C:\\initialshopping\\eye.png"); // Load the image
Image scaledImage = eyeIcon.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH); // Scale to a smaller size (16x16)
ImageIcon smallIcon = new ImageIcon(scaledImage); // Create a new ImageIcon with the scaled image
button1.setIcon(smallIcon); // Set the smaller icon on the button
button2.setIcon(smallIcon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernametext = new javax.swing.JTextField();
        usernamelabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        emailtext = new javax.swing.JTextField();
        emaillabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        passwordtext = new javax.swing.JPasswordField();
        passwordlabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        confirmpassword = new javax.swing.JPasswordField();
        signup = new javax.swing.JButton();
        confirmpasswordlabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        firstnametext = new javax.swing.JTextField();
        firstnamelabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lastnametext = new javax.swing.JTextField();
        lastnamelabel = new javax.swing.JLabel();
        button1 = new javax.swing.JButton();
        button2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Sign Up Page");

        jLabel3.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("Register with us for future convenience");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Username");

        usernamelabel.setText(".");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Email");

        emailtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailtextActionPerformed(evt);
            }
        });

        emaillabel.setText(".");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Password");

        passwordtext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordtextActionPerformed(evt);
            }
        });

        passwordlabel.setText(".");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Confirm Password");

        confirmpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmpasswordActionPerformed(evt);
            }
        });

        signup.setBackground(new java.awt.Color(255, 153, 0));
        signup.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        signup.setForeground(new java.awt.Color(255, 255, 255));
        signup.setText("Sign Up");
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });

        confirmpasswordlabel.setText(".");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("First Name");

        firstnamelabel.setText(".");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Last Name");

        lastnamelabel.setText(".");

        button1.setBackground(new java.awt.Color(255, 204, 102));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(255, 204, 102));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel9.setFont(new java.awt.Font("Vivaldi", 3, 36)); // NOI18N
        jLabel9.setText("To Sara's");

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\SARA University Data\\FIFTH SEMESTER\\SCD LAB\\download.png")); // NOI18N
        jLabel10.setText("jLabel10");

        jLabel12.setFont(new java.awt.Font("Viner Hand ITC", 3, 24)); // NOI18N
        jLabel12.setText("WELCOME");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel12)
                .addGap(38, 38, 38)
                .addComponent(jLabel9)
                .addGap(27, 27, 27)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lastnamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernametext, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstnamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(emailtext, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(passwordtext, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(emaillabel, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(passwordlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(confirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(60, 60, 60)
                                        .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(confirmpasswordlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(76, 76, 76))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(usernametext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addComponent(usernamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastnamelabel)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastnametext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(firstnamelabel)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(emailtext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(emaillabel)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(passwordtext, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))
                        .addGap(18, 18, 18)
                        .addComponent(passwordlabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(confirmpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addGap(18, 18, 18)
                        .addComponent(confirmpasswordlabel)
                        .addGap(32, 32, 32)
                        .addComponent(signup)
                        .addGap(40, 40, 40))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
 // Database instance
Database db = Database.getInstance();

// Clear previous messages
firstnamelabel.setText("<html>First Name *:</html>");
lastnamelabel.setText("<html>Last Name *:</html>");
usernamelabel.setText("<html>Username *:</html>");
emaillabel.setText("<html>Email *:</html>");
passwordlabel.setText("<html>Password *:</html>");
confirmpasswordlabel.setText("<html>Confirm Password *:</html>");

// Retrieve input values
String firstName = firstnametext.getText().trim();
String lastName = lastnametext.getText().trim();
String username = usernametext.getText().trim();
String email = emailtext.getText().trim();
String password = new String(passwordtext.getPassword());
String confirmPassword = new String(confirmpassword.getPassword());

// Validation flags
boolean validFirstName = false;
boolean validLastName = false;
boolean validUsername = false;
boolean validEmail = false;
boolean validPassword = false;
boolean validConfirmPassword = false;

// Validation checks for fields (firstName, lastName, username, email, password, confirmPassword)

// Check first name
if (firstName.isEmpty() || !firstName.matches("[a-zA-Z]+")) {
    firstnamelabel.setText("<html><b style='color:red;'>First Name must contain only alphabets. *</b></html>");
} else {
    firstnamelabel.setText("<html><b style='color:green;'>Correct!</b></html>");
    validFirstName = true;
}

// Check last name
if (lastName.isEmpty() || !lastName.matches("[a-zA-Z]+")) {
    lastnamelabel.setText("<html><b style='color:red;'>Last Name must contain only alphabets. *</b></html>");
} else {
    lastnamelabel.setText("<html><b style='color:green;'>Correct!</b></html>");
    validLastName = true;
}

// Check username uniqueness
if (username.isEmpty()) {
    usernamelabel.setText("<html><b style='color:red;'>Username is a mandatory field. *</b></html>");
} else {
    String alphanumericRegex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9_]+$";
    if (username.length() < 3 || username.length() > 255) {
        usernamelabel.setText("<html><b style='color:red;'>Username must be between 3 and 255 characters. *</b></html>");
    } else if (!username.matches(alphanumericRegex)) {
        usernamelabel.setText("<html><b style='color:red;'>Username must contain both letters and numbers. Only underscores are allowed as special characters. *</b></html>");
    } else if (db.checkUsernameExists(username)) {  // Check if username exists
        usernamelabel.setText("<html><b style='color:red;'>Username already exists. Please enter another username. *</b></html>");
    } else {
        usernamelabel.setText("<html><b style='color:green;'>Correct!</b></html>");
        validUsername = true;
    }
}

// Check email uniqueness
if (email.isEmpty()) {
    emaillabel.setText("<html><b style='color:red;'>Email is a mandatory field. *</b></html>");
} else {
    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    if (!email.matches(emailRegex)) {
        emaillabel.setText("<html><b style='color:red;'>Incorrect email format. *</b></html>");
    } else if (db.checkEmailExists2(email)) {  // Check if email exists
        emaillabel.setText("<html><b style='color:red;'>Email already exists. Please enter another email. *</b></html>");
    } else {
        emaillabel.setText("<html><b style='color:green;'>Correct!</b></html>");
        validEmail = true;
    }
}

// Check password complexity
// Validate password
if (password.isEmpty()) {
    passwordlabel.setText("<html><b style='color:red;'>Password is a mandatory field. *</b></html>");
    validPassword = false;
} else {
    // Update regex to include underscore (_) as a valid special character
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_])[A-Za-z\\d@$!%*?&_]{8,}$";
    if (!password.matches(passwordRegex)) {
        passwordlabel.setText("<html><b style='color:red;'>Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character (including _). Minimum length: 8 characters. *</b></html>");
        validPassword = false;
    } else {
        passwordlabel.setText("<html><b style='color:green;'>Correct!</b></html>");
        validPassword = true;
    }
}

// Validate confirm password
if (confirmPassword.isEmpty()) {
    confirmpasswordlabel.setText("<html><b style='color:red;'>Confirm password is a mandatory field. *</b></html>");
    validConfirmPassword = false;
} else if (!validPassword) {
    // If the original password does not meet syntax rules, block confirm password validation
    confirmpasswordlabel.setText("<html><b style='color:red;'>Fix the password requirements first. *</b></html>");
    validConfirmPassword = false;
} else if (!confirmPassword.equals(password)) {
    confirmpasswordlabel.setText("<html><b style='color:red;'>Passwords do not match. *</b></html>");
    validConfirmPassword = false;
} else {
    confirmpasswordlabel.setText("<html><b style='color:green;'>Correct!</b></html>");
    validConfirmPassword = true;
}


// Check if all fields are valid
if (validFirstName && validLastName && validUsername && validEmail && validPassword && validConfirmPassword) {
    // Proceed with user signup
    boolean signupSuccess;
    if (role.equals("admin")) {
        signupSuccess = db.signupAdmin(email, password, firstName, username);
    } else {
        signupSuccess = db.signupCustomer(firstName, lastName, username, email, password);  // Assuming first name and last name are required for customers
    }

    if (signupSuccess) {
        JOptionPane.showMessageDialog(this, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        // Clear fields after successful signup
        firstnametext.setText(""); 
        lastnametext.setText(""); 
        usernametext.setText(""); 
        emailtext.setText(""); 
        passwordtext.setText(""); 
        confirmpassword.setText(""); 

     loginformfacade ob=new loginformfacade();
     ob.setVisible(true);
this.dispose();
    } else {
        JOptionPane.showMessageDialog(this, "Signup failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
    }
} else {
    JOptionPane.showMessageDialog(this, "Please fill in all required fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
}

    }//GEN-LAST:event_signupActionPerformed

    private void emailtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtextActionPerformed

    private void passwordtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordtextActionPerformed

    private void confirmpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmpasswordActionPerformed
private boolean isPasswordVisible = false;
    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
           // TODO add your handling code here:
if (isPasswordVisible) {
    // Hide the password with bold round dots
    confirmpassword.setEchoChar('\u2022'); // Unicode character for a bold dot (●)
    isPasswordVisible = false;
} else {
    // Show the password as plain text
    confirmpassword.setEchoChar((char) 0); // Display characters as plain text
    isPasswordVisible = true;
}
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if (isPasswordVisible) {
            // Hide the password with bold round dots
            passwordtext.setEchoChar('\u2022'); // Unicode character for a bold dot (●)
            isPasswordVisible = false;
        } else {
            // Show the password as plain text
            passwordtext.setEchoChar((char) 0); // Display characters as plain text
            isPasswordVisible = true;
        }
    }//GEN-LAST:event_button1ActionPerformed

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
            java.util.logging.Logger.getLogger(Signupformfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signupformfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signupformfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signupformfacade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                String role = "admin"; // or "admin", depending on your logic
                new Signupformfacade(role).setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JPasswordField confirmpassword;
    private javax.swing.JLabel confirmpasswordlabel;
    private javax.swing.JLabel emaillabel;
    private javax.swing.JTextField emailtext;
    private javax.swing.JLabel firstnamelabel;
    private javax.swing.JTextField firstnametext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lastnamelabel;
    private javax.swing.JTextField lastnametext;
    private javax.swing.JLabel passwordlabel;
    private javax.swing.JPasswordField passwordtext;
    private javax.swing.JButton signup;
    private javax.swing.JLabel usernamelabel;
    private javax.swing.JTextField usernametext;
    // End of variables declaration//GEN-END:variables
}
