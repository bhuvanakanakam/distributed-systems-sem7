import java.io.*;
import java.net.*;

// Client class to connect to the server and communicate
public class Client {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server hostname
        int port = 1234; // Server port

        try (
            // Connect to the server socket
            Socket socket = new Socket(hostname, port);

            // Create input and output streams for communication with the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            // Read a message from the user
            System.out.print("Enter a message: ");
            String message = userInput.readLine();

            // Send the message to the server
            out.println(message);

            // Read and print the server's response
            String response = in.readLine();
            System.out.println("Reversed message from server: " + response);
        } catch (UnknownHostException e) {
            System.err.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}
