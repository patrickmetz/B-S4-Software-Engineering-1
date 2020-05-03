public interface ParkhausIF {

    ParkticketIF einfahren(KundeIF kunde);

    boolean ausfahren(KundeIF kunde, ParkticketIF parkTicket);
}
