package sara.sprettypicks;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class FaqServer {
    private ServerSocket serverSocket;
    private static final int PORT = 12345;

    public FaqServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("FAQ Server is running on port " + PORT);
    }

    public void start() {
        try {
            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected!");

                // Handle the client in a separate thread
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server stopped: " + e.getMessage());
        }
    }

   private void handleClient(Socket clientSocket) {
    try (
        DataInputStream input = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
    ) {
        System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());

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
        try {
            FaqServer server = new FaqServer();
            server.start(); // Start the server to listen for connections
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
