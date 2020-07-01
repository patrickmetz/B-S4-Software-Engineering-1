package kunde;

import java.util.Date;

/**
 * @author Tobias Lohmüller
 */
public interface KundeIF {

    String getKundenGruppe();
    KundenTypIF getKundenTyp();

    int getNr();

    Date getBeginn();
    Date getEnde();

    int getDauer();

    float getPreis();

    void updateDaten(KundenDatenIF kundenDaten);

}
