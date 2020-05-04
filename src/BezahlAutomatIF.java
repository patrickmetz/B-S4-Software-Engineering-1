public interface BezahlAutomatIF {

    boolean bezahlen(ParkticketIF parkTicket, UhrzeitIF zeit);

    Double getPreis(ParkticketIF parkTicket, UhrzeitIF zeit);
}
