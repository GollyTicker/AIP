package Persistenz;

import Utilities.TechnicalException;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

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
        } catch (IOException ex) {}
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Socket connectionSocket = server.accept();
                DatabaseSkeleton skel = new DatabaseSkeleton(connectionSocket, dbService)
                skeletons.add(skel);
                skel.start();
            } catch (IOException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown() {
        for (DatabaseSkeleton skel : skeletons) {
            skel.shutdown();
        }
        interrupt();
        try {
            server.close();
        } catch (IOException ex) {}
    }
}
