/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sara.sprettypicks;

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
        confirmpasswordtext = new javax.swing.JPasswordField();
        signup = new javax.swing.JButton();
        confirmpasswordlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

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

        passwordlabel.setText(".");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Confirm Password");

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernametext, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailtext)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordtext)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmpasswordtext)
                            .addComponent(usernamelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emaillabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(confirmpasswordlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 135, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(usernametext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usernamelabel)
                .addGap(28, 28, 28)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(emailtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(emaillabel)
                .addGap(26, 26, 26)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(passwordtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordlabel)
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(confirmpasswordtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmpasswordlabel)
                .addGap(45, 45, 45)
                .addComponent(signup)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
 
    // Clear previous messages
    Database db = Database.getInstance();
    usernamelabel.setText("<html>Username *:</html>"); // Indicate required fields with *
    emaillabel.setText("<html>Email *:</html>");
    passwordlabel.setText("<html>Password *:</html>");
    confirmpasswordlabel.setText("<html>Confirm Password *:</html>");

    // Retrieve input values
    String username = usernametext.getText().trim();
    String email = emailtext.getText().trim();
    String password = new String(passwordtext.getPassword());
    String confirmPassword = new String(confirmpasswordtext.getPassword());

    // Validation flags
    boolean validUsername = false;
    boolean validEmail = false;
    boolean validPassword = false;
    boolean validConfirmPassword = false;

    // Check for empty fields and display a message
    if (username.isEmpty()) {
        usernamelabel.setText("<html><b style='color:red;'>Username is a mandatory field. *</b></html>");
    } else {
        // Username validation
        String alphanumericRegex = "^[a-zA-Z0-9]+$";
        if (username.length() < 3 || username.length() > 255) {
            usernamelabel.setText("<html><b style='color:red;'>Username must be between 3 and 255 characters. *</b></html>");
        } else if (!username.matches(alphanumericRegex)) {
            usernamelabel.setText("<html><b style='color:red;'>Username must contain only alphabets and numbers, no special characters. *</b></html>");
        } else {
            usernamelabel.setText("<html><b style='color:green;'>Correct!</b></html>");
            validUsername = true;
        }
    }

    if (email.isEmpty()) {
        emaillabel.setText("<html><b style='color:red;'>Email is a mandatory field. *</b></html>");
    } else {
        // Email validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            emaillabel.setText("<html><b style='color:red;'>Incorrect format. *</b></html>");
        } else {
            emaillabel.setText("<html><b style='color:green;'>Correct!</b></html>");
            validEmail = true;
        }
    }

    if (password.isEmpty()) {
        passwordlabel.setText("<html><b style='color:red;'>Password is a mandatory field. *</b></html>");
    } else {
        // Password validation
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        if (!password.matches(passwordRegex)) {
            passwordlabel.setText("<html><b style='color:red;'>Password must be 8 characters long, 1 lowercase, 1 uppercase, no special character. *</b></html>");
        } else {
            passwordlabel.setText("<html><b style='color:green;'>Correct!</b></html>");
            validPassword = true;
        }
    }

    if (confirmPassword.isEmpty()) {
        confirmpasswordlabel.setText("<html><b style='color:red;'>Confirm Password is a mandatory field. *</b></html>");
    } else if (!password.equals(confirmPassword)) {
        confirmpasswordlabel.setText("<html><b style='color:red;'>Passwords do not match. Please enter again. *</b></html>");
    } else if (validPassword) {
        confirmpasswordlabel.setText("<html><b style='color:green;'>Correct!</b></html>");
        validConfirmPassword = true;
    }

    // Check if all fields are valid
    if (validUsername && validEmail && validPassword && validConfirmPassword) {
        // Check if the email already exists in the database
        if (db.checkEmailExists(email)) {
            JOptionPane.showMessageDialog(this, "User with the same email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean signupSuccess;

            // Insert into the database based on role
            if (role.equals("admin")) {
                signupSuccess = db.signupAdmin(email, password, username);
            } else {
                signupSuccess = db.signupCustomer(username, email, password);
            }

            if (signupSuccess) {
                JOptionPane.showMessageDialog(this, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                usernametext.setText("");
                emailtext.setText("");
                passwordtext.setText("");
                confirmpasswordtext.setText("");

                // Switch to the login frame
                loginformfacade loginFrame = new loginformfacade();
                loginFrame.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Signup failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    

    }//GEN-LAST:event_signupActionPerformed

    private void emailtextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtextActionPerformed

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
    private javax.swing.JLabel confirmpasswordlabel;
    private javax.swing.JPasswordField confirmpasswordtext;
    private javax.swing.JLabel emaillabel;
    private javax.swing.JTextField emailtext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel passwordlabel;
    private javax.swing.JPasswordField passwordtext;
    private javax.swing.JButton signup;
    private javax.swing.JLabel usernamelabel;
    private javax.swing.JTextField usernametext;
    // End of variables declaration//GEN-END:variables
}
