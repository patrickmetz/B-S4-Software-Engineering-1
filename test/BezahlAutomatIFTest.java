import PaymentProvider.CashPayment;
import kunde.Kunde;
import kunde.KundenDaten;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        automat = new BezahlAutomat();

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

    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, -10, 0, 1, 100, 5000, Integer.MAX_VALUE})
    @DisplayName("Die Preisberechnung des Bezahlautomaten ist korrekt")
    void getPreis_nachDemParken_berechnetKorrekt(Integer dauer) {
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
            assertEquals(automat.getPreis(ticket), ticket.getStundenPreis() * dauer);
        } else {
            RuntimeException e = assertThrows(RuntimeException.class, () -> {
                automat.getPreis(ticket);
            });

            assertEquals("Eine negative Parkdauer gibt es nicht", e.getMessage());
        }

    }

}