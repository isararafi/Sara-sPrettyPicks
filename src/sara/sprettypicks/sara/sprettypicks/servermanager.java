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
            faqs.put(1, "What is 'Sara's Pretty Picks'?");
faqs.put(2, "How do I browse products on 'Sara's Pretty Picks'?");
faqs.put(3, "How can I track my order?");
faqs.put(4, "What are the payment currency available?");
faqs.put(5, "How do I return or exchange an item?");
faqs.put(6, "How do I create an account on 'Sara's Pretty Picks'?");
faqs.put(7, "What should I do if I forget my password?");
faqs.put(8, "Is it safe to shop on 'Sara's Pretty Picks'?");
faqs.put(9, "How do I apply a discount code?");
faqs.put(10, "Can I cancel my order after it's placed?");
faqs.put(11, "Do you offer gift cards?");
faqs.put(12, "How do I contact customer support?");

            // Answers for each FAQ
            HashMap<Integer, String> answers = new HashMap<>();
              // Updated answers for 'Sara's Pretty Picks' FAQ questions
answers.put(1, "Sara's Pretty Picks is an online shopping platform that offers a curated selection of trendy and cute items for women, including phone cases, skincare products, fairy lights, and hijabs.");
answers.put(2, "You can browse products by category, such as Phone Cases, Skincare, Fairy Lights, and Hijabs. Simply click on the desired category to see the available products.");
answers.put(3, "To track your order, log in to your account, go to 'Order History', and select the order you wish to track. You will find real-time tracking information there.");
answers.put(4, "We accept payments via dollar,rupee,euro currency. We ensure that your payment information is processed securely.");
answers.put(5, "If you'd like to return or exchange an item, visit the 'Returns & Exchanges' section on our website and follow the instructions for initiating a return request.");
answers.put(6, "To create an account, click on the 'Sign Up' button on the homepage. Fill in your details, including your name, email, and password, and click 'Register'. You'll receive a confirmation email.");
answers.put(7, "If you've forgotten your password, click on the 'Forgot Password' link on the login page. Enter your registered email address, and we'll send you a link to reset your password.");
answers.put(8, "Yes, shopping on Sara's Pretty Picks is safe! We use encrypted payment methods and secure transaction processes to protect your personal and financial data.");
answers.put(9, "To apply a discount code, enter the code during checkout in the 'Promo Code' box. The discount will be applied to your order total automatically.");
answers.put(10, "Once an order is placed, it may not be cancellable. However, you can reach out to our customer support team within 24 hours, and we'll try our best to assist you with your request.");
answers.put(11, "Yes, we offer gift cards! You can purchase them from our website and send them to loved ones as a perfect gift. Gift cards can be used for shopping on our platform.");
answers.put(12, "You can contact customer support by emailing us at support@saraspick.com or by using the live chat option available on our website during business hours.");

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
