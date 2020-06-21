package mvc;

import java.util.List;

public interface ModelIF {
    void anmelden(ViewIF viewIF);

    List<Object> gibDaten();

    void speichereDaten(List<Object> daten);
}
