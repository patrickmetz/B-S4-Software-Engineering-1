import kunde.KundeIF;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tobias Lohmüller
 * @author Johannes Kratzsch
 */
public class ParkhausChartProcessor implements ParkhausChartProcessorIF {

    private ParkhausIF parkhaus;

    public ParkhausChartProcessor(ParkhausIF parkhaus) {
        this.parkhaus = parkhaus;
    }

    @Override
    public String getKundenPieChart() {
        JsonArrayBuilder kundenTypArray = Json.createArrayBuilder();
        JsonArrayBuilder kundenAufkommenArray = Json.createArrayBuilder();

        HashMap<String, Integer> kategorienAufkommen = new HashMap<>();

        parkhaus.getAllParktickets().values()
                .stream()
                .map(ParkticketIF::getKunde)
                .map(KundeIF::getKundenGruppe)
                .forEach(kundengruppe -> {
                    if(!kategorienAufkommen.containsKey(kundengruppe)) {
                        kategorienAufkommen.put(kundengruppe, 1);
                        kundenTypArray.add(kundengruppe);
                    } else {
                        kategorienAufkommen.put(kundengruppe,  kategorienAufkommen.get(kundengruppe)+1);
                    }
                });

        //Labels hinzufügen
        kategorienAufkommen.values()
                .forEach(kundenAufkommenArray::add);

        JsonObject rootPie = Json.createObjectBuilder()
                .add("layout", Json.createObjectBuilder()
                        .add("title", "Anteile der Kunden-Kategorien")
                )
                .add("data", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("values", kundenAufkommenArray)
                                .add("labels", kundenTypArray)
                                .add("type", "pie")

                        )
                ).build();


        return rootPie.toString();
    }

    @Override
    public String getKundenBarChart() {
        JsonArrayBuilder kundenNummern = Json.createArrayBuilder();
        JsonArrayBuilder kundenParkBeginn = Json.createArrayBuilder();
        JsonArrayBuilder kundenParkEnde = Json.createArrayBuilder();
        JsonArrayBuilder kundenParkDauer = Json.createArrayBuilder();

        parkhaus.getAllParktickets().values()
                .stream()
                .filter(ParkticketIF::isBezahlt)
                .map(ParkticketIF::getKunde)
                .forEach(kunde -> {
                    kundenNummern.add(kunde.getNr());
                    kundenParkDauer.add(kunde.getDauer());
                });

        JsonObject rootBar = Json.createObjectBuilder()
                .add("layout", Json.createObjectBuilder()
                    .add("title", "Parkdauer pro ausgefahrener Kunde")
                    .add("xaxis", Json.createObjectBuilder()
                        .add("title", "Kunden Nummer")
                        .add("tick0", 1)
                        .add("dtick", 1))
                    .add("yaxis", Json.createObjectBuilder()
                        .add("title", "Parkdauer in Millisekunden"))
                )
                .add("data", Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("x", kundenNummern)
                            .add("y", kundenParkDauer)
                            .add("type", "bar")
                            .add("name", "Parkdauer")
                    )
                ).build();

        System.out.println(rootBar.toString());

        return rootBar.toString();
    }
}
