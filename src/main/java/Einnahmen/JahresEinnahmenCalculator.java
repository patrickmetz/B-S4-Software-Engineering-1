package Einnahmen;

import java.util.Calendar;
import java.util.List;

/**
 * @author Tobias Lohmüller
 */
public class JahresEinnahmenCalculator extends EinnahmenCalculator {

    public JahresEinnahmenCalculator(List<Einnahme> einnahmen) {
        super(einnahmen);
    }

    @Override
    boolean sumTimeDedicator(Calendar currentDate, Calendar lastDate) {
        return currentDate.get(Calendar.YEAR) != lastDate.get(Calendar.YEAR);
    }

}
