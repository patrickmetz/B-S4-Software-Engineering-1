package Einnahmen;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author Tobias Lohmüller
 */

public class TagesEinnahmenCalculator extends EinnahmenCalculator {

    public TagesEinnahmenCalculator(ArrayList<Einnahme> einnahmen) {
        super(einnahmen);
    }

    @Override
    boolean sumTimeDedicator(Calendar currentDate, Calendar lastDate) {
        return currentDate.get(Calendar.DAY_OF_YEAR) != lastDate.get(Calendar.DAY_OF_YEAR);
    }
}
