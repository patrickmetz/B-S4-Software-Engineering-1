import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    ParkhausIF p;
    KundeIF k;

    @BeforeEach
    void setUp() {
        p = new Parkhaus();
        k = new Kunde();
    }

    @Test
    void einfahren() {

    }

    @Test
    @DisplayName("Einfahren mit leerem Parkhaus ist erfolgreich")
    void einfahren_mitLeeremParkhaus_istErfolgreich() {
        ParkticketIF ticket = p.einfahren(k);
        assertNotNull(ticket);
    }

    @Test
    @DisplayName("Einfahren mit vollem Parkhaus schlaegt fehl")
    void einfahren_mitVollemParkhaus_schlaegtFehl() {
        //alle Plätze füllen
        for(int i=0; i <= 99; i++) {
            p.einfahren(k);
        }

        assertNull(p.einfahren(k));
    }

    @Test
    @DisplayName("Ausfahren mit einem Kunden im Parkhaus ist erfolgreich")
    void ausfahren_mitEinemKundenImParkhaus_istErfolgreich() {
        ParkticketIF ticket = p.einfahren(k);
        assertTrue(p.ausfahren(k, ticket));
    }

    @Test
    @DisplayName("Ausfahren mit keinem Kunden im Parkhaus schlägt fehl")
    void ausfahren_mitKeinemKundenImParkhaus_schlaegtFehl() {
        ParkticketIF ticket = p.einfahren(k);
        p.ausfahren(k, ticket); //letzten Kunden ausfahren lassen
        assertFalse(p.ausfahren(k, ticket));
    }


}