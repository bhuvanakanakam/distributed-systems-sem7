import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorServer extends UnicastRemoteObject implements CalculatorInterface {

    public CalculatorServer() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            // Start the RMI registry on a different port, e.g., 1100
            LocateRegistry.createRegistry(1100);
            
            // Bind the server object to the new port
            Naming.rebind("//localhost:1100/CalculatorService", new CalculatorServer());
            System.out.println("Calculator Server is ready.");
        } catch (Exception e) {
            System.err.println("Server failed: " + e);
            e.printStackTrace();
        }
    }
}
