package Fahrzeuge;

import preis.PreisFahrzeugGebuehr;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tobias Lohmüller
 */

public class MultitonFahrzeugTyp {

    private PreisFahrzeugGebuehr preis;
    private float parkflaeche;

    private final static Map<FahrzeugTyp, MultitonFahrzeugTyp> INSTANZEN = new HashMap<FahrzeugTyp, MultitonFahrzeugTyp>(){{
        put(FahrzeugTyp.PKW, new MultitonFahrzeugTyp(7.1f, new PreisFahrzeugGebuehr(2f, "PKW")));
        put(FahrzeugTyp.Pickup, new MultitonFahrzeugTyp(9.7f, new PreisFahrzeugGebuehr(3f, "Pickup")));
        put(FahrzeugTyp.Quad, new MultitonFahrzeugTyp(1.9f, new PreisFahrzeugGebuehr(1.5f, "Quad")));
        put(FahrzeugTyp.SUV, new MultitonFahrzeugTyp(9f, new PreisFahrzeugGebuehr(3f, "SUV")));
        put(FahrzeugTyp.Trike, new MultitonFahrzeugTyp(6.5f, new PreisFahrzeugGebuehr(1.25f, "Trike")));
        put(FahrzeugTyp.Zweirad, new MultitonFahrzeugTyp(3.6f, new PreisFahrzeugGebuehr(1f, "Zweirad")));

    }};

    public MultitonFahrzeugTyp(float quadratmeter, PreisFahrzeugGebuehr fahrzeugGebuehr) {
        this.parkflaeche = quadratmeter;
        this.preis = fahrzeugGebuehr;
    }

    public static MultitonFahrzeugTyp getInstanz(FahrzeugTyp key) {
        return INSTANZEN.get(key);
    }

    public PreisFahrzeugGebuehr getPreis() {
        return preis;
    }

    public float getParkflaeche() {
        return parkflaeche;
    }
}
