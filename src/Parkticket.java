import kunde.KundeIF;

class Parkticket implements ParkticketIF {

    private boolean bezahlt = false;
    private float preisStunde = 5.00f;
    private KundeIF kunde;

    Parkticket (KundeIF k) {
        kunde = k;
    }

    @Override
    public void setBezahlt(boolean b) {
        bezahlt = b;
    }

    @Override
    public boolean isBezahlt() {
        return bezahlt;
    }

    @Override
    public float getStundenPreis() {
        return preisStunde;
    }

    @Override
    public KundeIF getKunde() {
        return kunde;
    }
}
