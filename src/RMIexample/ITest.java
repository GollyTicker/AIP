package RMIexample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Swaneet on 04.12.2014.
 */
public interface ITest extends Remote {
    Integer add(Integer a, Integer b) throws RemoteException;
}
