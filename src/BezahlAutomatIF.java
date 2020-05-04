public interface BezahlAutomatIF {

    boolean bezahlen(ParkticketIF parkTicket);

    Double getStundenPreis(ParkticketIF parkTicket);
}
