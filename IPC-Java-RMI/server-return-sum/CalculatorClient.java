import java.rmi.Naming;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Connect to the updated port 1100
            CalculatorInterface calculator = (CalculatorInterface) Naming.lookup("rmi://localhost:1100/CalculatorService");
            
            // Call the add method
            int result = calculator.add(5, 3);
            System.out.println("The sum is: " + result);
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}
