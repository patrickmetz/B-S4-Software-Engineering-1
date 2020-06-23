package preis;

import kunde.KundenTyp;
import kunde.KundenTypIF;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author Patrick Metz
 */
public class PreisVerwaltungController implements PreisVerwaltungControllerIF {
    public static final String JSON_PREIS_OBJEKT = "{\"typ\" : \"%s\", \"bezeichnung\" : \"%s\", \"betrag\" : %s}";
    public static final String JSON_PREIS_OBJEKT_ARRAY = "[%s]";

    HashMap<String, PreisIF> preiseMap;

    public PreisVerwaltungController(KundenTyp[] kundenTypen) {
        preiseMap = new HashMap<>();
        initialisierePreise(kundenTypen);
    }

    private void initialisierePreise(KundenTyp[] kundenTypen) {
        for (KundenTypIF kundenTyp : kundenTypen) {
            PreisIF preis = PreisFactory.erzeugePreis(
                    kundenTyp,
                    kundenTyp.getInitialPreis()
            );

            preiseMap.put(kundenTyp.getTyp(), preis);
        }
    }

    @Override
    public PreisIF getPreis(KundenTypIF kundenTyp) {
        return preiseMap.get(kundenTyp.getTyp());
    }

    @Override
    public String getPreiseAlsJsonArray() {
        String preise = preiseMap
                .entrySet()
                .stream()
                .map(
                        x -> String.format(
                                JSON_PREIS_OBJEKT,
                                x.getKey(),
                                x.getValue().getBezeichnung(),
                                x.getValue().getBetrag()
                        )
                )
                .collect(Collectors.joining(","));

        return String.format(JSON_PREIS_OBJEKT_ARRAY, preise);
    }

    @Override
    public void setPreis(KundenTypIF kundenTyp, float betrag) {
        PreisIF preis = PreisFactory.erzeugePreis(kundenTyp, betrag);

        preiseMap.put(kundenTyp.getTyp(), preis);
    }
}
