package preis;

import kunde.KundenTyp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Patrick Metz
 */
class PreisVerwaltungModelTest {
    @Test
    @DisplayName("Nach der Instanziierung sollen für alle Kundentypen initiale Preise gesetzt sein.")
    public void PreisInitialisierungTest(){

        // wir testen absichtlich keine getter und setter
        // https://stackoverflow.com/questions/6197370/should-unit-tests-be-written-for-getter-and-setters

        PreisVerwaltungModel model = new PreisVerwaltungModel(KundenTyp.values());

        for (KundenTyp kundenTyp : KundenTyp.values()) {
            assertNotNull(model.getPreis(kundenTyp));
        }
    }
}