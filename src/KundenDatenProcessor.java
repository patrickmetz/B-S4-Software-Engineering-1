/**
 * @author Tobias Lohmüller
 * @author Patrick Metz
 * @author Johannes Kratzsch
 */
public class KundenDatenProcessor implements KundenDatenProcessorIF {

    private ParkhausIF parkhaus;

    public KundenDatenProcessor(ParkhausIF parkhaus) {
        this.parkhaus = parkhaus;
    }

    @Override
    public float getSumme() {
        float sum = 0;
        for (float einnahme : parkhaus.getEinahmen()) {
            sum += einnahme;
        }

        return sum;
    }

    @Override
    public int getDurchschnittsDauer() {
        int totalDuration = 0;

        for (float duration : parkhaus.getParkdauerZeiten()) {
            totalDuration += duration;
        }

        return totalDuration/parkhaus.getParkdauerZeiten().size();
    }

    @Override
    public float getDurschnittsPreis() {
        return getSumme()/parkhaus.getEinahmen().size();
    }

    @Override
    public float getUmsatzSteuer() {
        float salesTax = 0;
        float taxRate = 0.19f;

        for (float revenue : parkhaus.getEinahmen()) {
            float baseValue = revenue / (1 + taxRate);
            salesTax += (revenue - baseValue);
        }

        return salesTax;
    }
}
