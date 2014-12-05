package Wartung;

/**
 * Created by Swaneet on 03.12.2014.
 */
public class Monitor {
    // * Der Monitor hält sich mit dem Status der Prozesse im Laufenenden, benachrichtigt Dispatcher beim Ausfall
    //    * evtl. sind Monitor  und Dispatcher ein einziger Thread.
    public static int MONITOR_LISTENER_PORT = 9600;
    public static int ALIVE_INTERVAL_MILLIS = 100;
    public static int ALIVE_TIMEOUT_TIMEOUT= 500;
}
