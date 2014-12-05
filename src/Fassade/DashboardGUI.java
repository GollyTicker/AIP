package Fassade;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import Wartung.Monitor;
import Wartung.ServerManager;

import static Wartung.ServerManager.*;

public class DashboardGUI extends JFrame{

	Monitor monitor;
	JList<String> listOfAllServers;
	DefaultListModel<String> listOfAllServersListModel;
	JTextPane selectedServerStatusPane;
	JTextField downTimeTextPane;
	JTextField upTimeTextPane;
	private JTextField txtAvailableServers;
	private JTextField dispatcherStatusTextField;
    ServerManager smgr;
	
	public DashboardGUI(Monitor monitor, ServerManager smgr) {
		
		JFrame houptFenster = new JFrame("Dashboard");
		this.monitor = monitor;
        this.smgr = smgr;
		
		setSize(500, 400);
		setLocation(100,75);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLayout(null);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnStartServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                smgr.startServer();
			}
        });
		
		btnStartServer.setBounds(155, 22, 112, 23);
		panel.add(btnStartServer);
		
		listOfAllServersListModel = new DefaultListModel<String>();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 24, 112, 238);
		panel.add(scrollPane);
		listOfAllServers = new JList<String>(listOfAllServersListModel);
		scrollPane.setViewportView(listOfAllServers);
		listOfAllServers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!listOfAllServers.isSelectionEmpty()) {
					String selectedElement = listOfAllServers.getSelectedValue();
                    smgr.setSelectedServer(Integer.parseInt(selectedElement));
				} else {
                    smgr.setSelectedServer(0);
				}
			}
		});
		
		listOfAllServers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		txtAvailableServers = new JTextField();
		txtAvailableServers.setEditable(false);
		txtAvailableServers.setText("Available Servers");
		scrollPane.setColumnHeaderView(txtAvailableServers);
		txtAvailableServers.setColumns(10);
		
		JTextField txtpnSelectedServerStatus = new JTextField();
		txtpnSelectedServerStatus.setEditable(false);
		txtpnSelectedServerStatus.setText("Server Status:");
		txtpnSelectedServerStatus.setBounds(155, 62, 234, 23);
		panel.add(txtpnSelectedServerStatus);
		
		selectedServerStatusPane = new JTextPane();
		selectedServerStatusPane.setEditable(false);
		selectedServerStatusPane.setBounds(155, 96, 234, 23);
		panel.add(selectedServerStatusPane);
		
		JButton killServerButton = new JButton("Stop Server");
		killServerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                smgr.killServer(Integer.parseInt(listOfAllServers.getSelectedValue()));
			}
		});
		killServerButton.setBounds(277, 22, 112, 23);
		panel.add(killServerButton);
		
		downTimeTextPane = new JTextField();
		downTimeTextPane.setEditable(false);
		downTimeTextPane.setBounds(155, 208, 102, 23);
		panel.add(downTimeTextPane);
		
		upTimeTextPane = new JTextField();
		upTimeTextPane.setEditable(false);
		upTimeTextPane.setBounds(287, 208, 102, 23);
		panel.add(upTimeTextPane);
		
		dispatcherStatusTextField = new JTextField();
		dispatcherStatusTextField.setEditable(false);
		dispatcherStatusTextField.setBounds(156, 242, 233, 20);
		panel.add(dispatcherStatusTextField);
		dispatcherStatusTextField.setColumns(10);
		setVisible(true);
		
	}
	
	
	synchronized public void setIdleAmount(int noOfIdleServers) {
		downTimeTextPane.setText("DownTime: " + noOfIdleServers);
	}
	
	synchronized public void setBusyAmount(int noOfBusyServers) {
		upTimeTextPane.setText("UpTime: " + noOfBusyServers);
	}
	
	synchronized public void setDispatcherStatus(boolean alive) {

		if(alive) {
			dispatcherStatusTextField.setText("Dispatcher Status: I am Alive");
		} else {
			dispatcherStatusTextField.setText("Dispatcher Status: I am Dead");
		}
	}
	
	synchronized public void setStatusOfSelectedServer(String status) {
		if(status == null) {
			selectedServerStatusPane.setText("Not available");
		} else {
			selectedServerStatusPane.setText(status);
		}
	}
	
	synchronized public void refreshServerList(Set<Integer> serverList) {
		int selectedIndex = listOfAllServers.getSelectedIndex();
		listOfAllServersListModel.clear();
		for(Integer server : serverList) {
			listOfAllServersListModel.addElement(Integer.toString(server));
		}
		if(selectedIndex < listOfAllServersListModel.getSize()) {
			listOfAllServers.setSelectedIndex(selectedIndex);
		} else {
			listOfAllServers.setSelectedIndex(listOfAllServersListModel.getSize() - 1);
		}
	}
	
}
	

