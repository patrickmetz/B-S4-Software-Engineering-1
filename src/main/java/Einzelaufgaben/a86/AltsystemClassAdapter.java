package Einzelaufgaben.a86;

public class AltsystemClassAdapter extends Altsystem implements ServiceIF {
    @Override
    public int saveLinie(Farbe f, int zentimeter) {
        return super.saveLinie(f, zentimeter * 10);
    }
}
