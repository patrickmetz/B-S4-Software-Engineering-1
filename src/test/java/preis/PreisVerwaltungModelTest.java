package preis;

import kunde.KundenTyp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Patrick Metz
 */
class PreisVerwaltungModelTest {

    @Test
    @DisplayName("Nach der Instanziierung sollen für alle Kundentypen initiale Preise gesetzt sein.")
    public void PreisInitialisierungTest() {
        PreisVerwaltungModel model = new PreisVerwaltungModel(KundenTyp.values());

        for (KundenTyp kundenTyp : KundenTyp.values()) {
            assertNotNull(model.getPreis(kundenTyp));
        }
    }

    @Test
    @DisplayName("Preise sollen korrekt per String-Hashmap gesetzt werden.")
    public void PreisSetzen() {
        PreisVerwaltungModel model = new PreisVerwaltungModel(KundenTyp.values());

        for (KundenTyp kundenTyp : KundenTyp.values()) {
            float neuerPreis = 1.23f;

            HashMap<String, String> map = new HashMap<>();
            map.put(kundenTyp.toString(), neuerPreis + "");

            model.setPreiseAlsStringMap(map);

            assertEquals(model.getPreis(kundenTyp).getBetrag(), neuerPreis);
        }
    }
}