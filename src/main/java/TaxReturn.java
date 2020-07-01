import Einnahmen.Einnahme;
import Einnahmen.MonatsEinnahmenCalculator;

import javax.json.Json;
import javax.json.JsonObject;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Johannes Kratzsch
 */
public class TaxReturn {
    private static final float exemption = 2000.0f; //Steuerlicher Freibetrag
    private final List<Command> commands = new LinkedList<>();

    public static final List<String> keys = Arrays.asList(new String[]{});

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for(Command c: commands) {
            c.run();
        }


    }

    public static void taxReturnCommand(ParkhausStatistics statistics) {
        Finanzamt finanzamt = Finanzamt.getInstance();

        List<Einnahme> einnahmen = statistics.getEinnahmen();
        MonatsEinnahmenCalculator calc = new MonatsEinnahmenCalculator(einnahmen);
        float sum = calc.getSumEinnahmen();
        float net = Finanzamt.getNetFromGross(sum);
        float tax = Finanzamt.getTaxFromGross(sum);

        JsonObject taxReturn = Json.createObjectBuilder()
                .add("Steuererklärung", Json.createObjectBuilder()
                        .add("Summe der Monatseinnahmen (brutto)", KundenDatenUtils.floatToEuro(sum))
                        .add("Summe der Monatseinnahmen (netto)", KundenDatenUtils.floatToEuro(net))
                        .add("Summe der Steuern", KundenDatenUtils.floatToEuro(tax))
                        .add("Monatlicher Freibetrag", KundenDatenUtils.floatToEuro(exemption))
                ).build();

        finanzamt.sendTaxReturn(taxReturn);

    }
}
