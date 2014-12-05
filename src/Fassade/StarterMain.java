package Fassade;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import Fassade.DashboardGUI;
import Wartung.Dispatcher;
import Wartung.Monitor;
import Wartung.ServerManager;

import java.awt.*;

public class StarterMain {
	
	  private static Dispatcher dispatcher = null;
	  private static Monitor monitor = null;
	//private static CallCenterUI callcenterui = null;
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {

		dispatcher = new Dispatcher();
		DashboardGUI dashboard = new DashboardGUI(monitor, new ServerManager());

     }
}
