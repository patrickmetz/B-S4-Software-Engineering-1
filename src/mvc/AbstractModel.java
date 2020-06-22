package mvc;

import java.util.List;

public abstract class AbstractModel {
    private List<AbstractView> views;

    public void anmelden(AbstractView view) {
        views.add(view);
    }

    public void aktualisieren() {
        for (AbstractView view : views) {
            view.aktualisieren();
        }
    }
}
