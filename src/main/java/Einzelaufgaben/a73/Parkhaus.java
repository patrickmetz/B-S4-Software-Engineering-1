package Einzelaufgaben.a73;/*
 * Johannes Kratzsch
 * Version 1.0
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Parkhaus {
    private static int gebuehr() {
        return 1;
    }

    public static void main(String[] args) {
        List<Auto> autos = Arrays.asList(
                new Auto(false, new Kunde(true)),
                new Auto(true, new Kunde(true)),
                new Auto(true, new Kunde(true)),
                new Auto(false, new Kunde(true))
        );

        // Die Summe der Produkte aus Auto-Parkdauer und ParkHaus-Gebühr aller Autos mit Turbo, deren Kunde eine Firma ist:
        int summe = autos.stream()
                .filter(auto -> auto.turbo && auto.kunde().istFirma())
                .map(auto -> auto.parkdauer() * gebuehr())
                .reduce(0, Integer::sum); // Alternative: .reduce(0, (x, y) -> x + y)

        System.out.println(summe);


        // Die Liste aller Firmen, deren Autos länger als ein Tag parken, wenn parkdauer() die Minuten liefert:
        List<Kunde> liste = autos.stream()
                .filter(auto -> auto.parkdauer() > 60 * 24)
                .map(Auto::kunde) // alternativ: .map(auto -> auto.kunde())
                .filter(Kunde::istFirma) //alternativ: .filter(kunde -> kunde.istFirma())
                .collect(Collectors.toList());


        System.out.println(liste);

    }

}
