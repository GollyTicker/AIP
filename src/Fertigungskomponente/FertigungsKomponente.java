package Fertigungskomponente;

import Auftragskomponente.AuftragsNr;
import Auftragskomponente.IAuftragsvermittlungVonFertigung;
import Fassade.IFertigungsUIServicesFuerFertigung;
import Materialkomponente.BauteilNr;
import Materialkomponente.IMaterialServicesFuerFertigung;
import Persistenz.IPersistenzService;
import Utilities.KeineInventurAtomarerBauteileException;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FertigungsKomponente implements IFertigungServicesFuerAuftrag, IFertigungVermittlungVonFertigungsUI {

	private Fertigungverwalter fVerw = null;
    private IMaterialServicesFuerFertigung mServ = null;
    private IFertigungsUIServicesFuerFertigung fuiServ = null;
    private IAuftragsvermittlungVonFertigung afVerm = null;
    // Die Referenz zurück zum Auftrag zur Vermittlung muss von außen mit fServ.vermittleAufraegeAn(afVerm) gesetzte werden;

	public FertigungsKomponente(IPersistenzService pServ,
                                IMaterialServicesFuerFertigung mServ,
                                IFertigungsUIServicesFuerFertigung fuiServ) {
        fVerw = new Fertigungverwalter(pServ);
        this.mServ = mServ;
        this.fuiServ = fuiServ;
	}

    // Anwendungsfälle
    @Override
    public FertigungsauftragNr fertigeAn(AuftragsNr n) throws NotFoundException, TechnicalException, KeineInventurAtomarerBauteileException {
        // FertugungsauftragNr erzeugen sowie anzahl und beuteilnr holen
        Integer anzahl = afVerm.getAuftragBauteileAnzahl(n);
        BauteilNr btn = afVerm.getAuftragBauteilNr(n);

        // atomare bauteile müssen nicht gebaut werden.
        if (!mServ.istKomplexesBauteil(btn)) {
            // atomare Bauteile sind nach Annahme immer verfügbar. Es muss nichts gemacht werden.
            FertigungsauftragNr leererPlanAuftrag = fVerw.erzeugeLeerenplanAuftrag();
            afVerm.stelleFertig(leererPlanAuftrag);
            return leererPlanAuftrag;
        }
        // falls ich genügend auf Lager habe, brauche ich nichts zu machen.
        int vorhanden = mServ.zeigeInventar(btn);
        if (vorhanden >= anzahl) {
            FertigungsauftragNr leererPlanAuftrag = fVerw.erzeugeLeerenplanAuftrag();
            afVerm.stelleFertig(leererPlanAuftrag);
            return leererPlanAuftrag;
        }

        // Sonst erzeuge ich `zuErzeugen`-viele Bauteile und verbrauche dessen Bestandteile.
        int zuErzeugen = Integer.valueOf(anzahl - vorhanden);
        Map<BauteilNr, Integer> plan = new HashMap();
        plan.put(btn, zuErzeugen);

        List<BauteilNr> verbrauch = mServ.bestandTeilevon(btn);
        fuegealsVerbrauchHinzu(verbrauch, plan);

        Fertigungsplan p = Fertigungsplan.fertigungsPlan(plan);
        FertigungsplanNr fpNr = fVerw.erzeugeFertigungsplan(p);
        FertigungsauftragNr fnr = fVerw.erzeugeFertigungsauftragAusAuftrag(n, fpNr);
        // Fertingungsplan an die Abteilung verschicken.
        fuiServ.fertigeAn(fpNr, fnr);
        return fnr;
    }

    @Override
    public void vermittleAufraegeAn(IAuftragsvermittlungVonFertigung afVerm) {
        this.afVerm = afVerm;
    }

    @Override
    public void stelleFertig(FertigungsauftragNr fNr) throws NotFoundException, TechnicalException, KeineInventurAtomarerBauteileException {
        // Fertigstellung des Auftrags, nachdem die FertigungsUI die tatsöchliche Fertigung durchgeführt hat.
        Fertigungsplan fPlan = fVerw.holeFertigungsplanzumFertigungsauftrag(fNr);
        for (Map.Entry<BauteilNr, Integer> e:fPlan.getBauteilAenderungen().entrySet()){
            BauteilNr btn = e.getKey();
            Integer i = e.getValue();
            // nur komplexe bauteile können gebaut/verbraucht werden
            if(mServ.istKomplexesBauteil(btn)) {
                if (i > 0) {
                    mServ.erzeuge(btn, i);
                }
                else {
                    mServ.verbrauche(btn, i);
                }
            }
        }
        afVerm.stelleFertig(fNr);
    }

    private void fuegealsVerbrauchHinzu(List<BauteilNr> vrb, Map<BauteilNr, Integer> plan) {
        for(BauteilNr btn:vrb){
            if (plan.containsKey(btn)) {
                Integer i = plan.get(btn) - 1;
                plan.put(btn, i);
            }
            else {
                plan.put(btn, Integer.valueOf(-1));
            }
        }
    }
}
