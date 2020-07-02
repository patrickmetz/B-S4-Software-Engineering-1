package kunde;

import Fahrzeuge.FahrzeugTyp;

import java.util.Date;

/**
 * @author Tobias Lohmüller
 */
public interface KundenDatenIF {
    FahrzeugTyp getFahrzeugTyp();

    Integer getNr();

    Date getBeginn();

    Integer getDauer();

    Float getPreis();

    String getTickethash();

    String getFarbe();

    Integer getSlot();

    String getKundengruppe();

    void updateDaten(KundenDatenIF kundenDaten);
}
