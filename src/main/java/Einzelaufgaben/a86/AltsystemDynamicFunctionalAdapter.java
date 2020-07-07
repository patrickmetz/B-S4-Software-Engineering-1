package Einzelaufgaben.a86;

import java.util.function.Function;

public class AltsystemDynamicFunctionalAdapter implements ServiceIF {
    private Function<Integer, Integer> mapper;
    Altsystem system;

    AltsystemDynamicFunctionalAdapter(Altsystem sys) {
        system = sys;
    }

    public void setMapper(Function<Integer, Integer> m) {
        mapper = m;
    }

    @Override
    public int saveLinie(Farbe f, int wert) {
        if (mapper == null) {
            throw new RuntimeException("Mapper fehlt");
        }

        int millimeter = mapper.apply(wert);
        return system.saveLinie(f, millimeter);
    }
}
