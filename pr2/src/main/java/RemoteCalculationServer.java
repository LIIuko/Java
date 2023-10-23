import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RemoteCalculationServer implements Calculator {

    public RemoteCalculationServer() throws RemoteException {
        super();
    }

    @Override
    public Result quadraticEquation(int a, int b, int c) throws RemoteException {
        double D = pow(b, 2) - 4 * a * c;
        double x1 = (-b + sqrt(D)) / (2 * a);
        double x2 = (-b - sqrt(D)) / (2 * a);
        return new Result(x1, x2);
    }
}
