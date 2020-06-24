import Einnahmen.*;

import java.util.List;

/**
 * @author Johannes Kratzsch
 */
public class EinnahmenController {
    private JahresEinnahmenView einnahmenView;

    public void jahresEinnahmen (ParkhausStatistics statistics) {
        //Subscribe the view to the ParkhausStatistics model
        einnahmenView = new JahresEinnahmenView(statistics);
    }

    public JahresEinnahmenView getView() {
        return einnahmenView;
    }
}
