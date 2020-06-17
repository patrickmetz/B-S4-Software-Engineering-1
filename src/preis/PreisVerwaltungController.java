package preis;

import kunde.KundenTyp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Patrick Metz
 */
public class PreisVerwaltungController implements PreisVerwaltungControllerIF {
    HashMap<String, PreisIF> preiseMap;

    public PreisVerwaltungController(KundenTyp[] kundenTypen) {
        preiseMap = new HashMap<>();
        inititialisierePreise(kundenTypen);
    }

    private void inititialisierePreise(KundenTyp[] kundenTypen) {
        for (KundenTyp kundenTyp : kundenTypen) {
            PreisIF preis = PreisFactory.erzeugePreis(
                    kundenTyp.toString(),
                    kundenTyp.getInitialPreis()
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
        String[] strings = new String[preiseMap.size()];

        int i = 0;

        for (Map.Entry<String, PreisIF> eintrag : preiseMap.entrySet()) {
            strings[i++] =
                    "\"" + eintrag.getKey() + "\""
                            + ":" + eintrag.getValue().getBetrag();
        }

        return "{" + String.join(",", strings) + "}";
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
