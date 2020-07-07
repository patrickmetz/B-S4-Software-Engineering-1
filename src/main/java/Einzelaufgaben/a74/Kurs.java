package Einzelaufgaben.a74;/*
 * Johannes Kratzsch
 * Version 1.0
 */

public class Kurs {
    boolean hatZertifikat;
    int ECTS;

    public int gebuehr;

    public Kurs(boolean b, int i1, int i2) {
        hatZertifikat = b;
        ECTS = i1;
        gebuehr = i2;
    }
}
