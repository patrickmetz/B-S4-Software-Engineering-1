import java.time.LocalTime;

class Parkticket implements ParkticketIF {

    private boolean bezahlt = false;
    private float preisStunde = 5.00f;

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
