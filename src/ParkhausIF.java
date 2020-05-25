import kunde.KundeIF;
import kunde.KundenDatenIF;

import java.util.HashMap;
import java.util.List;

public interface ParkhausIF {

    ParkticketIF einfahren(KundeIF kunde);

    boolean ausfahren(ParkticketIF parkTicket, KundenDatenIF kundenDaten);

    void addParkticket(String hash, ParkticketIF parkTicket);

    ParkticketIF getParkticket(String hash);
    HashMap<String, ParkticketIF> getAllParktickets();

    List<Float> getEinahmen();
    List<Integer> getParkdauerZeiten();
}
