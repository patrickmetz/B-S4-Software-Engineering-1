import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BezahlAutomatIFTest {
    BezahlAutomatIF automat;
    ParkticketIF ticket;
    UhrzeitIF zeit;

    @BeforeEach
    void setUp() {
        automat = new BezahlAutomat();
        ticket = new Parkticket();
        zeit = new Uhrzeit(5, 5);
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