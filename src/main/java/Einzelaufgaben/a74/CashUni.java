package Einzelaufgaben.a74;

import java.util.Arrays;
import java.util.List;

public class CashUni {
    List<Kurs> kurse;
    List<Student> studenten;

    CashUni (Kurs[] k, Student[] s) {
        kurse = Arrays.asList(k);
        studenten = Arrays.asList(s);
    }

    /**
     * soll die Summe aller Gebühren aller Kurse berechnen (ohne dabei Studierende und Kursbelegungen zu berücksichtigen,
     * d.h. jeden Kurs nur einmal):
     *
     * @return
     */
    public int sumKurse () {
        return kurse
                .stream()
                .map(kurs -> kurs.gebuehr)
                .reduce(0, (x,y) -> x + y); //alternativ: .reduce(0, Integer::sum)
    }

    /**
     * soll die Summe der Gebühren aller berufstätigen Studierenden berücksichtigen. Hinweis: Studierende können mehrere Kurse
     * belegen, deren Gebühren alle aufzusummieren sind. In der Summe kann die Gebühr eines Kurses mehrfach addiert werden,
     * wenn er von mehreren Studierenden belegt wurde.
     * @return
     */
    public int sumStudenten () {
        return studenten
                .stream()
                .map(student -> student.kurse)
                .map(kurse -> kurse.
                        stream()
                        .map(k -> k.gebuehr)
                        .reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    /**
     * soll die durchschnittliche Gebühr aller Zertifikatskurse mit mehr als 5 ECTS berechnen.
     * @return
     */
    public int avg() {
        return (int)kurse
                .stream()
                .filter(k -> k.hatZertifikat)
                .mapToInt(k -> k.gebuehr)
                .average()
                .getAsDouble();
    }

    public static void main(String[] args) {
        Kurs k1 = new Kurs(true, 1, 300);
        Kurs k2 = new Kurs(true, 1, 700);

        Student s1 = new Student(false, new Kurs[]{k1, k2});
        Student s2 = new Student(false, new Kurs[]{k1});


        CashUni cu = new CashUni(new Kurs[]{k1, k2}, new Student[]{s1, s2});

        System.out.println(cu.sumKurse()); // 1000
        System.out.println(cu.sumStudenten()); // 1300
        System.out.println(cu.avg()); // 500


    }
}
