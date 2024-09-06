import java.io.*;
import java.net.*;

// Server class to handle multiple clients using multithreading
public class MultithreadedServer {
    public static void main(String[] args) {
        int port = 1234; // Port number for the server to listen on
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            // Continuously listen for client connections
            while (true) {
                // Accept a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Create a new thread for each connected client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

// Handler class for individual client connections
class ClientHandler extends Thread {
    private Socket clientSocket;

    // Constructor
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            // Create input and output streams for communication with the client
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            // Read the client's message
            String message = in.readLine();
            System.out.println("Received: " + message);

            // Reverse the message and send it back to the client
            String reversedMessage = new StringBuilder(message).reverse().toString();
            out.println(reversedMessage);
            System.out.println("Sent: " + reversedMessage);
        } catch (IOException e) {
            System.err.println("Client handler exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                // Close the client socket
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Failed to close client socket: " + e.getMessage());
            }
        }
    }
}
