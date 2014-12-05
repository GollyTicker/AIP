package RMIexample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Swaneet on 04.12.2014.
 */
public class Maker {
    static ITest t;
    public static int port = 50000;
    public static String name = "MPS1";
    public static void main(String[] args) throws Exception {
        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        t = new TestImpl();
        ITest stub = (ITest) UnicastRemoteObject.exportObject(t, 0); // automatically creates the registry.
        Registry reg = LocateRegistry.getRegistry();
        //System.out.println("List:" + reg.list());
        //System.out.println("List:" + reg);
        reg.rebind(name, stub);

    }
}
