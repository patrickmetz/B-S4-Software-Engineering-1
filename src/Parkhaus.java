public class Parkhaus implements ParkhausIF {

    private final int plaetze = 100;
    private int plaetzeFrei;

    @Override
    public ParkticketIF einfahren(KundeIF kunde, UhrzeitIF zeit) {
        return null;
    }

    @Override
    public boolean ausfahren(KundeIF kunde, ParkticketIF parkTicket) {
        return false;
    }
}
