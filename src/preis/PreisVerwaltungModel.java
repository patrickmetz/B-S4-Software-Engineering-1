package preis;

import kunde.KundenTyp;
import kunde.KundenTypIF;
import mvc.AbstractModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Patrick Metz
 */
public class PreisVerwaltungModel extends AbstractModel implements PreisVerwaltungIF {
    HashMap<String, PreisIF> preiseMap;

    PreisVerwaltungModel(KundenTyp[] kundenTypen) {
        preiseMap = new HashMap<>();
        initialisierePreise(kundenTypen);
    }

    HashMap<String, PreisIF> getPreiseMap() {
        return preiseMap;
    }

    @Override
    public PreisIF getPreis(KundenTypIF kundenTyp) {
        return preiseMap.get(kundenTyp.getTyp());
    }

    @Override
    public void setPreis(KundenTypIF kundenTyp, float betrag) {
        PreisIF preis = PreisFactory.erzeugePreis(kundenTyp, betrag);

        preiseMap.put(kundenTyp.getTyp(), preis);
        aktualisieren();
    }

    private void initialisierePreise(KundenTyp[] kundenTypen) {
        for (KundenTypIF kundenTyp : kundenTypen) {
            PreisIF preis = PreisFactory.erzeugePreis(
                    kundenTyp,
                    kundenTyp.getInitialPreis()
            );

            preiseMap.put(kundenTyp.getTyp(), preis);
        }

        aktualisieren();
    }

    @Override
    public void setPreiseAlsStringMap(HashMap<String, String> stringMap) {
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            String kundenTypString = entry.getKey();

            for (KundenTypIF kundenTyp : KundenTyp.values()) {
                if (kundenTyp.getTyp().equals(kundenTypString)) {

                    float betrag;
                    try {
                        betrag = Float.parseFloat(entry.getValue());
                    } catch (Exception e) {
                        betrag = kundenTyp.getInitialPreis();
                    }

                    this.setPreis(kundenTyp, betrag);
                }
            }
        }

        aktualisieren();
    }
}
