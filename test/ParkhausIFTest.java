import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {
    ParkhausIF parkhaus;
    KundeIF kunde;
    BezahlAutomatIF automat;
    UhrzeitIF zeit;

    @BeforeEach
    void setUp() {
        parkhaus = new Parkhaus();
        kunde = new Kunde();
        automat = new BezahlAutomat();
        zeit = new Uhrzeit(5, 5);
    }

    @Test
    @DisplayName("Einfahren mit leerem Parkhaus ist erfolgreich")
    void einfahren_mitLeeremParkhaus_istErfolgreich() {
        ParkticketIF ticket = parkhaus.einfahren(kunde, zeit);
        assertNotNull(ticket);
    }

    @Test
    @DisplayName("Einfahren mit vollem Parkhaus schlaegt fehl")
    void einfahren_mitVollemParkhaus_schlaegtFehl() {
        //alle Plätze füllen
        for(int i=0; i <= 99; i++) {
            parkhaus.einfahren(kunde, zeit);
        }

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            parkhaus.einfahren(kunde, zeit);
        });

        assertEquals("Das Parkhaus ist voll", exception.getMessage());
    }

    @Test
    @DisplayName("Ausfahren mit einem Kunden im Parkhaus ist erfolgreich")
    void ausfahren_mitEinemKundenImParkhaus_istErfolgreich() {
        ParkticketIF ticket = parkhaus.einfahren(kunde, zeit);
        automat.bezahlen(ticket, zeit);
        assertTrue(parkhaus.ausfahren(kunde, ticket));
    }

    @Test
    @DisplayName("Ausfahren mit keinem Kunden im Parkhaus schlägt fehl")
    void ausfahren_mitKeinemKundenImParkhaus_schlaegtFehl() {
        ParkticketIF ticket = parkhaus.einfahren(kunde, zeit);
        automat.bezahlen(ticket, zeit);
        parkhaus.ausfahren(kunde, ticket); //letzten Kunden ausfahren lassen

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            parkhaus.ausfahren(kunde, ticket);
        });

        assertEquals("Es gibt keinen Kunden mehr, der ausfahren könnte", exception.getMessage());
    }

    @Test
    @DisplayName("Ausfahren, wenn nicht bezahlt wurde, schlägt fehl")
    void ausfahren_wennNichtBezahltWurde_schlaegtFehl() {
        ParkticketIF ticket = parkhaus.einfahren(kunde, zeit);
        parkhaus.ausfahren(kunde, ticket);

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
            parkhaus.ausfahren(kunde, ticket);
        });

        assertEquals("Der Kunde hat noch nicht gezahlt.", exception.getMessage());
    }


}