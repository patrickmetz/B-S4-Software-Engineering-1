package Einzelaufgaben.a86;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ServiceIFTest {
    ServiceIF service;
    Farbe farbe = new Farbe();
    static int ZENTIMETER = 70;
    static int MILLIMETER = 700;

    public static ServiceIF[] services() {
        Altsystem as = new Altsystem();
        Function<Integer, Integer> map = zentimeter -> zentimeter * 10;

        AltsystemDynamicFunctionalAdapter adap = new AltsystemDynamicFunctionalAdapter(as);
        adap.setMapper(map);

        return new ServiceIF[]{
                new AltsystemClassAdapter(),
                new AltsystemObjectAdapter(as),
                new AltsystemStaticFunctionalAdapter(as, map),
                adap
        };
    }

    @ParameterizedTest
    @MethodSource("services")
    void saveLinie_berechnetDenRichtigenWert (ServiceIF service) {
        assertEquals(MILLIMETER, service.saveLinie(farbe, ZENTIMETER));
    }
}