package Fassade;

import Angebotskomponente.AngebotsNr;
import Auftragskomponente.AuftragsNr;
import Auftragskomponente.IAuftragServicesFuerCallCenterUI;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

/**
 * Created by Swaneet on 13.11.2014.
 */
public class CallCenterUI implements IUserInterfaceServicesFuerCallCenterUI {
    IAuftragServicesFuerCallCenterUI afServ;
    public CallCenterUI(IAuftragServicesFuerCallCenterUI afServ) {
        this.afServ = afServ;
    }

    @Override
    public AuftragsNr erstelleAuftrag(AngebotsNr anr) throws NotFoundException, TechnicalException {
        return afServ.erstelleAuftrag(anr);
    }
}
