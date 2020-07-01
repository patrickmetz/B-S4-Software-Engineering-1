package preis;

import kunde.KundenTyp;
import kunde.KundenTypIF;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Patrick Metz
 */
public class PreisVerwaltungController implements PreisVerwaltungControllerIF {
    PreisVerwaltungModel model;
    PreisVerwaltungView view;

    public PreisVerwaltungController(KundenTyp[] kundenTypen) {
        model = new PreisVerwaltungModel(kundenTypen);
        view = new PreisVerwaltungView(model);
    }

    @Override
    public PreisIF getPreis(KundenTypIF kundenTyp) {
        return model.getPreis(kundenTyp);
    }

    @Override
    public void setPreis(KundenTypIF kundenTyp, float betrag) {
        model.setPreis(kundenTyp, betrag);
    }

    @Override
    public void setPreiseAlsStringMap(HashMap<String, String> stringMap) {
        this.model.setPreiseAlsStringMap(stringMap);
    }

    @Override
    public PreisVerwaltungView getView() {
        return view;
    }
}
