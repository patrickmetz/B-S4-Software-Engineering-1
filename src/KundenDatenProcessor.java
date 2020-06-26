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
        return parkhaus.getEinahmen()
                .stream()
                .reduce(0.f, Float::sum);
    }

    @Override
    public int getDurchschnittsDauer() {
        return (int) parkhaus.getParkdauerZeiten()
                .stream()
                .mapToInt(i -> i)
                .average()
                .getAsDouble();
    }

    @Override
    public float getDurschnittsPreis() {
        return (float) parkhaus.getEinahmen()
                .stream()
                .mapToDouble(i -> i)
                .average()
                .getAsDouble();
    }

    @Override
    public float getUmsatzSteuer() {
        return Finanzamt.getTaxFromGross(getSumme());
    }
}
