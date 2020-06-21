package mvc;

import java.util.List;

public interface ModelIF {
    List<Object> gibDaten();

    void speichereDaten(List<Object> daten);
}
