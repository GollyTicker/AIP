package Angebotskomponente;
import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Entity
@Table(name = "angebot")

public class Angebot  {

    @Id
    private String angebotNr;


    private String kundenNr;

    private Date gueltigBis, gueltigAb;

    @ElementCollection( fetch = FetchType.EAGER)
    private Map<String, Integer> produktListe;


    public Angebot(String kundenNr, Date gueltigBis, Date gueltigAb, Map<String, Integer> produktListe) {
        this.angebotNr = "ANG-" + UUID.randomUUID();
        this.kundenNr = kundenNr;
        this.gueltigAb = gueltigAb;
        this.gueltigBis = gueltigBis;
        this.produktListe = new HashMap<>(produktListe);
    }

    private Angebot() {

    }


    public AngebotTyp getAngebotTyp() {
        return new AngebotTyp(angebotNr, kundenNr, new Date(gueltigBis.getTime()), new Date(gueltigAb.getTime()), new HashMap<>(produktListe));
    }


    String getAngebotNr() {
        return angebotNr;
    }

    void setAngebotNr(String angebotNr) {
        this.angebotNr = angebotNr;
    }

    private String getKundenNr() {
        return kundenNr;
    }

    private void setKundenNr(String kundenNr) {
        this.kundenNr = kundenNr;
    }

    private Date getGueltigBis() {
        return gueltigBis;
    }

    private void setGueltigBis(Date gueltigBis) {
        this.gueltigBis = gueltigBis;
    }

    private Date getGueltigAb() {
        return gueltigAb;
    }

    private void setGueltigAb(Date gueltigAb) {
        this.gueltigAb = gueltigAb;
    }


    private Map<String, Integer> getProduktListe() {
        return produktListe;
    }

    private void setProduktListe(Map<String, Integer> produktListe) {
        this.produktListe = produktListe;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Angebot{");
        sb.append("angebotNr='").append(angebotNr).append('\'');
        sb.append(", kundenNr='").append(kundenNr).append('\'');
        sb.append(", gueltigBis=").append(gueltigBis);
        sb.append(", gueltigAb=").append(gueltigAb);
        sb.append(", produktListe=").append(produktListe);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Angebot angebot = (Angebot) o;

        if (!angebotNr.equals(angebot.angebotNr)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return angebotNr.hashCode();
    }

}
