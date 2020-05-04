import java.time.LocalTime;

public class Parkticket implements ParkticketIF {

    private LocalTime checkIn;
    private boolean bezahlt = false;
    private float preisStunde = 5.00f;

    @Override
    public void setBezahlt(boolean b) {

    }

    @Override
    public boolean getBezahlt() {
        return false;
    }

    @Override
    public float getStundenPreis() {
        return 0;
    }
}
