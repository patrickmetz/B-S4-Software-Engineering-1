import java.time.LocalTime;

public class Parkticket implements ParkticketIF {

    private LocalTime checkIn;
    private boolean bezahlt = false;
    private float preisStunde = 5.00f;

    @Override
    public void setBezahlt() {

    }

    @Override
    public float getPreis() {
        return 0;
    }
}
