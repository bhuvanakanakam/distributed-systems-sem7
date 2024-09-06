import java.rmi.Remote;
import java.rmi.RemoteException;

// Define the remote interface
public interface CalculatorInterface extends Remote {
    // Method to add two numbers remotely
    int add(int a, int b) throws RemoteException;
}
