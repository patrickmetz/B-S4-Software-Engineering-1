package preis;

import kunde.KundenTyp;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Patrick Metz
 */
class PreisFactoryTest {

    @Test
    @DisplayName("Für bekannte Kundengruppen sollen Preise erzeugt werden können.")
    void bekannte_kundengruppen_erzeugen_preise() {

        for (KundenTyp kundenTyp : KundenTyp.values()) {
            PreisIF preis = PreisFactory.erzeugePreis(
                    kundenTyp,
                    kundenTyp.getInitialPreis()
            );

            assertNotNull(preis);
        }
    }

    @Test
    @DisplayName("Negative Preise sollen nicht erzeugt werden können.")
    void negative_betraege_erzeugen_keine_preise() {
        for (KundenTyp kundenTyp : KundenTyp.values()) {
            PreisIF preis = PreisFactory.erzeugePreis(
                    kundenTyp,
                    -1 * kundenTyp.getInitialPreis()
            );

            assertNull(preis);
        }
    }
}