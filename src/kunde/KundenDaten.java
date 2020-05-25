package kunde;

import java.util.Date;

public class KundenDaten implements KundenDatenIF {
    private Integer nr;
    private Date beginn;
    private Integer dauer;
    private Float preis;
    private String tickethash;
    private String farbe;
    private Integer slot;
    private String kundengruppe;

    public KundenDaten (String[] params) {
        // see https://kaul.inf.h-brs.de/se/#app-content-4-0&03_Technologien=page-61
        nr = Integer.parseInt(params[0]);
        beginn = new Date(Long.parseLong(params[1]));

        dauer = null;
        if (! params[2].equals("_")) {
            dauer = Integer.parseInt(params[2]);
        }

        preis = null;
        if (! params[3].equals("_")) {
            preis = Float.parseFloat(params[3])/100;
        }

        tickethash = params[4];
        farbe = params[5];
        slot = Integer.parseInt(params[6]);
        kundengruppe = params[7];
    }

    public void updateDaten(KundenDatenIF kundenDaten) {
        this.dauer = kundenDaten.getDauer();
        this.preis = kundenDaten.getPreis();
    }

    public Integer getNr() {
        return nr;
    }

    public Date getBeginn() {
        return beginn;
    }

    public Integer getDauer() {
        return dauer;
    }

    public Float getPreis() {
        return preis;
    }

    public String getTickethash() {
        return tickethash;
    }

    public String getFarbe() {
        return farbe;
    }

    public Integer getSlot() {
        return slot;
    }

    public String getKundengruppe() {
        return kundengruppe;
    }
}
