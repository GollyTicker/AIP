package Persistenz;

import Persistenz.IPersistenzService;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

import java.io.*;
import java.net.Socket;
import static Utilities.TechnicalException.TECHEX;
import static Utilities.NotFoundException.NFEX;

/**
 * Created by Swaneet on 04.12.2014.
 */
public class DatabaseConnector implements IPersistenzService {
    int port=0;
    Socket db;
    InputStream inputStream;
    OutputStream outputStream;
    BufferedReader reader;
    DataOutputStream writer;
    // Simulated the Database Connection from inside the MPS system.

    public DatabaseConnector(int port) throws IOException {
        this.port = port;
        this.db = new Socket("localhost", port);
        inputStream = db.getInputStream();
        outputStream = db.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        writer = new DataOutputStream(outputStream);
    }

    @Override
    public int create(String type, String typeRep) throws TechnicalException {
        String request = "create;" + type + ";" + typeRep;
        try {
            send(request);
            String resp = receive();
            if (resp.startsWith(TECHEX)){
                TechnicalException.throwNewTechnicalException(TECHEX);
            }
            return Integer.parseInt(resp);
        } catch (IOException e) {
            e.printStackTrace();
            TechnicalException.throwNewTechnicalException(e.getMessage());
        }
        return 0;
    }

    @Override
    public String read(String type, int id) throws NotFoundException, TechnicalException {
        String request = "read;" + type + ";" + id;
        try {
            send(request);
            String resp = receive();
            if (resp.startsWith(NFEX)) {
                NotFoundException.throwNotFoundException();
            }
            if (resp.startsWith(TECHEX)){
                TechnicalException.throwNewTechnicalException(resp);
            }
            return resp;
        } catch (IOException e){
            e.printStackTrace();
            TechnicalException.throwNewTechnicalException(e.getMessage());
        }
        return "";
    }

    @Override
    public void update(String type, int id, String typeRep) throws NotFoundException, TechnicalException {
        String request = "update;" + type + ";" + id + ";" + typeRep;
        try {
            send(request);
            String resp = receive();

            if (resp.startsWith(NFEX)){
                NotFoundException.throwNotFoundException();
            }
            if (resp.startsWith(TECHEX)){
                TechnicalException.throwNewTechnicalException(resp);
            }
        } catch (IOException e){
            e.printStackTrace();
            TechnicalException.throwNewTechnicalException(e.getMessage());
        }
    }

    @Override
    public void delete(String type, int id) throws TechnicalException {
        String request = "delete;" + type + ";" + id;
        try {
            send(request);
            String resp = receive();
            if(resp.startsWith(TECHEX)){
                TechnicalException.throwNewTechnicalException(resp);
            }
        } catch (IOException e){
            e.printStackTrace();
            TechnicalException.throwNewTechnicalException(e.getMessage());
        }

    }
    private void send(String request) throws IOException {
        writer.writeBytes(request + "\n");
    }
    private String receive() throws IOException {
        String request = reader.readLine();
        return request;
    }
}
