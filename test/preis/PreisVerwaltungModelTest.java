package preis;

import kunde.KundenTyp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PreisVerwaltungModelTest {
    @Test
    @DisplayName("Nach der Instanziierung sollen für alle Kundentypen initiale Preise gesetzt sein.")
    public void PreisInitialisierungTest(){
        PreisVerwaltungModel model = new PreisVerwaltungModel(KundenTyp.values());

        for (KundenTyp kundenTyp : KundenTyp.values()) {
            assertNotNull(model.getPreis(kundenTyp));
        }
    }
}