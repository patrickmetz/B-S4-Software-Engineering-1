package Einzelaufgaben.a73;/*
 * Johannes Kratzsch
 * Version 1.0
 */

public class Auto {
    boolean turbo;
    Kunde kunde;

    Auto (boolean turb, Kunde k) {
        turbo = turb;
        kunde = k;
    }

    public boolean hatTurbo() {
        return turbo;
    }

    public Kunde kunde() {
        return kunde;
    }

    public int parkdauer () {
        return 9000;
    }
}
