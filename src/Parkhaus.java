import Exceptions.NichtBezahltException;
import Exceptions.ParkhausLeerException;
import Exceptions.ParkhausVollException;
import kunde.KundeIF;
import kunde.KundenDatenIF;
import kunde.KundenTyp;
import preis.PreisVerwaltungController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Team
 */
public class Parkhaus implements ParkhausIF {

    private final int plaetze = 100;
    private int plaetzeFrei;
    private HashMap<String, ParkticketIF> parktickets;
    private final BezahlAutomat bezahlAutomat;

    private ParkhausStatistics parkhausStatistics;

    Parkhaus() {
        plaetzeFrei = plaetze;
        parktickets = new HashMap<>();
        parkhausStatistics = new ParkhausStatistics();
        bezahlAutomat = new BezahlAutomat(new PreisVerwaltungController(KundenTyp.values()));
    }

    @Override
    public ParkticketIF einfahren(KundeIF kunde) {

        if(plaetzeFrei == 0) throw new ParkhausVollException();
        plaetzeFrei--;

        return new Parkticket(kunde);
    }

    @Override
    public boolean ausfahren(ParkticketIF parkTicket, KundenDatenIF kundenDaten) {
        KundeIF kunde = parkTicket.getKunde();
        kunde.updateDaten(kundenDaten);

        if(plaetzeFrei == plaetze) {
            throw new ParkhausLeerException();
        }

        if(!parkTicket.isBezahlt()) {
            throw new NichtBezahltException();
        }

        parkhausStatistics.addEinahme(bezahlAutomat.getPreis(parkTicket));
        parkhausStatistics.addParkdauer(kunde.getDauer());

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

    public HashMap<String, ParkticketIF> getAllParktickets() {
        return parktickets;
    }

    @Override
    public List<Float> getEinahmen() {
        return parkhausStatistics.getEinnahmenWerte();
    }

    @Override
    public List<Integer> getParkdauerZeiten() {
        return parkhausStatistics.getParkdauerZeiten();
    }

    public ParkhausStatistics getParkhausStatistics() {
        return parkhausStatistics;
    }

    public BezahlAutomat getBezahlAutomat() {
        return bezahlAutomat;
    }
}
