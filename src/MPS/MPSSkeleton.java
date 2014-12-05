package MPS;

import Angebotskomponente.AngebotsNr;
import Auftragskomponente.AuftragsNr;
import Auftragskomponente.IAuftragServicesFuerCallCenterUI;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

/**
 * Created by Swaneet on 04.12.2014.
 */
// implementiert die Methoden die der Client aufrugen will. und macht das Parsen der Funktionsaufrufe
// Es läuft auf dem Port MPS_BASE_PORT + num
// D.h. MPS4 Skeleton läuft auf port 9304
public class MPSSkeleton {
    IAuftragServicesFuerCallCenterUI afServ;

    public MPSSkeleton(int skeletonPort, IAuftragServicesFuerCallCenterUI afServ) {
        // listen on Port skeletonPort and return the results
        Processor p = new Processor(afServ);
        // for each new Client start new Thread and process methods
        new MPSServer(skeletonPort, p).start();
    }
}
