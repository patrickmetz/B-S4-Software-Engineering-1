import kunde.KundeIF;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tobias Lohmüller
 */
public class ParkhausChartProcessor implements ParkhausChartProcessorIF {

    private Parkhaus parkhaus;

    public ParkhausChartProcessor(Parkhaus parkhaus) {
        this.parkhaus = parkhaus;
    }

    @Override
    public String getKundenPieChart() {
        JsonArrayBuilder kundenTypArray = Json.createArrayBuilder();
        JsonArrayBuilder kundenAufkommenArray = Json.createArrayBuilder();

        HashMap<String, Integer> kategorienAufkommen = new HashMap<>();

        for (ParkticketIF parkticket : parkhaus.getAllParktickets().values()) {
            KundeIF kunde = parkticket.getKunde();

            if(!kategorienAufkommen.containsKey(kunde.getKundenGruppe())) {
                kategorienAufkommen.put(kunde.getKundenGruppe(), 1);
                kundenTypArray.add(kunde.getKundenGruppe());
            } else {
                kategorienAufkommen.put(kunde.getKundenGruppe(),  kategorienAufkommen.get(kunde.getKundenGruppe())+1);
            }
        }

        //Labels hinzufügen
        for(Map.Entry<String, Integer> entry : kategorienAufkommen.entrySet()) {
            kundenAufkommenArray.add(entry.getValue());
        }

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

        for(ParkticketIF parkticket : parkhaus.getAllParktickets().values()) {
            KundeIF kunde = parkticket.getKunde();

            if(parkticket.isBezahlt()) {
                kundenNummern.add(kunde.getNr());
                kundenParkDauer.add(kunde.getDauer());
            }
        }

        JsonObject rootBar = Json.createObjectBuilder()
                .add("layout", Json.createObjectBuilder()
                    .add("title", "Parkdauer pro ausgefahrener Kunde")
                    .add("xaxis", Json.createObjectBuilder()
                        .add("title", "Kunden Nummer")
                        .add("tick0", 1)
                        .add("dtick", 1))
                    .add("yaxis", Json.createObjectBuilder()
                        .add("title", "Parkdauer in Sekunden"))
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