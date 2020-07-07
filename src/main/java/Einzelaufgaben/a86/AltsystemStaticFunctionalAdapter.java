package Einzelaufgaben.a86;

import java.util.function.Function;

public class AltsystemStaticFunctionalAdapter implements ServiceIF {
    private final Function<Integer, Integer> mapper;
    Altsystem system;

    AltsystemStaticFunctionalAdapter(Altsystem sys, Function<Integer, Integer> m) {
        system = sys;
        mapper = m;
    }

    @Override
    public int saveLinie(Farbe f, int wert) {
        int millimeter = mapper.apply(wert);
        return system.saveLinie(f, millimeter);
    }
}
