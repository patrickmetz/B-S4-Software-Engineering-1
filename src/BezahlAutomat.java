public class BezahlAutomat implements BezahlAutomatIF {

    @Override
    public boolean bezahlen(ParkticketIF parkTicket, UhrzeitIF zeit) {
        parkTicket.setBezahlt(true);
        return true;
    }

    @Override
    public float getPreis(ParkticketIF parkTicket, UhrzeitIF zeit) {
        return parkTicket.getStundenPreis() * zeit.getStunden();
    }
}
