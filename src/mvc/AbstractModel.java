package mvc;

import java.util.List;

public abstract class AbstractModel implements ModelIF {
    private List<ViewIF> views;

    @Override
    public void anmelden(ViewIF view) {
        views.add(view);
    }

    public void aktualisieren() {
        for (ViewIF view : views) {
            view.aktualisieren();
        }
    }

    @Override
    public abstract List<Object> gibDaten();

    @Override
    public abstract void speichereDaten(List<Object> daten);
}
