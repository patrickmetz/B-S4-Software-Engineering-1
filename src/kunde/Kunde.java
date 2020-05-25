package kunde;

import java.util.Calendar;
import java.util.Date;

public class Kunde implements KundeIF {

    private int dauer;
    private float preis;
    private KundenDatenIF kundenDaten;

    public Kunde (KundenDatenIF kundenDaten) {
        this.kundenDaten = kundenDaten;
    }

    @Override
    public String getKundenGruppe() {
        return kundenDaten.getKundengruppe();
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
