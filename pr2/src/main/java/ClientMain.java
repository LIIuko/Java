import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientMain {

    public static final String UNIQUE_BINDING_NAME = "server.calculator";

    public static void main(String[] args){

        try {
            final Registry registry = LocateRegistry.getRegistry(2732);

            Calculator calculator = (Calculator) registry.lookup(UNIQUE_BINDING_NAME);

            Result result = calculator.quadraticEquation(1, 4, 4);

            System.out.println(result.getX1() + " " + result.getX2());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }


    }
}