package Wartung;

import Angebotskomponente.AngebotsNr;
import Auftragskomponente.AuftragsNr;
import Auftragskomponente.IAuftragServicesFuerCallCenterUI;
import MPS.MPSConnector;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

import java.io.IOException;

/**
 * Created by Swaneet on 03.12.2014.
 */
public class Dispatcher implements IAuftragServicesFuerCallCenterUI {
    Monitor monitor;
    public Dispatcher() throws IOException {
        this.monitor = new Monitor();
        monitor.start();
        // TODO: DashboardGUI starten
    }

    private MPSConnector getAvailibleMPS() throws TechnicalException{
        System.out.println("Dispaatcher: Searching for availible MPS...");
        int port = monitor.getFirstMPSport();
        System.out.println("Dispatcher: MPS ["+port+"]");
        return new MPSConnector(port);
    }

    // * Dispatcher, welcher jeweils an dem ersten freien Systmem die Anfragen abschickt.
    @Override
    public AuftragsNr erstelleAuftrag(AngebotsNr nr) throws NotFoundException, TechnicalException {
        MPSConnector mps = getAvailibleMPS();
        return mps.erstelleAuftrag(nr);
    }
}
