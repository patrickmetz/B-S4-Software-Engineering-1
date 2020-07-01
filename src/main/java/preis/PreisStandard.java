package preis;

/**
 * @author Patrick Metz
 */
class PreisStandard implements PreisIF {
    private final float betrag;
    private final String bezeichnung;

    public PreisStandard(Float betrag, String bezeichnung) {
        this.betrag = betrag;
        this.bezeichnung = bezeichnung;
    }

    @Override
    public float getBetrag() {
        return betrag;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
}
