import kunde.Kunde;
import kunde.KundenDaten;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Johannes Kratzsch
 */
class ParkhausChartProcessorIFTest {
    ParkhausChartProcessorIF chartProcessor;

    @BeforeEach
    void setUp() {
        Parkhaus parkhaus = new Parkhaus();
        BezahlAutomatIF automat = new BezahlAutomat();
        chartProcessor = new ParkhausChartProcessor(parkhaus);

        KundenDaten kd1 = new KundenDaten(new String[]{
                "123456",           //Nr
                "1590408991325",    //Beginn
                "20",               //Dauer
                "5000",             //Preis
                "jrjhekjdlfjdsfk",  //Tickethash
                "ff00ff",           //Farbe
                "7",                //Slot
                "Familie"           //Kundengruppe

        });

        KundenDaten kd2 = new KundenDaten(new String[]{
                "478349",           //Nr
                "1590408991325",    //Beginn
                "64",               //Dauer
                "383",              //Preis
                "kfjien",           //Tickethash
                "ff00ff",           //Farbe
                "7",                //Slot
                "PersonMitBehinderung" //Kundengruppe

        });
        Kunde k1 = new Kunde(kd1);
        Kunde k2 = new Kunde(kd1);

        ParkticketIF t1 = parkhaus.einfahren(k1);
        parkhaus.addParkticket("jrjhekjdlfjdsfk", t1);
        automat.bezahlen(t1);
        parkhaus.ausfahren(t1, kd1);

        ParkticketIF t2 = parkhaus.einfahren(k2);
        parkhaus.addParkticket("kfjien", t2);
        automat.bezahlen(t2);
        parkhaus.ausfahren(t2, kd2);
    }

    @Test
    void getKundenPieChart_returnsCorrectJson() {
        assertEquals("{\"layout\":{\"title\":\"Anteile der Kunden-Kategorien\"}," +
                        "\"data\":[{\"values\":[2],\"labels\":[\"Familie\"],\"type\":\"pie\"}]}",
                chartProcessor.getKundenPieChart());
    }

    @Test
    void getKundenBarChart_returnsCorrectJson() {
        assertEquals("{\"layout\":{\"title\":\"Parkdauer pro ausgefahrener Kunde\"," +
                        "\"xaxis\":{\"title\":\"Kunden Nummer\",\"tick0\":1,\"dtick\":1}," +
                        "\"yaxis\":{\"title\":\"Parkdauer in Sekunden\"}}," +
                        "\"data\":[{\"x\":[123456,123456],\"y\":[64,64],\"type\":\"bar\",\"name\":\"Parkdauer\"}]}",
                chartProcessor.getKundenBarChart());
    }
}