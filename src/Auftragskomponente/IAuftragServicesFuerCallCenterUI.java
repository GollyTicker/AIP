package Auftragskomponente;

import Angebotskomponente.AngebotsNr;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

public interface IAuftragServicesFuerCallCenterUI {
    AuftragsNr erstelleAuftrag(AngebotsNr nr) throws NotFoundException, TechnicalException;
}
