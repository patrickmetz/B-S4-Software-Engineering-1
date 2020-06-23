package mvc;

public abstract class AbstractView {

    private final AbstractModel model;

    public AbstractView(AbstractModel model) {
        this.model = model;
        model.anmelden(this);
        aktualisieren();
    }

    public abstract void aktualisieren();

    public abstract String view();
}
