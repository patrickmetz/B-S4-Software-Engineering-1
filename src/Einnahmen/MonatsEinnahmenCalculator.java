package Einnahmen;

import java.util.Calendar;
import java.util.List;

/**
 * @author Tobias Lohmüller
 */

public class MonatsEinnahmenCalculator extends EinnahmenCalculator {

    public MonatsEinnahmenCalculator(List<Einnahme> einnahmen) {
        super(einnahmen);
    }

    @Override
    boolean sumTimeDedicator(Calendar currentDate, Calendar lastDate) {
        return currentDate.get(Calendar.MONTH) != lastDate.get(Calendar.MONTH);
    }
}
