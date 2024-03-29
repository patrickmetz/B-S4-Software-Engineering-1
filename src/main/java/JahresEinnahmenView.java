import Einnahmen.Einnahme;
import Einnahmen.JahresEinnahmenCalculator;
import mvc.AbstractModel;
import mvc.AbstractView;

import java.util.List;

/**
 * @author Johannes Kratzsch
 */
public class JahresEinnahmenView extends AbstractView {
    private String view = "";

    public JahresEinnahmenView(AbstractModel model) {
        super(model);
        aktualisieren();
    }

    @Override
    public void aktualisieren() {
        ParkhausStatistics statistics = (ParkhausStatistics)model;
        List<Einnahme> einnahmen = statistics.getEinnahmen();
        JahresEinnahmenCalculator calc = new JahresEinnahmenCalculator(einnahmen);

        view = KundenDatenUtils.floatToEuro(calc.getSumEinnahmen());
    }

    @Override
    public String view() {
        return view;
    }
}
