import Einnahmen.Einnahme;
import mvc.AbstractModel;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tobias Lohmüller
 */
public class ParkhausStatistics extends AbstractModel {

    private List<Einnahme> einnahmen = new LinkedList<>();
    private List<Integer> parkdauerZeiten = new LinkedList<>();

    public void addEinahme(float einnahme) {
        Date currentTime = Calendar.getInstance().getTime();
        einnahmen.add(new Einnahme(currentTime, einnahme));
        aktualisieren();
    }

    public void addParkdauer(int parkdauer) {
        parkdauerZeiten.add(parkdauer);
        aktualisieren();

    }

    public List<Integer> getParkdauerZeiten() {
        return parkdauerZeiten;
    }

    public List<Float> getEinnahmenWerte() {
        return einnahmen.stream().map(Einnahme::getWert).collect(Collectors.toList());
    }

    public List<Einnahme> getEinnahmen() {
        return einnahmen;
    }


}
