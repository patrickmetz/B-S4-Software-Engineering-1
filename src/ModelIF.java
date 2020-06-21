import java.util.List;

public interface ModelIF {
    public void abmelden(ViewIF viewIF);

    public void anmelden(ViewIF viewIF);

    public abstract List<Object> gibDaten();
}
