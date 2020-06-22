import Einnahmen.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tobias Lohmüller
 */

public class EinnahmenTest {

    ArrayList<Einnahme> werte;
    EinnahmenCalculator einnahmen;

    @BeforeEach
    public void setUp() {
        werte = new ArrayList<>();

        werte.add(new Einnahme(new GregorianCalendar(2019, Calendar.FEBRUARY, 9).getTime(), 4f));

        werte.add(new Einnahme(new GregorianCalendar(2020, Calendar.JANUARY, 31).getTime(), 3f));

        werte.add(new Einnahme(new GregorianCalendar(2020, Calendar.FEBRUARY, 1).getTime(), 9f));

        werte.add(new Einnahme(new GregorianCalendar(2020, Calendar.FEBRUARY, 6).getTime(), 2f));
        werte.add(new Einnahme(new GregorianCalendar(2020, Calendar.FEBRUARY, 6).getTime(), 1f));
        werte.add(new Einnahme(new GregorianCalendar(2020, Calendar.FEBRUARY, 6).getTime(), 6f));

        werte.add(new Einnahme(new GregorianCalendar(2020, Calendar.FEBRUARY, 22).getTime(), 10f));

    }

    @Test
    public void testTagesEinnahmen() {
        einnahmen = new TagesEinnahmenCalculator(werte);

        Iterator<Einnahme> iterator = einnahmen.einnahmenIterator();

        Einnahme example1 = iterator.next();
        assertEquals(new GregorianCalendar(2019, Calendar.FEBRUARY, 9).getTime(), example1.getDate());
        assertEquals(4, example1.getWert());

        iterator.next();
        iterator.next();

        Einnahme example2 = iterator.next();
        assertEquals(new GregorianCalendar(2020, Calendar.FEBRUARY, 6).getTime(), example2.getDate());
        assertEquals(9, example2.getWert());
    }

    @Test
    public void testWochenEinnahmen() {
        einnahmen = new WochenEinnahmenCalculator(werte);

        Iterator<Einnahme> iterator = einnahmen.einnahmenIterator();

        Einnahme example1 = iterator.next();
        assertEquals(new GregorianCalendar(2019, Calendar.FEBRUARY, 9).getTime(), example1.getDate());
        assertEquals(4, example1.getWert());

        Einnahme example2 = iterator.next();
        assertEquals(new GregorianCalendar(2020, Calendar.FEBRUARY, 1).getTime(), example2.getDate());
        assertEquals(12, example2.getWert());

        Einnahme example3 = iterator.next();
        assertEquals(new GregorianCalendar(2020, Calendar.FEBRUARY, 6).getTime(), example3.getDate());
        assertEquals(9, example3.getWert());
    }

    @Test
    public void testMonatsEinnahmen() {
        einnahmen = new MonatsEinnahmenCalculator(werte);

        Iterator<Einnahme> iterator = einnahmen.einnahmenIterator();

        Einnahme example1 = iterator.next();
        assertEquals(new GregorianCalendar(2019, Calendar.FEBRUARY, 9).getTime(), example1.getDate());
        assertEquals(4, example1.getWert());

        Einnahme example2 = iterator.next();
        assertEquals(new GregorianCalendar(2020, Calendar.JANUARY, 31).getTime(), example2.getDate());
        assertEquals(3, example2.getWert());

        Einnahme example3 = iterator.next();
        assertEquals(new GregorianCalendar(2020, Calendar.FEBRUARY, 22).getTime(), example3.getDate());
        assertEquals(28, example3.getWert());
    }

    @Test
    public void testJahresEinnahmen() {
        einnahmen = new JahresEinnahmenCalculator(werte);

        Iterator<Einnahme> iterator = einnahmen.einnahmenIterator();

        Einnahme example1 = iterator.next();
        assertEquals(new GregorianCalendar(2019, Calendar.FEBRUARY, 9).getTime(), example1.getDate());
        assertEquals(4, example1.getWert());

        Einnahme example2 = iterator.next();
        assertEquals(new GregorianCalendar(2020, Calendar.FEBRUARY, 22).getTime(), example2.getDate());
        assertEquals(31, example2.getWert());
    }

}
