/**
 * @author Johannes Kratzsch
 */
public class EinnahmenController implements EinnahmenControllerIF {
    private JahresEinnahmenView jahresEinnahmenView;
    private TagesEinnahmenView tagesEinnahmenView;
    ParkhausStatistics statistics;

    EinnahmenController (ParkhausStatistics statistics) {
        this.statistics = statistics;
    }

    public JahresEinnahmenView getJahresEinnahmenView() {
        if (jahresEinnahmenView == null) {
            //Subscribe the view to the ParkhausStatistics model
            jahresEinnahmenView = new JahresEinnahmenView(statistics);
        }

        return jahresEinnahmenView;
    }

    public TagesEinnahmenView getTagesEinnahmenView() {
        if (tagesEinnahmenView == null) {
            //Subscribe the view to the ParkhausStatistics model
            tagesEinnahmenView = new TagesEinnahmenView(statistics);
        }

        return tagesEinnahmenView;
    }
}
