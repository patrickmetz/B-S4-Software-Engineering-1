package Einnahmen;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Tobias Lohmüller
 */

public class WochenEinnahmenCalculator extends EinnahmenCalculator {

    public WochenEinnahmenCalculator(List<Einnahme> einnahmen) {
        super(einnahmen);
    }

    @Override
    boolean sumTimeDedicator(Calendar currentDate, Calendar lastDate) {
        return currentDate.get(Calendar.WEEK_OF_YEAR) != lastDate.get(Calendar.WEEK_OF_YEAR);
    }

}
