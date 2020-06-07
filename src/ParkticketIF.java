import kunde.KundeIF;

/**
 * @author Patrick Metz
 * @author Johannes Kratzsch
 */
public interface ParkticketIF {

    void setBezahlt(boolean b);

    boolean isBezahlt();

    float getStundenPreis();

    KundeIF getKunde();
}
