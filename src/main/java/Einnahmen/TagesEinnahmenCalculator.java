package Einnahmen;

import java.util.*;

/**
 * @author Tobias Lohmüller
 */

public class TagesEinnahmenCalculator extends EinnahmenCalculator {

    public TagesEinnahmenCalculator(List<Einnahme> einnahmen) {
        super(einnahmen);
    }

    @Override
    boolean sumTimeDedicator(Calendar currentDate, Calendar lastDate) {
        return currentDate.get(Calendar.DAY_OF_YEAR) != lastDate.get(Calendar.DAY_OF_YEAR);
    }
}
