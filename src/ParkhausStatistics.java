import java.util.LinkedList;
import java.util.List;

public class ParkhausStatistics {

    //Still not good enough

    private List<Float> einnahmen = new LinkedList<>();
    private List<Integer> parkdauerZeiten = new LinkedList<>();

    public void addEinahme(float einnahme) {
        einnahmen.add(einnahme);
    }

    public void addParkdauer(int parkdauer) {
        parkdauerZeiten.add(parkdauer);
    }

    public List<Integer> getParkdauerZeiten() {
        return parkdauerZeiten;
    }

    public List<Float> getEinnahmen() {
        return einnahmen;
    }


}
