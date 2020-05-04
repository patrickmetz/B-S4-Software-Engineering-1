public class BezahlAutomat implements BezahlAutomatIF {

    @Override
    public boolean bezahlen(ParkticketIF parkTicket) {
        return false;
    }

    @Override
    public Double getStundenPreis(ParkticketIF parkTicket) {
        return null;
    }
}
