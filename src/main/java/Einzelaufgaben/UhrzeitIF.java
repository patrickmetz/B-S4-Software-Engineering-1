package Einzelaufgaben;

public interface UhrzeitIF {
    int getStunden();

    int getMinuten();

    void setStunden(int h);

    void setMinuten(int m);

    void addStunden(int h);

    void addMinuten(int m);
}