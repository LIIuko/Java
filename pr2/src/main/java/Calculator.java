import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

    Result quadraticEquation(int a, int b, int c) throws RemoteException;
}