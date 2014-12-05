package Angebotskomponente;
import java.util.Date;
import java.util.Map;

public interface IAngebotManager {
	
public AngebotTyp erstelleAngebot(String kundenNr, Date gueltigBis, Date gueltigAb, Map<String, Integer> produktListe);

}
