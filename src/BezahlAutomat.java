public class BezahlAutomat implements BezahlAutomatIF {

    @Override
    public boolean bezahlen(ParkticketIF parkTicket, UhrzeitIF zeit) {
        return false;
    }

    @Override
    public Double getPreis(ParkticketIF parkTicket, UhrzeitIF zeit) {
        return null;
    }
}
