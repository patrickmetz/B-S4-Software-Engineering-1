package Fahrzeuge;

import preis.PreisFahrzeugGebuehr;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author Tobias Lohmüller
 */

public class MultitonFahrzeugTyp {

    private PreisFahrzeugGebuehr preis;
    private float parkflaeche;

    private final static Map<FahrzeugTyp, MultitonFahrzeugTyp> INSTANZEN;

    static{
        Hashtable<FahrzeugTyp,MultitonFahrzeugTyp> tmp =
                new Hashtable<>();
        tmp.put(FahrzeugTyp.PKW, new MultitonFahrzeugTyp(7.1f, new PreisFahrzeugGebuehr(2f, "PKW")));
        tmp.put(FahrzeugTyp.Pickup, new MultitonFahrzeugTyp(9.7f, new PreisFahrzeugGebuehr(3f, "Pickup")));
        tmp.put(FahrzeugTyp.Quad, new MultitonFahrzeugTyp(1.9f, new PreisFahrzeugGebuehr(1.5f, "Quad")));
        tmp.put(FahrzeugTyp.SUV, new MultitonFahrzeugTyp(9f, new PreisFahrzeugGebuehr(3f, "SUV")));
        tmp.put(FahrzeugTyp.Trike, new MultitonFahrzeugTyp(6.5f, new PreisFahrzeugGebuehr(1.25f, "Trike")));
        tmp.put(FahrzeugTyp.Zweirad, new MultitonFahrzeugTyp(3.6f, new PreisFahrzeugGebuehr(1f, "Zweirad")));
        INSTANZEN = Collections.unmodifiableMap(tmp);
    }

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
