public interface BezahlAutomatIF {

    boolean bezahlen(ParkticketIF parkTicket);

    float getPreis(ParkticketIF parkTicket);
}
