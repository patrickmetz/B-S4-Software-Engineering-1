import kunde.KundeIF;
import kunde.KundenTyp;

/**
 * @author Johannes Kratzsch
 */
class Parkticket implements ParkticketIF {

    private boolean bezahlt = false;
    private final KundeIF kunde;

    Parkticket (KundeIF k) {
        kunde = k;
    }

    @Override
    public void setBezahlt(boolean b) {
        bezahlt = b;
    }

    @Override
    public boolean isBezahlt() {
        return bezahlt;
    }

    @Override
    public KundeIF getKunde() {
        return kunde;
    }
}
