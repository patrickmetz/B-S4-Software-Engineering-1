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

    @Override
    public float getPreis(ParkticketIF parkTicket) {
        if (parkTicket.getKunde().getDauer() < 0) {
            throw new RuntimeException("Eine negative Parkdauer gibt es nicht");
        }

        return parkTicket.getStundenPreis() * parkTicket.getKunde().getDauer();
    }

}
