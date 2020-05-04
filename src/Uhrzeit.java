public class Uhrzeit implements UhrzeitIF {
    private int h;
    private int m;

    Uhrzeit(int h, int m) {
        this.h = h;
        this.m = m;
    }

    @Override
    public int getStunden() {
        return h;
    }

    @Override
    public int getMinuten() {
        return m;
    }

    @Override
    public void setStunden(int h) {
        this.h = h;
    }

    @Override
    public void setMinuten(int m) {
        this.m = m;
    }

    @Override
    public void addStunden(int ho) {
        h += ho;

        if (h%24 != 0) {
            h = h%24;
        }

        if (h < 0) {
            h = 24 + h;
        }


    }

    @Override
    public void addMinuten(int mi) {
        m += mi;

        int hours;
        if ((hours = m/60) != 0) {
            addStunden(hours);

            m = m%60;
        }

        if (m < 0) {
            addStunden(-1);

            m = 60 + m;
        }
    }
}
