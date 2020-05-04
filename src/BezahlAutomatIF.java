public interface BezahlAutomatIF {

    boolean bezahlen(ParkticketIF parkTicket, UhrzeitIF zeit);

    float getPreis(ParkticketIF parkTicket, UhrzeitIF zeit);
}
