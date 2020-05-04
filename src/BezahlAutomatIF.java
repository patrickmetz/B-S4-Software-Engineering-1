public interface BezahlAutomatIF {

    boolean bezahlen(ParkticketIF parkTicket);

    Double getPreis(ParkticketIF parkTicket);
}
