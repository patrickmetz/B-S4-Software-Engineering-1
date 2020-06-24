package preis;

import kunde.KundenTyp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * @author Patrick Metz
 */
public class PreisVerwaltungMVCTest {
    @Test
    @DisplayName("Der View soll sich, nach Änderung eines Preises im Model, ebenfalls ändern.")
    public void PreisInitialisierungTest() {

        // wir testen absichtlich keine getter und setter
        // https://stackoverflow.com/questions/6197370/should-unit-tests-be-written-for-getter-and-setters

        PreisVerwaltungControllerIF controller = new PreisVerwaltungController(KundenTyp.values());
        String vorher = controller.getView().view();

        controller.setPreis(KundenTyp.Standard, 1.0f);

        assertNotEquals(vorher, controller.getView().view());
    }
}
