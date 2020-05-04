public class BezahlAutomat implements BezahlAutomatIF {

    @Override
    public boolean bezahlen(ParkticketIF parkTicket) {
        return false;
    }

    @Override
    public Double getPreis(ParkticketIF parkTicket) {
        return null;
    }
}
