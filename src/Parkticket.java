import java.time.LocalTime;

public class Parkticket implements ParkticketIF {

    private LocalTime checkIn;
    private boolean bezahlt = false;
    private float preisStunde = 5.00f;

    Parkticket() {
        checkIn = LocalTime.now();
    }

    @Override
    public void setBezahlt(boolean b) {
        bezahlt = b;
    }

    @Override
    public boolean getBezahlt() {
        return bezahlt;
    }

    @Override
    public float getStundenPreis() {
        return preisStunde;
    }
}
