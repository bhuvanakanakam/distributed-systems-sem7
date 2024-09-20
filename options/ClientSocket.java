import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8080;

        try {
            // Connect to the server
            Socket socket = new Socket(hostname, port);
            System.out.println("Connected to the server");

            // Get user input for name and roll number
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your roll number: ");
            String rollNumber = scanner.nextLine();

            // Send the name and roll number to the server
            String message = "Name: " + name + ", Roll Number: " + rollNumber;
            OutputStream output = socket.getOutputStream();
            output.write(message.getBytes());

            // Close the socket after use
            socket.close();
            System.out.println("Client socket closed");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
