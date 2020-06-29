import kunde.KundeIF;

/**
 * @author Johannes Kratzsch
 */
public interface ParkticketIF {

    void setBezahlt(boolean b);

    boolean isBezahlt();

    KundeIF getKunde();
}
