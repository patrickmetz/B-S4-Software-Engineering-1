package Einnahmen;

import java.util.*;

/**
 * @author Tobias Lohmüller
 */

public abstract class EinnahmenCalculator {

    private List<Einnahme> einnahmen;
    private List<Einnahme> einnahmenSummen;

    public EinnahmenCalculator(List<Einnahme> einnahmen) {
        this.einnahmen = einnahmen;
        einnahmenSummen = new ArrayList<>();
        sumEinnahmen();
    }

    /**
     * Iteriert durch die dazugehörigen Zeiteinheit (Tage, Wochen, Monate, Jahre, etc.)
     * @return Letztes Datum der Zeiteinheit, Totaleinnahmen der Zeiteinheit
     */
    final public Iterator<Einnahme> einnahmenIterator() {
        return einnahmenSummen.iterator();
    }

    public float getSumEinnahmen() {
        return einnahmenSummen
                .stream()
                .map(Einnahme::getWert)
                .reduce(0.f, Float::sum);
    }

    List<Einnahme> getEinnahmen() {
        return einnahmen;
    }

    void addEinnahmenSumme(Date date, float summe) {
        einnahmenSummen.add(new Einnahme(date, summe));
    }

    void sumEinnahmen() {
        Calendar lastDate = null;
        float tempSum = 0;

        for(Einnahme einnahme : getEinnahmen()) {
            Calendar currentDate = Calendar.getInstance();
            currentDate.setTime(einnahme.getDate());

            if (lastDate == null) {
                lastDate = Calendar.getInstance();
                lastDate.setTime(einnahme.getDate());
            }

            //Seperate
            if (sumTimeDedicator(currentDate, lastDate)) {
                addEinnahmenSumme(lastDate.getTime(), tempSum);
                tempSum = 0;
            }

            tempSum += einnahme.getWert();

            lastDate.setTime(einnahme.getDate());
        }

        if (tempSum == 0) {
            return;
        }

        addEinnahmenSumme(lastDate.getTime(), tempSum);
    }

    abstract boolean sumTimeDedicator(Calendar currentDate, Calendar lastDate);

}
