package Auftragskomponente;

import Fertigungskomponente.FertigungsauftragNr;
import Materialkomponente.BauteilNr;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

/**
 * Created by Swaneet on 13.11.2014.
 */
public interface IAuftragsvermittlungVonFertigung {
    void stelleFertig(FertigungsauftragNr fnr);
    BauteilNr getAuftragBauteilNr(AuftragsNr n) throws NotFoundException, TechnicalException;
    Integer getAuftragBauteileAnzahl(AuftragsNr n) throws NotFoundException, TechnicalException;
}
