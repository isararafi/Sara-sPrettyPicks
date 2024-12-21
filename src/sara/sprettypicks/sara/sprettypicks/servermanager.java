package sara.sprettypicks;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class servermanager {
    // Method to start the server
    public void startServer() {
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
        try (
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        ) {
            // FAQ data
            HashMap<Integer, String> faqs = new HashMap<>();
            faqs.put(1, "What is this application?");
            faqs.put(2, "How do I use this feature?");
            faqs.put(3, "Who can I contact for support?");
            faqs.put(4, "What is the refund policy?");
            faqs.put(5, "How to reset my password?");

            // Answers for each FAQ
            HashMap<Integer, String> answers = new HashMap<>();
            answers.put(1, "This is a FAQ client-server application.");
            answers.put(2, "You can ask questions, and the server will provide answers.");
            answers.put(3, "You can contact support@example.com.");
            answers.put(4, "Please refer to the refund policy page on our website.");
            answers.put(5, "To reset your password, click on 'Forgot Password' on the login page.");

            boolean continueCommunication = true;
            while (continueCommunication) {
                String request = input.readUTF(); // Wait for client request
                System.out.println("Received request: " + request); // Debugging log

                if ("GET_FAQS".equals(request)) {
                    // Send FAQ list
                    StringBuilder faqList = new StringBuilder();
                    for (int i = 1; i <= faqs.size(); i++) {
                        faqList.append(i).append(". ").append(faqs.get(i)).append("\n");
                    }
                    output.writeUTF(faqList.toString()); // Send FAQs to the client
                    System.out.println("FAQ list sent to client.");

                } else if (request.startsWith("GET_ANSWER:")) {
                    // Extract question number from the request
                    String questionNumberStr = request.substring("GET_ANSWER:".length()).trim();
                    try {
                        int questionNumber = Integer.parseInt(questionNumberStr);

                        // Get the answer for the specified question number
                        String answer = answers.getOrDefault(questionNumber, "No answer found for this question.");
                        output.writeUTF(answer); // Send answer to client
                        System.out.println("Answer sent to client for question " + questionNumber);
                    } catch (NumberFormatException e) {
                        output.writeUTF("Invalid question number.");
                    }

                } else if ("EXIT".equals(request)) {
                    System.out.println("Client disconnected.");
                    continueCommunication = false; // End communication
                } else {
                    output.writeUTF("Unknown request. Please try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        servermanager server = new servermanager();
        server.startServer(); // Start the server to listen for connections
    }
}
