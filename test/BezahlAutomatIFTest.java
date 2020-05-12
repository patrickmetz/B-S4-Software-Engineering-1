import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BezahlAutomatIFTest {
    private BezahlAutomatIF automat;
    private ParkticketIF ticket;
    private UhrzeitIF zeit;

    @BeforeEach
    void setUp() {
        automat = new BezahlAutomat();
        ticket = new Parkticket(new Kunde());
        zeit = new Uhrzeit(0, 0);
    }

    @Test
    @DisplayName("Bezahlen eines Parktickets läuft erfolgreich")
    void bezahlen_einesParktickets_laeuftErfolgreich() {
        assertFalse(ticket.getBezahlt());
        assertTrue(automat.bezahlen(ticket, zeit));
        assertTrue(ticket.getBezahlt());
    }

    @Test
    @DisplayName("Die Preisberechnung des Bezahlautomaten ist korrekt")
    void getPreis_nachDemParken_berechnetKorrekt() {
        zeit.addStunden(1);
        assertEquals(automat.getPreis(ticket, zeit), ticket.getStundenPreis());

        zeit.addStunden(9);
        assertEquals(automat.getPreis(ticket, zeit), 10 * ticket.getStundenPreis());
    }
}