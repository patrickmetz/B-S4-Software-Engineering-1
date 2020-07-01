import Einnahmen.Einnahme;
import Einnahmen.TagesEinnahmenCalculator;
import mvc.AbstractModel;
import mvc.AbstractView;

import java.util.List;

/**
 * @author Johannes Kratzsch
 */
public class TagesEinnahmenView extends AbstractView {
    private String view = "";

    public TagesEinnahmenView(AbstractModel model) {
        super(model);
        aktualisieren();
    }

    @Override
    public void aktualisieren() {
        ParkhausStatistics statistics = (ParkhausStatistics)model;
        List<Einnahme> einnahmen = statistics.getEinnahmen();
        TagesEinnahmenCalculator calc = new TagesEinnahmenCalculator(einnahmen);

        view = KundenDatenUtils.floatToEuro(calc.getSumEinnahmen());
    }

    @Override
    public String view() {
        return view;
    }
}
