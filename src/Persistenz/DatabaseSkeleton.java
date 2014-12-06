package Persistenz;

import java.net.Socket;
import java.io.*;

/**
 * Created by croehricht on 05.12.14.
 */
public class DatabaseSkeleton extends Thread {
    private Socket socket;
    private BufferedReader inStream;
    private DataOutputStream outStream;
    private IPersistenzService dbService;

    public DatabaseSkeleton(Socket socket, IPersistenzService dbService) {
        this.socket = socket;
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
                outStream.writeBytes(respondToCall(call) + "\n");
            } catch (IOException ex) {
                
            }
        }
    }

    private String respondToCall(String call) {
        //TODO parse to crud operation on dbService
        return "";
    }

    public void shutdown() {
        interrupt();
        try {
            socket.close();
        } catch (IOException ex) {}
    }
}
