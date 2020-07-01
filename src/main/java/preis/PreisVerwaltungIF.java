package preis;

import kunde.KundenTypIF;
import java.util.HashMap;

/**
 * @author Patrick Metz
 */
public interface PreisVerwaltungIF {
    public PreisIF getPreis(KundenTypIF kundenTyp);

    public void setPreis(KundenTypIF kundenTyp, float betrag);

    void setPreiseAlsStringMap(HashMap<String, String> stringMap);
}
