package preis;

import kunde.KundenTyp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreisVerwaltungControllerTest {
    @Test
    @DisplayName("Nach der Instanziierung sollen für alle Kundentypen initiale Preise gesetzt sein.")
    public void PreisInitialisierungTest(){
        PreisVerwaltungController controller = new PreisVerwaltungController(KundenTyp.values());

        for (KundenTyp kundenTyp : KundenTyp.values()) {
            assertNotNull(controller.getPreis(kundenTyp));
        }
    }
}