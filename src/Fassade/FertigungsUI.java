package Fassade;

import Fertigungskomponente.FertigungsauftragNr;
import Fertigungskomponente.FertigungsplanNr;
import Fertigungskomponente.IFertigungServicesFuerAuftrag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swaneet on 13.11.2014.
 */
public class FertigungsUI implements IFertigungsUIServicesFuerFertigung {
    public List<FertigungsplanNr> erhalteneAuftraege = new ArrayList<FertigungsplanNr>();
    public FertigungsUI() {
    }

    @Override
    public void fertigeAn(FertigungsplanNr fpNr, FertigungsauftragNr fnr) {
        System.out.println("Plan " + fpNr + " vom Fertigungsauftrag " + fnr + " wurde von der Abteilung gefertigt.");
        erhalteneAuftraege.add(fpNr);
    }
}
