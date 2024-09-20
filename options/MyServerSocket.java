import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MyServerSocket {
    public static void main(String[] args) {
        int port = 8080;
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Server is listening on port " + port);

            while (true) {
                // Accept incoming connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                // Set socket options on the client socket
                clientSocket.setKeepAlive(true);            // Enable SO_KEEPALIVE
                clientSocket.setSoLinger(true, 30);         // Enable SO_LINGER with a 30-second timeout
                clientSocket.setSendBufferSize(8192);       // Set send buffer size to 8192 bytes (SO_SNDBUF)
                clientSocket.setReceiveBufferSize(8192);    // Set receive buffer size to 8192 bytes (SO_RCVBUF)
                clientSocket.setTcpNoDelay(true);           // Disable Nagle's algorithm (TCP_NODELAY)

                // Close the client socket
                clientSocket.close();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
