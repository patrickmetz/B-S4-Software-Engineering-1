import Fahrzeuge.FahrzeugTyp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FahrzeugTypTest {

    @Test
    public void testRandomFahrzeugTyp() {
        assertTrue(Arrays.asList(FahrzeugTyp.values()).contains(FahrzeugTyp.randomFahrzeugTyp()));
    }

}
