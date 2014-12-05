package Persistenz;

import Utilities.TechnicalException;

import java.net.ServerSocket;

/**
 * Created by croehricht on 05.12.14.
 */
public class DatabaseServer extends Thread {
    private int port;
    private ServerSocket server;
    private List<DatabaseSkeleton> skeletons;
    private IPersistenzService dbService;

    public DatabaseServer(int port) {
        this.port = port;
        try {
            this.dbService = new DatabaseConnection();
        } catch (TechnicalException ex) {}
        try {
            this.server = new ServerSocket(port);
            //TODO dbService
        } catch (IOException ex) {}
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Socket connectionSocket = server.accept();
                skeletons.add(new DatabaseSkeleton(connectionSocket, dbService));
            } catch (IOException ex) {}
        }
    }
}
