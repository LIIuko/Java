import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerMain {

    public static final String UNIQUE_BINDING_NAME = "server.calculator";

    public static void main(String[] args){

        try {
            RemoteCalculationServer server = new RemoteCalculationServer();
            final Registry registry = LocateRegistry.createRegistry(2732);

            Remote stub = UnicastRemoteObject.exportObject(server, 0);
            registry.bind(UNIQUE_BINDING_NAME, stub);
            System.out.println("Server started");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
