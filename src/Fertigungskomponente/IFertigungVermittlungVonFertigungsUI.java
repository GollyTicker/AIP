package Fertigungskomponente;


import Utilities.KeineInventurAtomarerBauteileException;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

/**
 * Created by Swaneet on 13.11.2014.
 */
public interface IFertigungVermittlungVonFertigungsUI {
    void stelleFertig(FertigungsauftragNr fNr) throws NotFoundException, TechnicalException, KeineInventurAtomarerBauteileException;
}
