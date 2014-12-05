package Fertigungskomponente;

import Auftragskomponente.AuftragsNr;
import Auftragskomponente.IAuftragsvermittlungVonFertigung;
import Utilities.KeineInventurAtomarerBauteileException;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

/**
 * Created by Swaneet on 13.11.2014.
 */
public interface IFertigungServicesFuerAuftrag {
    FertigungsauftragNr fertigeAn(AuftragsNr n) throws NotFoundException, TechnicalException, KeineInventurAtomarerBauteileException;

    void vermittleAufraegeAn(IAuftragsvermittlungVonFertigung afVerm);
}
