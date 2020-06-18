package preis;

import kunde.KundenTyp;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
    public String getPreiseAlsJsonObjekt() {
        String preise = preiseMap
                .entrySet()
                .stream()
                .map(x -> "\"" + x.getKey() + "\":" + x.getValue().getBetrag())
                .collect(Collectors.joining(","));

        return "{" + preise + "}";
    }

    @Override
    public void setPreis(String kundenTyp, float betrag) {
        PreisIF preis = PreisFactory.erzeugePreis(kundenTyp, betrag);

        preiseMap.put(kundenTyp, preis);
    }
}
