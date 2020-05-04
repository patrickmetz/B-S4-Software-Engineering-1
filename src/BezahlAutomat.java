public class BezahlAutomat implements BezahlAutomatIF {

    @Override
    public boolean bezahlen(ParkticketIF parkTicket) {
        parkTicket.setBezahlt(true);
        return true;
    }

    @Override
    public float getPreis(ParkticketIF parkTicket) {
        return parkTicket.getStundenPreis();
    }
}
