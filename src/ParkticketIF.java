import kunde.KundeIF;

public interface ParkticketIF {

    void setBezahlt(boolean b);

    boolean isBezahlt();

    float getStundenPreis();

    KundeIF getKunde();
}
