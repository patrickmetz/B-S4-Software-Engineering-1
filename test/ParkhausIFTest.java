import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    ParkhausIF parkhaus;
    KundeIF kunde;
    BezahlAutomatIF automat;

    @BeforeEach
    void setUp() {
        parkhaus = new Parkhaus();
        kunde = new Kunde();
        automat = new BezahlAutomat();
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

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            parkhaus.einfahren(kunde);
        });

        assertEquals("Das Parkhaus ist voll", exception.getMessage());
    }

    @Test
    @DisplayName("Ausfahren mit einem Kunden im Parkhaus ist erfolgreich")
    void ausfahren_mitEinemKundenImParkhaus_istErfolgreich() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        automat.bezahlen(ticket);
        assertTrue(parkhaus.ausfahren(kunde, ticket));
    }

    @Test
    @DisplayName("Ausfahren mit keinem Kunden im Parkhaus schlägt fehl")
    void ausfahren_mitKeinemKundenImParkhaus_schlaegtFehl() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        automat.bezahlen(ticket);
        parkhaus.ausfahren(kunde, ticket); //letzten Kunden ausfahren lassen

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            parkhaus.ausfahren(kunde, ticket);
        });

        assertEquals("Es gibt keinen Kunden mehr, der ausfahren könnte", exception.getMessage());
    }

    @Test
    @DisplayName("Ausfahren, wenn nicht bezahlt wurde, schlägt fehl")
    void ausfahren_wennNichtBezahltWurde_schlaegtFehl() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        parkhaus.ausfahren(kunde, ticket);

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            parkhaus.ausfahren(kunde, ticket);
        });

        assertEquals("Der Kunde hat noch nicht gezahlt.", exception.getMessage());
    }


}