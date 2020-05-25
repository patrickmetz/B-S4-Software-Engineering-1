import Exceptions.NichtBezahltException;
import Exceptions.ParkhausLeerException;
import Exceptions.ParkhausVollException;
import kunde.KundeIF;
import kunde.KundenDatenIF;

import java.util.HashMap;
import java.util.List;

public class Parkhaus implements ParkhausIF {

    private final int plaetze = 100;
    private int plaetzeFrei;
    private HashMap<String, ParkticketIF> parktickets;

    private ParkhausStatistics parkhausStatistics;

    Parkhaus() {
        plaetzeFrei = plaetze;
        parktickets = new HashMap<>();
        parkhausStatistics = new ParkhausStatistics();
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

        parkhausStatistics.addEinahme(kunde.getPreis());
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
        return parkhausStatistics.getEinnahmen();
    }

    @Override
    public List<Integer> getParkdauerZeiten() {
        return parkhausStatistics.getParkdauerZeiten();
    }


}
