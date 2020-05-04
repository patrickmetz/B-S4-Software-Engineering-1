import Exceptions.NichtBezahltException;
import Exceptions.ParkhausLeerException;
import Exceptions.ParkhausVollException;

public class Parkhaus implements ParkhausIF {

    private final int plaetze = 100;
    private int plaetzeFrei;

    Parkhaus() {
        plaetzeFrei = plaetze;
    }

    @Override
    public ParkticketIF einfahren(KundeIF kunde) {

        if(plaetzeFrei == 0) throw new ParkhausVollException();
        plaetzeFrei--;

        return new Parkticket();
    }

    @Override
    public boolean ausfahren(KundeIF kunde, ParkticketIF parkTicket) {
        if(plaetzeFrei == 100) {
            throw new ParkhausLeerException();
        }

        if(!parkTicket.getBezahlt()) {
            throw new NichtBezahltException();
        }

        plaetzeFrei++;

        return true;
    }
}
