import java.rmi.Remote;
import java.rmi.RemoteException;

// Define the remote interface
public interface CalculatorInterface extends Remote {
    int add(int a, int b) throws RemoteException;
}
