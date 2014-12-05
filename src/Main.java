import Angebotskomponente.AngebotsNr;
import Fassade.IUserInterfaceServicesFuerCallCenterUI;
import Fassade.UserInterface;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;

/**
 * Created by Swaneet on 13.11.2014.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        UserInterface ui = new UserInterface();
        ui.afServ.erstelleAuftrag(AngebotsNr.angebotsNr(15));
    }
}
