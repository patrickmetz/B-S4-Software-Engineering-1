import Exceptions.NichtBezahltException;
import Exceptions.ParkhausLeerException;
import Exceptions.ParkhausVollException;

import java.util.HashMap;

public class Parkhaus implements ParkhausIF {

    private final int plaetze = 100;
    private int plaetzeFrei;
    private HashMap<String, ParkticketIF> parktickets;

    Parkhaus() {
        plaetzeFrei = plaetze;
        parktickets = new HashMap<>();
    }

    @Override
    public ParkticketIF einfahren(KundeIF kunde, UhrzeitIF zeit) {

        if(plaetzeFrei == 0) throw new ParkhausVollException();
        plaetzeFrei--;

        return new Parkticket(kunde);
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

    @Override
    public void addParkticket(String hash, ParkticketIF parkTicket) {
        parktickets.put(hash, parkTicket);
    }

    @Override
    public ParkticketIF getParkticket(String hash) {
        return parktickets.get(hash);
    }
}
