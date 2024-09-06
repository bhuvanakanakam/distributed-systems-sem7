import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

// Make sure this matches your CalculatorInterface definition
public class CalculatorServer extends UnicastRemoteObject implements CalculatorInterface {

    // Constructor
    public CalculatorServer() throws RemoteException {
        super();
    }

    // Implement the add method
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            // Bind the server object to the RMI registry with the name "CalculatorService"
            Naming.rebind("CalculatorService", new CalculatorServer());
            System.out.println("Calculator Server is ready.");
        } catch (Exception e) {
            System.err.println("Server failed: " + e);
            e.printStackTrace();
        }
    }
}
