package kunde;

import Fahrzeuge.FahrzeugTyp;

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

    FahrzeugTyp getFahrzeugTyp();

    int getDauer();

    float getPreis();

    void updateDaten(KundenDatenIF kundenDaten);

}
