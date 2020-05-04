public interface ParkhausIF {

    ParkticketIF einfahren(KundeIF kunde, UhrzeitIF zeit);

    boolean ausfahren(KundeIF kunde, ParkticketIF parkTicket);
}
