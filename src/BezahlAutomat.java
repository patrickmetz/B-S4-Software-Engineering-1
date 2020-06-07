import java.util.Calendar;
import java.util.Date;

/**
 * @author Tobias Lohmüller
 */
public class BezahlAutomat implements BezahlAutomatIF {

    @Override
    public boolean bezahlen(ParkticketIF parkTicket) {
        parkTicket.setBezahlt(true);
        return true;
    }

}
