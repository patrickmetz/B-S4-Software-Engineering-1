package preis;

import mvc.AbstractModel;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author Patrick Metz
 */
public class PreisVerwaltungView extends mvc.AbstractView{
    public static final String JSON_PREIS_OBJEKT = "{\"typ\" : \"%s\", \"bezeichnung\" : \"%s\", \"betrag\" : %s}";
    public static final String JSON_PREIS_OBJEKT_ARRAY = "[%s]";

    String json;

    public PreisVerwaltungView(AbstractModel model) {
        super(model);
    }

    @Override
    public void aktualisieren() {
        PreisVerwaltungModel meinModel = (PreisVerwaltungModel) this.model;
        HashMap<String, PreisIF> preiseMap = meinModel.getPreiseMap();

        json = preiseMapZuJSONArray(preiseMap);
    }

    @Override
    public String view() {
        return json;
    }

    public String preiseMapZuJSONArray(HashMap<String, PreisIF> preiseMap) {
        String preise = preiseMap
                .entrySet()
                .stream()
                .map(
                        x -> String.format(
                                JSON_PREIS_OBJEKT,
                                x.getKey(),
                                x.getValue().getBezeichnung(),
                                x.getValue().getBetrag()
                        )
                )
                .collect(Collectors.joining(","));

        return String.format(JSON_PREIS_OBJEKT_ARRAY, preise);
    }
}
