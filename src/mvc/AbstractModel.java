package mvc;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractModel {
    private List<AbstractView> views = new LinkedList<>();

    public void anmelden(AbstractView view) {
        views.add(view);
    }

    public void aktualisieren() {
        for (AbstractView view : views) {
            view.aktualisieren();
        }
    }
}
