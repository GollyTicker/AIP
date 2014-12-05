package RMIexample;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Swaneet on 04.12.2014.
 */
public class Getter {
    public static void main(String[] args) throws Exception {
        Registry reg = LocateRegistry.getRegistry(Maker.port);
        ITest myTest = (ITest)reg.lookup(Maker.name);
        System.out.println(myTest.add(4,5));
    }
}
