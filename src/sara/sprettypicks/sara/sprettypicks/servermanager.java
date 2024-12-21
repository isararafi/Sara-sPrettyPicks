/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sara.sprettypicks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author sarar
 */
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
        try {
            // Create input and output streams to communicate with the client
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            // Read data from the client and send a response
            String message = input.readUTF();
            System.out.println("Received from client: " + message);
            output.writeUTF("Message received: " + message);

            // Close the connection
            input.close();
            output.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error handling client: " + e.getMessage());
        }
    }
    
}
