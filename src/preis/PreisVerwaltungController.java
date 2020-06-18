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
        String[] preiseStrings = new String[preiseMap.size()];

        int i = 0;

        for (Map.Entry<String, PreisIF> eintrag : preiseMap.entrySet()) {
            preiseStrings[i++] =
                    "\"" + eintrag.getKey() + "\""
                            + ":" + eintrag.getValue().getBetrag();
        }

        return "{" + String.join(",", preiseStrings) + "}";
    }

    @Override
    public void setPreis(String kundenTyp, float betrag) {
        PreisIF preis = PreisFactory.erzeugePreis(kundenTyp, betrag);

        preiseMap.put(kundenTyp, preis);
    }
}
