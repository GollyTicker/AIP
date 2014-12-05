package Materialkomponente;

import Utilities.KeineInventurAtomarerBauteileException;
import Utilities.NotFoundException;
import Utilities.TechnicalException;

import java.util.List;

/**
 * Created by Swaneet on 13.11.2014.
 */
public interface IMaterialServicesFuerFertigung {
    int zeigeInventar(BauteilNr btn) throws KeineInventurAtomarerBauteileException, NotFoundException, TechnicalException;
    boolean istKomplexesBauteil(BauteilNr btn) throws NotFoundException, TechnicalException;
    List<BauteilNr> bestandTeilevon(BauteilNr btn) throws NotFoundException, TechnicalException;
    void erzeuge(BauteilNr btn, int anzahl) throws NotFoundException, TechnicalException, KeineInventurAtomarerBauteileException;
    void verbrauche(BauteilNr btn, int anzahl) throws NotFoundException, TechnicalException;
}
