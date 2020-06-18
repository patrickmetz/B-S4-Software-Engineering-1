package preis;

/**
 * @author Patrick Metz
 */
public interface PreisVerwaltungControllerIF {
    public PreisIF getPreis(String kundenTyp);

    public String getPreiseAlsJson();

    public void setPreis(String kundenTyp, float betrag);
}
