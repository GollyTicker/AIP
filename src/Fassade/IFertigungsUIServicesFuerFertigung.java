package Fassade;

import Fertigungskomponente.FertigungsauftragNr;
import Fertigungskomponente.FertigungsplanNr;

/**
 * Created by Swaneet on 13.11.2014.
 */
public interface IFertigungsUIServicesFuerFertigung {
    void fertigeAn(FertigungsplanNr fpNr, FertigungsauftragNr fnr);
}
