package preis;

import kunde.KundenTyp;

import java.util.HashMap;

/**
 * @author Patrick Metz
 */
public class PreisVerwaltungController implements PreisVerwaltungControllerIF {
    HashMap<String, PreisIF> preiseMap;

    public PreisVerwaltungController(KundenTyp[] kundenTypen) {
        preiseMap = new HashMap<>();

        for (KundenTyp kundenTyp : kundenTypen) {
            PreisIF preis = PreisFactory.erzeugePreis(
                    kundenTyp.toString(),
                    kundenTyp.getPreis()
            );

            preiseMap.put(kundenTyp.toString(), preis);
        }
    }

    @Override
    public PreisIF getPreis(String kundenTyp) {
        return preiseMap.get(kundenTyp);
    }

    @Override
    public String getPreiseAlsJson() {

        return null;
    }

    @Override
    public boolean setPreis(String kundenTyp, float betrag) {
        PreisIF preis = PreisFactory.erzeugePreis(kundenTyp, betrag);

        if (preis == null) {
            return false;
        } else {
            preiseMap.put(kundenTyp, preis);

            return true;
        }
    }
}
