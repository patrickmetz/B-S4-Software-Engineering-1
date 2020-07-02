import Fahrzeuge.FahrzeugTyp;
import Fahrzeuge.MultitonFahrzeugTyp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import preis.PreisFahrzeugGebuehr;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Tobias Lohmüller
 */

public class MultitonFahrzeugTypTest {

    MultitonFahrzeugTyp multitonFahrzeugTyp;

    @BeforeEach
    public void setUp() {
        multitonFahrzeugTyp = MultitonFahrzeugTyp.getInstanz(FahrzeugTyp.PKW);
    }

    @Test
    @DisplayName("Die Übereinstimmung mit der Multitoninstanz")
    public void testInstanzierung() {
        assertEquals(MultitonFahrzeugTyp.getInstanz(FahrzeugTyp.PKW), multitonFahrzeugTyp);
        assertEquals(7.1f, multitonFahrzeugTyp.getParkflaeche());
        assertEquals(2f, multitonFahrzeugTyp.getPreis().getBetrag());
    }

}
