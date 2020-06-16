import java.util.Date;

/**
 * @author Tobias Lohmüller
 */
public interface BezahlAutomatIF {

    boolean bezahlen(ParkticketIF parkTicket);

    float getPreis(ParkticketIF parkTicket);
}
