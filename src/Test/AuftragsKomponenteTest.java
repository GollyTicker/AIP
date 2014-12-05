package Test;

import static Angebotskomponente.AngebotsNr.angebotsNr;

import Angebotskomponente.AngebotsNr;
import Auftragskomponente.AuftragsNr;
import Auftragskomponente.Auftragverwalter;
import Persistenz.DatabaseConnection;
import Persistenz.IPersistenzService;
import Utilities.NotFoundException;
import Utilities.TechnicalException;
import junit.framework.Assert;
import junit.framework.TestCase;;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static Persistenz.DatabaseConnection.ANGEBOT;
import static Persistenz.DatabaseConnection.AUFTRAG;
import static org.junit.Assert.assertTrue;

public class AuftragsKomponenteTest extends TestCase {
    IPersistenzService pServ;
    Auftragverwalter afVerwalt1;
    String angS;
    int angNr = 0;
    @Before
    public void setUp() throws Exception {
        pServ = new DatabaseConnection();
        afVerwalt1 = new Auftragverwalter(pServ);

        // Angebot in Datenbank erzeugen
        // 15 ist die KundenNr
        // 3 ist die BauteilNr
        // 7 ist die Anzahl der angebotenen Bauteile
        // 14.2 sind die Kosten in Euro
        angS = "15,3,7,14.2";
        angNr = pServ.create(ANGEBOT, angS);
    }

    @After
    public void tearDown() {
        pServ = null;
        afVerwalt1 = null;
        angS = null;
        angNr = 0;
    }

    @Test
    public void testerzeugeAuftragAusAngebot() throws TechnicalException, NotFoundException {

        // Einen Auftrag aus dem Angebot erzeugen
        AngebotsNr an = angebotsNr(angNr);
        AuftragsNr aufNr = afVerwalt1.erzeugeAuftragAusAngebot(an);

        // Aus der Datenbank lesen und auf gleichheit prüfen
        String s = pServ.read(AUFTRAG, aufNr.getNR());
        Assert.assertEquals(s, angS);

        // das Angebot sollte nicht mehr existieren
        boolean thrown = false;
        try {
            pServ.read(ANGEBOT, angNr);
        }
        catch (NotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}