import PaymentProvider.CashPayment;
import kunde.Kunde;
import kunde.KundenDaten;
import kunde.KundenTyp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import preis.PreisVerwaltungController;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Johannes Kratzsch
 */
class BezahlAutomatIFTest {
    private BezahlAutomatIF automat;
    private ParkticketIF ticket;
    private Parkhaus parkhaus;

    @BeforeEach
    void setUp() {
        parkhaus = new Parkhaus();
        automat = parkhaus.getBezahlAutomat();

        KundenDaten kd = new KundenDaten(new String[]{
                "123456",           //Nr
                "1590408991325",    //Beginn
                "20",               //Dauer
                "5000",             //Preis
                "jrjhekjdlfjdsfk",  //Tickethash
                "ff00ff",           //Farbe
                "7",                //Slot
                "Familie"           //Kundengruppe

        });
        ticket = new Parkticket(new Kunde(kd));
    }

    @Test
    @DisplayName("Bezahlen eines Parktickets läuft erfolgreich")
    void bezahlen_einesParktickets_laeuftErfolgreich() {
        assertFalse(ticket.isBezahlt());
        assertTrue(automat.bezahlen(ticket, Optional.empty()));
        assertTrue(ticket.isBezahlt());
    }

    static KundenTyp[] KundenTypen() {
        return KundenTyp.values();
    }

    @ParameterizedTest
    @MethodSource("KundenTypen")
    @DisplayName("Die Preisberechnung des Bezahlautomaten ist für alle Kundentypen korrekt")
    void getPreis_nachDemParkenMitVerschiedenenKundenTypen_rechnetKorrekt(KundenTyp kundenTyp) {
        Integer dauer = 3000;

        KundenDaten kd = new KundenDaten(new String[]{
                "478349",           //Nr
                "1590408991325",    //Beginn
                dauer.toString(),   //Dauer
                "383",              //Preis
                "kfjien",           //Tickethash
                "ff00ff",           //Farbe
                "7",                //Slot
                kundenTyp.getBezeichnung() //Kundengruppe
        });

        Kunde k = new Kunde(kd);
        ParkticketIF ticket = parkhaus.einfahren(k);
        parkhaus.addParkticket("jrjhekjdlfjdsfk", ticket);
        assertEquals(automat.getPreis(ticket), kundenTyp.getInitialPreis() * dauer/ (60f * 60f));

    }

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -10, 0, 600, 9000, 50000, Integer.MAX_VALUE})
    @DisplayName("Die Preisberechnung des Bezahlautomaten ist für alle Parkzeiten korrekt")
    void getPreis_nachDemParkenMitVerschiedenenZeiten_rechnetKorrekt(Integer dauer) {
        KundenDaten kd = new KundenDaten(new String[]{
                "478349",           //Nr
                "1590408991325",    //Beginn
                dauer.toString(),   //Dauer
                "383",              //Preis
                "kfjien",           //Tickethash
                "ff00ff",           //Farbe
                "7",                //Slot
                "PersonMitBehinderung" //Kundengruppe
        });

        Kunde k = new Kunde(kd);
        ParkticketIF ticket = parkhaus.einfahren(k);
        parkhaus.addParkticket("jrjhekjdlfjdsfk", ticket);

        if (dauer >= 0) {
            assertEquals(automat.getPreis(ticket), k.getKundenTyp().getInitialPreis() * dauer / (60f * 60f), 0.0001);
        } else {
            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                automat.getPreis(ticket);
            });

            assertEquals("Eine negative Parkdauer gibt es nicht", e.getMessage());
        }

    }

}