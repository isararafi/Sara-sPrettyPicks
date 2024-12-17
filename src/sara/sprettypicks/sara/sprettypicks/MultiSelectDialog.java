/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

/**
 *
 * @author sarar
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MultiSelectDialog extends JDialog {
    private List<JCheckBox> checkboxes; // List to hold checkboxes
    private boolean confirmed; // To track if the selection is confirmed

    public MultiSelectDialog(Frame parent, List<String> products) {
        super(parent, "Select Products", true); // Create modal dialog
        checkboxes = new ArrayList<>(); // Initialize checkboxes list

        // Create a panel to hold the checkboxes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set vertical layout

        // Create a checkbox for each product
        for (String product : products) {
            JCheckBox checkbox = new JCheckBox(product); // Create checkbox with product name
            checkboxes.add(checkbox); // Add checkbox to the list
            panel.add(checkbox); // Add checkbox to the panel
        }

        // Button to confirm selection
        JButton confirmButton = new JButton("OK");
        confirmButton.addActionListener(e -> {
            confirmed = true; // Mark as confirmed
            setVisible(false); // Close the dialog
        });

        // Layout setup
        setLayout(new BorderLayout());
        add(new JScrollPane(panel), BorderLayout.CENTER); // Add scrollable panel
        add(confirmButton, BorderLayout.SOUTH); // Add confirm button
        pack(); // Pack the components
        setLocationRelativeTo(parent); // Center the dialog
    }

    // Method to get the selected products
    public List<String> getSelectedProducts() {
        List<String> selectedProducts = new ArrayList<>();
        for (JCheckBox checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                selectedProducts.add(checkbox.getText()); // Add selected checkbox text to the list
            }
        }
        return selectedProducts; // Return selected products
    }

    // Method to check if the selection was confirmed
    public boolean isConfirmed() {
        return confirmed; // Return confirmation status
    }
}
