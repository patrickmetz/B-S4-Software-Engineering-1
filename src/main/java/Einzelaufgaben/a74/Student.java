package Einzelaufgaben.a74;/*
 * Johannes Kratzsch
 * Version 1.0
 */

import java.util.LinkedList;
import java.util.List;

public class Student {
    boolean isBerufstaetig;

    List<Kurs> kurse = new LinkedList<>();

    public Student (boolean b, Kurs[] kurse) {
        isBerufstaetig = b;

        for (Kurs k: kurse) {
            addKurs(k);
        }
    }

    public void addKurs(Kurs k) {
        kurse.add(k);
    }
}
