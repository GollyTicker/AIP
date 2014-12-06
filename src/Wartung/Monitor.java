package Wartung;

import MPS.MPS;
import MPS.MPSclientAcceptor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import static MPS.MPS.MPS_BASE_PORT;

/**
 * Created by Swaneet on 03.12.2014.
 */
public class Monitor extends Thread{
    // * Der Monitor hält sich mit dem Status der Prozesse im Laufenenden, benachrichtigt Dispatcher beim Ausfall
    //    * evtl. sind Monitor  und Dispatcher ein einziger Thread.
    public static int MONITOR_LISTENER_PORT = 9600;
    public static int ALIVE_INTERVAL_MILLIS = 100;
    public static int ALIVE_TIMEOUT_MILLIS= 500;
    public static String ALIVE = "alive";
    ServerSocket serverSocket;
    public static Map<Integer, String> mpssystems = new HashMap(); // stores the responses of the reporters

    public Monitor() {
        try {
            serverSocket = new ServerSocket(MONITOR_LISTENER_PORT);
            System.out.println("Monitor Listener [" + MONITOR_LISTENER_PORT+"]");
        } catch (IOException se) {
            System.err.println("Monitor Listener: Can not start listening on port " + MONITOR_LISTENER_PORT);
            se.printStackTrace();
            System.exit(-1);
        }
    }

    synchronized public void recordState(Integer mpsNum, String state) {
        mpssystems.putIfAbsent(mpsNum, state);
    }

    synchronized public void deleteMPS(Integer mpsNum) {
        mpssystems.remove(mpsNum);
    }

    public int getFirstMPSport() {
        while(noAvailibleSystems()) {
            Thread.yield();
        }
        for(Map.Entry<Integer, String> e:mpssystems.entrySet()) {
            return MPS_BASE_PORT  + e.getKey();
        }
        throw new RuntimeException("blubb");
    }

    synchronized private boolean noAvailibleSystems() {
        return mpssystems.isEmpty();
    }

    public void run() {
        while (true) {  // forver keep accepting new clients.
            try {
                Socket socket = serverSocket.accept();
                new ReporterHandler(socket, this).start();
                System.out.println("Monitor Listener: Got New MPS.");
            } catch (IOException e) {
                System.out.println("Monitor Listener: Exception on MPS");
                e.printStackTrace();
            }
        }
    }
}
