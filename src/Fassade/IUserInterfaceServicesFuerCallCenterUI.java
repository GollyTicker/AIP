package Fassade;

import Angebotskomponente.AngebotsNr;
import Auftragskomponente.AuftragsNr;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

public interface IUserInterfaceServicesFuerCallCenterUI {
	public AuftragsNr erstelleAuftrag(AngebotsNr anr) throws NotFoundException, TechnicalException;
}
