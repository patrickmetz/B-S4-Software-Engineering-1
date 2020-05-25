import kunde.Kunde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BezahlAutomatIFTest {
    private BezahlAutomatIF automat;
    private ParkticketIF ticket;

    @BeforeEach
    void setUp() {
        automat = new BezahlAutomat();
        ticket = new Parkticket(new Kunde(null));
    }

    @Test
    @DisplayName("Bezahlen eines Parktickets läuft erfolgreich")
    void bezahlen_einesParktickets_laeuftErfolgreich() {
        assertFalse(ticket.isBezahlt());
        assertTrue(automat.bezahlen(ticket));
        assertTrue(ticket.isBezahlt());
    }

}