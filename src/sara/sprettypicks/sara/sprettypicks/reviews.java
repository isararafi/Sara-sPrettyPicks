package sara.sprettypicks;

import java.sql.*;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;



public class reviews extends javax.swing.JFrame {
 Database db = Database.getInstance();
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
        jLabel2 = new javax.swing.JLabel();
        satisfied = new javax.swing.JRadioButton();
        best = new javax.swing.JRadioButton();
        good = new javax.swing.JRadioButton();
        bad = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("REVIEWS!!!");

        reviewTextArea.setColumns(20);
        reviewTextArea.setRows(5);
        jScrollPane1.setViewportView(reviewTextArea);

        jButton1.setBackground(new java.awt.Color(255, 204, 0));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jButton1.setText("Submit");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listofproducts.setBackground(new java.awt.Color(153, 204, 255));
        listofproducts.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        listofproducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listofproductsActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Mongolian Baiti", 1, 18)); // NOI18N
        jLabel2.setText("How was your Experience?");

        satisfied.setBackground(new java.awt.Color(204, 255, 204));
        satisfied.setText("Satisfied");

        best.setBackground(new java.awt.Color(204, 255, 204));
        best.setText("Best");

        good.setBackground(new java.awt.Color(204, 255, 204));
        good.setText("Good");

        bad.setBackground(new java.awt.Color(204, 255, 204));
        bad.setText("Bad");

        jLabel3.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        jLabel3.setText("Write your Feed back in the Text Box below.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(97, 97, 97)
                                        .addComponent(satisfied, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(best, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(141, 141, 141)))
                                .addComponent(good, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bad, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listofproducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listofproducts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jLabel2)
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(satisfied)
                    .addComponent(best)
                    .addComponent(good)
                    .addComponent(bad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
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
    // Ensure radio buttons belong to the same ButtonGroup
    ButtonGroup experienceGroup = new ButtonGroup();
    experienceGroup.add(satisfied);
    experienceGroup.add(best);
    experienceGroup.add(good);
    experienceGroup.add(bad);

    // Get the selected product name from the combo box
    String selectedProduct = (String) listofproducts.getSelectedItem();
    System.out.println("Selected product before submission: " + selectedProduct);

    // Check if a product is selected
    if (selectedProduct == null || selectedProduct.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please select a product before submitting a review.");
        return; // Exit if no product is selected
    } else {
        System.out.println("Selected product: " + selectedProduct);
    }

    // Get the review text from the text area
    String reviewText = reviewTextArea.getText().trim();

    // Ensure that the review text is not empty
    if (reviewText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter your review before submitting.");
        return; // Exit if review text is empty
    }

    // Get the selected experience from radio buttons
    String selectedExperience = null;
    if (satisfied.isSelected()) {
        selectedExperience = "Satisfied";
    } else if (best.isSelected()) {
        selectedExperience = "Best";
    } else if (good.isSelected()) {
        selectedExperience = "Good";
    } else if (bad.isSelected()) {
        selectedExperience = "Bad";
    }

    // Ensure that an experience is selected
    if (selectedExperience == null) {
        JOptionPane.showMessageDialog(null, "Please select your experience.");
        return; // Exit if no experience is selected
    } else {
        System.out.println("Selected experience: " + selectedExperience);
    }

    // Retrieve the logged-in user's name
    String customerName = SessionManager.getLoggedInUserName();

    // Check if customerName is not null or empty
    if (customerName == null || customerName.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error: Customer name is not available.");
        return; // Exit if name is not available
    }

    // Create an instance of ComboBoxRetrieveProduct
    ComboBoxRetriveProduct productRetriever = new ComboBoxRetriveProduct();

    // Get the product ID from the selected product name
    int selectedProductId = productRetriever.getProductIdFromSelectedItem(selectedProduct);

    // Check if selectedProductId is valid (not -1)
    if (selectedProductId == -1) {
        JOptionPane.showMessageDialog(null, "Error: Unable to retrieve product ID.");
        return; // Exit if the product ID is invalid
    }

    // Create a Reviewclass object
    Reviewclass review = new Reviewclass(selectedProductId, customerName, reviewText, selectedExperience);

    // Submit the review
    submitReview(review);
}

// Method to submit the review
public void submitReview(Reviewclass review) {
    // SQL query to insert the review into the database
    String query = "INSERT INTO reviews (product_id, customer_username, review_text, experience) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pstmt = db.connect().prepareStatement(query)) {
        pstmt.setInt(1, review.getProductId()); // Set product ID as int
        pstmt.setString(2, review.getCustomerUsername()); // Set customer username
        pstmt.setString(3, review.getReviewText()); // Set review text
        pstmt.setString(4, review.getExperience()); // Set experience

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
    private javax.swing.JRadioButton bad;
    private javax.swing.JRadioButton best;
    private javax.swing.JRadioButton good;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listofproducts;
    private javax.swing.JTextArea reviewTextArea;
    private javax.swing.JRadioButton satisfied;
    // End of variables declaration//GEN-END:variables

}
