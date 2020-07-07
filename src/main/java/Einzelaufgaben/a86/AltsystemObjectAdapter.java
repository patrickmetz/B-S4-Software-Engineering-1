package Einzelaufgaben.a86;

public class AltsystemObjectAdapter implements ServiceIF {
    Altsystem as;

    AltsystemObjectAdapter(Altsystem a) {
        as = a;
    }

    @Override
    public int saveLinie(Farbe f, int zentimeter) {
        return as.saveLinie(f, zentimeter * 10);
    }
}
