package Fahrzeuge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Tobias Lohmüller
 */

public enum FahrzeugTyp {

    PKW, Pickup, SUV, Zweirad, Trike, Quad;

    private static final List<FahrzeugTyp> FAHRZEUG_TYPEN =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random RANDOM = new Random();

    public static FahrzeugTyp randomFahrzeugTyp()  {
        return FAHRZEUG_TYPEN.get(RANDOM.nextInt(FAHRZEUG_TYPEN.size()));
    }

}
