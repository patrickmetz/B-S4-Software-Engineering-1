package preis;

/**
 * @author Patrick Metz
 */
public class Preis implements PreisIF {
    private final float betrag;

    Preis(float betrag) {
        this.betrag = betrag;
    }

    @Override
    public float getBetrag() {
        return betrag;
    }
}
