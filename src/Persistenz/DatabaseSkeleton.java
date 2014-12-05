package Persistenz;

import java.net.Socket;
import java.io.*;

/**
 * Created by croehricht on 05.12.14.
 */
public class DatabaseSkeleton extends Thread {
    private Socket connectionSocket;
    private BufferedReader inStream;
    private DataOutputStream outStream;
    private IPersistenzService dbService;

    public DatabaseSkeleton(Socket connectionSocket, IPersistenzService dbService) {
        this.connectionSocket = connectionSocket;
        this.dbService = dbService;
        try {
            this.inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.outStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {}
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                String call = inStream.readLine();
                outStream.writeBytes(respondToCall(call));
            } catch (IOException ex) {
                
            }
        }
    }

    private String respondToCall(String call) {
        //TODO parse crud
    }
}
