package preis;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Patrick Metz
 */
class PreisFactoryTest {

    @ParameterizedTest
    @ValueSource(strings = {"Familie", "Frau", "Standard", "PersonMitBehinderung", "Mitarbeiter"})
    @DisplayName("Für bekannte Kundengruppen sollen Preise erzeugt werden können.")
    void bekannte_kundengruppen_erzeugen_preise(String kundenGruppe) {
        assertDoesNotThrow(() -> {
            PreisFactory.erzeugePreis(kundenGruppe, 1.0f);
        });
    }

    @Test
    @DisplayName("Für unbekannte Kundengruppen sollen keine Preise erzeugt werden können.")
    void unbekannte_kundengruppen_erzeugen_keine_preise() {
        assertThrows(Exception.class, () -> {
            PreisFactory.erzeugePreis("armer Student", 1.0f);
        });
    }

    @Test
    @DisplayName("Negative Preise sollen nicht erzeugt werden können.")
    void negative_betraege_erzeugen_keine_preise() {
        assertThrows(Exception.class, () -> {
            PreisFactory.erzeugePreis("Standard", -1.0f);
        });
    }
}