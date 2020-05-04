import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    ParkhausIF parkhaus;
    KundeIF kunde;

    @BeforeEach
    void setUp() {
        parkhaus = new Parkhaus();
        kunde = new Kunde();
    }

    @Test
    void einfahren() {

    }

    @Test
    @DisplayName("Einfahren mit leerem Parkhaus ist erfolgreich")
    void einfahren_mitLeeremParkhaus_istErfolgreich() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        assertNotNull(ticket);
    }

    @Test
    @DisplayName("Einfahren mit vollem Parkhaus schlaegt fehl")
    void einfahren_mitVollemParkhaus_schlaegtFehl() {
        //alle Plätze füllen
        for(int i=0; i <= 99; i++) {
            parkhaus.einfahren(kunde);
        }

        assertNull(parkhaus.einfahren(kunde));
    }

    @Test
    @DisplayName("Ausfahren mit einem Kunden im Parkhaus ist erfolgreich")
    void ausfahren_mitEinemKundenImParkhaus_istErfolgreich() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        assertTrue(parkhaus.ausfahren(kunde, ticket));
    }

    @Test
    @DisplayName("Ausfahren mit keinem Kunden im Parkhaus schlägt fehl")
    void ausfahren_mitKeinemKundenImParkhaus_schlaegtFehl() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        parkhaus.ausfahren(kunde, ticket); //letzten Kunden ausfahren lassen
        assertFalse(parkhaus.ausfahren(kunde, ticket));
    }


}