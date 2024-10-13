package sara.sprettypicks;

import java.sql.*;
import javax.swing.JOptionPane;



public class reviews extends javax.swing.JFrame {

    public reviews() {
        initComponents();

         ComboBoxRetriveProduct ob=new  ComboBoxRetriveProduct();


        // Call the method to populate the combo box with products from the database
        ob.populateProductComboBox(listofproducts);
        // Populate the combo box when the dashboard is created
//        ob.populateProductMap();

        // Add an ActionListener to handle selection changes
        listofproducts.addActionListener(e -> {
            String selectedProduct = (String) listofproducts.getSelectedItem();
            if (selectedProduct != null) {
                
            }
        });

        // Set the close operation to dispose the frame when closed
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reviewTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        listofproducts = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("REVIEWS!!!");

        reviewTextArea.setColumns(20);
        reviewTextArea.setRows(5);
        jScrollPane1.setViewportView(reviewTextArea);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listofproducts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listofproducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listofproductsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(listofproducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(listofproducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63)
                .addComponent(jButton1)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//// Get the selected product name from the combo box
// Get the selected product name from the combo box
    String selectedProduct = (String) listofproducts.getSelectedItem();
    System.out.println("Selected product before submission: " + selectedProduct);

    // Debugging: Check if a product is selected
    if (selectedProduct == null) {
        System.out.println("No product selected!");
        JOptionPane.showMessageDialog(null, "Please select a product before submitting a review.");
        return; // Exit if no product is selected
    } else {
        System.out.println("Selected product: " + selectedProduct);
    }

    // Get the review text from the text area
    String reviewText = reviewTextArea.getText();

    // Ensure that the review text is not empty
    if (reviewText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter your review before submitting.");
        return; // Exit if review text is empty
    }

    // Create an instance of SessionManager to get the logged-in user's email
    SessionManager sessionManager = new SessionManager();
    String customerEmail = sessionManager.getLoggedInUserEmail();

    // Check if customerEmail is not null or empty
    if (customerEmail == null || customerEmail.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error: Customer email is not available.");
        return; // Exit if email is not available
    }

    // Create an instance of ComboBoxRetrieveProduct
    ComboBoxRetriveProduct productRetriever = new ComboBoxRetriveProduct();

    // Get the product ID from the selected product name
    int selectedProductId = productRetriever.getProductIdFromSelectedItem(selectedProduct); // Get product ID as int

    // Check if selectedProductId is valid (not -1)
    if (selectedProductId == -1) {
        JOptionPane.showMessageDialog(null, "Error: Unable to retrieve product ID.");
        return; // Exit if the product ID is invalid
    }

    // Create a Reviewclass object
    Reviewclass review = new Reviewclass(selectedProductId, customerEmail, reviewText);

    // Submit the review
    submitReview(review);
}

// Method to submit the review
public void submitReview(Reviewclass review) {
    // SQL query to insert the review into the database
    String query = "INSERT INTO reviews (product_id, customer_email, review_text) VALUES (?, ?, ?)";
    Database db = new Database(); // Assuming you have a Database class that manages connections

    try (PreparedStatement pstmt = db.connect().prepareStatement(query)) {
        pstmt.setInt(1, review.getProductId()); // Set product ID as int
        pstmt.setString(2, review.getCustomerEmail()); // Set customer email
        pstmt.setString(3, review.getReviewText()); // Set review text

        // Execute the insert
        int rowsAffected = pstmt.executeUpdate();

        // Check if the insert was successful
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Review submitted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to submit review.");
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error submitting review: " + ex.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listofproductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listofproductsActionPerformed
        ComboBoxRetriveProduct ob = new  ComboBoxRetriveProduct();
       
    }//GEN-LAST:event_listofproductsActionPerformed

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
            java.util.logging.Logger.getLogger(reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reviews.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reviews().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listofproducts;
    private javax.swing.JTextArea reviewTextArea;
    // End of variables declaration//GEN-END:variables

}
