package Einnahmen;

import java.util.Date;

public class Einnahme {

    private Date date;
    private float wert;

    public Einnahme(Date date, float wert) {
        this.date = date;
        this.wert = wert;
    }

    public Date getDate() {
        return date;
    }

    public float getWert() {
        return wert;
    }


}
