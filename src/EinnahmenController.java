import Einnahmen.*;

import java.util.List;

/**
 * @author Johannes Kratzsch
 */
public class EinnahmenController {
    public void jahresEinnahmen (ParkhausStatistics statistics) {
        //Subscribe the view to the ParkhausStatistics model
        JahresEinnahmenView einnahmenView = new JahresEinnahmenView(statistics);
    }
}
