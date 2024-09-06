import java.rmi.Naming;

// Client to call the remote server method
public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Lookup the CalculatorService in the RMI registry
            CalculatorInterface calculator = (CalculatorInterface) Naming.lookup("rmi://localhost/CalculatorService");

            // Call the add method
            int result = calculator.add(5, 3);
            System.out.println("The sum is: " + result);
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}
