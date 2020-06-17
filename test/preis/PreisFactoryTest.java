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
                    kundenTyp.toString(),
                    kundenTyp.getInitialPreis()
            );

            assertNotNull(preis);
        }
    }

    @Test
    @DisplayName("Für unbekannte Kundengruppen sollen keine Preise erzeugt werden können.")
    void unbekannte_kundengruppen_erzeugen_keine_preise() {
        assertNull(
                PreisFactory.erzeugePreis("ArmerStudent", 1.0f)
        );
    }

    @Test
    @DisplayName("Negative Preise sollen nicht erzeugt werden können.")
    void negative_betraege_erzeugen_keine_preise() {
        assertNull(
                PreisFactory.erzeugePreis("Standard", -1.0f)
        );
    }
}