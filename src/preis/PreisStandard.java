package preis;

/**
 * @author Patrick Metz
 */
class PreisStandard implements PreisIF {
    private final float betrag;

    public PreisStandard(Float betrag) {
        this.betrag = betrag;
    }

    @Override
    public float getBetrag() {
        return betrag;
    }
}
