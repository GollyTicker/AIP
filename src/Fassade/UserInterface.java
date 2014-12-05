package Fassade;

import Auftragskomponente.AuftragsKomponente;
import Auftragskomponente.IAuftragServicesFuerCallCenterUI;
import Auftragskomponente.IAuftragsvermittlungVonFertigung;
import Fertigungskomponente.FertigungsKomponente;
import Fertigungskomponente.IFertigungServicesFuerAuftrag;
import Materialkomponente.IMaterialServicesFuerFertigung;
import Materialkomponente.MaterialKomponente;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Utilities.TechnicalException;

public class UserInterface {
    IPersistenzService perServ;
    public IAuftragServicesFuerCallCenterUI afServ;
    public IFertigungServicesFuerAuftrag fServ;
    IMaterialServicesFuerFertigung mServ;
    IAuftragsvermittlungVonFertigung afVerm;
    public IFertigungsUIServicesFuerFertigung fuiServ;
	public UserInterface() throws TechnicalException {
		this.perServ = new DatabaseConnection();
        this.mServ = new MaterialKomponente(perServ);
        fuiServ = new FertigungsUI();
        this.fServ = new FertigungsKomponente(perServ, mServ, fuiServ);
		AuftragsKomponente afK = new AuftragsKomponente(perServ, fServ);
        this.afVerm = afK;
        fServ.vermittleAufraegeAn(afVerm);
        new CallCenterUI(afServ);
	}
}
