package sara.sprettypicks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class FaqClient {

    private JFrame faqFrame;
    private JTextArea faqDisplay;
    private JTextArea answerDisplay; // Declare the answer display JTextArea
    private JButton getQuestionsButton;
    private JButton getAnswerButton;
    private JTextField questionNumberField;

    public FaqClient() {
       faqFrame = new JFrame("FAQ Client");
faqFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
faqFrame.setSize(600, 600); // Increase frame size to accommodate both FAQs and answers

JPanel panel = new JPanel();
panel.setLayout(new BorderLayout());
panel.setBackground(new Color(230, 230, 250)); // Light purple background for the main panel

// Display area for FAQs
faqDisplay = new JTextArea();
faqDisplay.setEditable(false); // FAQs are not editable
faqDisplay.setBackground(new Color(230, 200, 255)); // Lighter purple background
faqDisplay.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font size and style for FAQs
faqDisplay.setForeground(Color.BLACK); // Set the text color to black for visibility

// JScrollPane for FAQs to handle vertical scrolling
JScrollPane faqScroll = new JScrollPane(faqDisplay);
faqScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for scroll

// Create a panel for answers
answerDisplay = new JTextArea();
answerDisplay.setEditable(true); // Answers should be editable
answerDisplay.setBackground(new Color(144, 238, 144)); // Light green background for answers
answerDisplay.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for answers

// Enable line wrapping and word wrapping
answerDisplay.setLineWrap(true);
answerDisplay.setWrapStyleWord(true);

// Set preferred size for answer display to avoid horizontal scroll
answerDisplay.setPreferredSize(new Dimension(600, 200)); // Adjust the height for the answer area
answerDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border for the answer text area

// Panel for FAQ and answers (combining them in a split pane)
JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, faqScroll, new JScrollPane(answerDisplay));
splitPane.setDividerLocation(300); // Set divider location to allow ample space for both FAQs and answers

// Panel for buttons and input field
JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new FlowLayout()); // Using FlowLayout for button alignment
buttonPanel.setBackground(new Color(230, 230, 250)); // Light purple background for the button panel

getQuestionsButton = new JButton("Get Questions");
getQuestionsButton.setBackground(new Color(255, 165, 0)); // Orange background for the button
getQuestionsButton.setFont(new Font("Arial", Font.BOLD, 14));

getAnswerButton = new JButton("Get Answer");
getAnswerButton.setBackground(new Color(255, 165, 0)); // Orange background for the button
getAnswerButton.setFont(new Font("Arial", Font.BOLD, 14));

questionNumberField = new JTextField(5);
questionNumberField.setFont(new Font("Arial", Font.PLAIN, 14));

buttonPanel.add(getQuestionsButton);
buttonPanel.add(new JLabel("Enter Question Number:"));
buttonPanel.add(questionNumberField);
buttonPanel.add(getAnswerButton);

// Place the buttons and input field at the top
panel.add(buttonPanel, BorderLayout.NORTH);
panel.add(splitPane, BorderLayout.CENTER); // Place the split pane with FAQs and answers in the center

faqFrame.add(panel);
faqFrame.setVisible(true);

        // Set up the "Get Questions" button listener
        getQuestionsButton.addActionListener(e -> fetchQuestions());

        // Set up the "Get Answer" button listener
        getAnswerButton.addActionListener(e -> fetchAnswer());
    }

    public JFrame getFaqFrame() {
        return faqFrame;
    }

    // Fetches the list of questions from the server
    private void fetchQuestions() {
        new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 12345)) {
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());

                // Send request to fetch FAQs
                output.writeUTF("GET_FAQS");

                // Read the FAQ list from the server
                String faqList = input.readUTF();
                SwingUtilities.invokeLater(() -> faqDisplay.setText(faqList));
            } catch (IOException ex) {
                SwingUtilities.invokeLater(() -> faqDisplay.setText("Error fetching FAQs. Try again."));
                ex.printStackTrace();
            }
        }).start();
    }

    // Fetches the answer for a specific question number from the server
    private void fetchAnswer() {
        new Thread(() -> {
            try {
                // Connect to the server
                Socket socket = new Socket("127.0.0.1", 12345); // Ensure this is the correct server address and port
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());

                // Get the question number from the input field
                String questionNumber = questionNumberField.getText().trim();

                // Send the question number to the server
                output.writeUTF("GET_ANSWER:" + questionNumber);

                // Receive the answer from the server
                String answer = input.readUTF();

                // Update the answerDisplay JTextArea
                SwingUtilities.invokeLater(() -> answerDisplay.setText(answer));

                // Close the streams and socket
                input.close();
                output.close();
                socket.close();
            } catch (IOException ex) {
                // Log and handle errors
                System.out.println("Error fetching answer: " + ex.getMessage());
                ex.printStackTrace();

                // Show an error message in the answerDisplay JTextArea
                SwingUtilities.invokeLater(() -> answerDisplay.setText("Unable to fetch the answer. Please try again."));
            }
        }).start();
    }

    public static void main(String[] args) {
        new FaqClient();
    }
}
