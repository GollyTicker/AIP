package MPS;

import Angebotskomponente.AngebotsNr;
import Auftragskomponente.AuftragsNr;
import Auftragskomponente.IAuftragServicesFuerCallCenterUI;
import Persistenz.IPersistenzService;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

import static Utilities.NotFoundException.*;
import static Utilities.TechnicalException.*;
import static Utilities.Constants.*;
import static MPS.Processor.ERSTELLE_AUFTRAG;
import static java.lang.Integer.parseInt;
import static Auftragskomponente.AuftragsNr.auftragsNr;
import static Angebotskomponente.AngebotsNr.angebotsNr;
/**
 * Created by Swaneet on 04.12.2014.
 */
public class MPSConnector implements IAuftragServicesFuerCallCenterUI {
    int port;
    Socket mpssocket;
    InputStream inputStream;
    OutputStream outputStream;
    BufferedReader reader;
    DataOutputStream writer;
    // simulates the MPS system from inside the Dispatcher.
    // needs the port on which the MPS server is listening.

    public MPSConnector(int port) throws IOException {
        this.port = port;
        this.mpssocket = new Socket("localhost", port);
        inputStream = mpssocket.getInputStream();
        outputStream = mpssocket.getOutputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        writer = new DataOutputStream(outputStream);
    }

    @Override
    public AuftragsNr erstelleAuftrag(AngebotsNr nr) throws NotFoundException, TechnicalException {
        String req = ERSTELLE_AUFTRAG + SEP + nr.toString();
        try {
            send(req);
            String resp = receive();

            if(resp.startsWith(TECHEX)){
                throwNewTechnicalException(resp);
            }
            if(resp.startsWith(NFEX)){
                throwNotFoundException();
            }
            AuftragsNr aNr = auftragsNr(parseInt(resp));
            return aNr;
        } catch (IOException e){
            e.printStackTrace();
            TechnicalException.throwNewTechnicalException(e.getMessage());
        }
        return null;
    }

    private void send(String request) throws IOException {
        writer.writeBytes(request + "\n");
    }
    private String receive() throws IOException {
        return reader.readLine();
    }
}
