package preis;

/**
 * @author Patrick Metz
 */
public interface PreisVerwaltungControllerIF {
    public PreisIF getPreis(String kundenTyp);

    public String getPreiseAlsJson();

    public boolean setPreis(String kundenTyp, float betrag);
}
