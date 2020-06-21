package mvc;

import java.util.List;

public abstract class AbstractModel implements ModelIF{
    private List<ViewIF> views;

    public void anmelden(ViewIF view) {
        views.add(view);
    }

    public void aktualisieren() {
        for (ViewIF view : views) {
            view.aktualisieren();
        }
    }
}
