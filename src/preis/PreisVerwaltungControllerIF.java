package preis;

import kunde.KundenTypIF;

/**
 * @author Patrick Metz
 */
public interface PreisVerwaltungControllerIF {
    public PreisIF getPreis(KundenTypIF kundenTyp);

    public String getPreiseAlsJsonArray();

    public void setPreis(KundenTypIF kundenTyp, float betrag);
}
