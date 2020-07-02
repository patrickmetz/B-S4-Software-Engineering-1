package kunde;

import Fahrzeuge.FahrzeugTyp;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Tobias Lohmüller
 */
public class Kunde implements KundeIF {
    private KundenDatenIF kundenDaten;
    private final KundenTyp kundenTyp;

    public Kunde (KundenDatenIF kundenDaten) {
        this.kundenDaten = kundenDaten;

        kundenTyp = KundenTyp.valueOf(kundenDaten.getKundengruppe());
    }

    @Override
    public String getKundenGruppe() {
        return kundenDaten.getKundengruppe();
    }

    @Override
    public KundenTypIF getKundenTyp() {
        return kundenTyp;
    }

    @Override
    public int getNr() {
        return kundenDaten.getNr();
    }

    @Override
    public Date getBeginn() {
        return kundenDaten.getBeginn();
    }

    @Override
    public Date getEnde() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getBeginn());
        calendar.add(Calendar.SECOND, kundenDaten.getDauer());

        return calendar.getTime();
    }

    @Override
    public FahrzeugTyp getFahrzeugTyp() {
        return kundenDaten.getFahrzeugTyp();
    }

    public void updateDaten(KundenDatenIF kundenDaten) {
        this.kundenDaten.updateDaten(kundenDaten);
    }

    @Override
    public int getDauer() {
        return kundenDaten.getDauer();
    }

    @Override
    public float getPreis() {
        return kundenDaten.getPreis();
    }

}
