
import kunde.Kunde;
import kunde.KundeIF;
import kunde.KundenDaten;
import kunde.KundenDatenIF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tobias Lohmüller
 * @author Johannes Kratzsch
 */
class ParkhausIFTest {
    private ParkhausIF parkhaus;
    private KundeIF kunde;
    private BezahlAutomatIF automat;

    private KundenDatenIF kundenEinfahrDatenIF;
    private KundenDatenIF kundenAusfahrDatenIF;

    @BeforeEach
    void setUp() {
        kundenEinfahrDatenIF = new KundenDaten(new String[]{"1","1590407917444","_","_","5fd73388c8286759fddc1cf53ca744cb","#4d56d8","7","Mitarbeiter"});
        kundenAusfahrDatenIF = new KundenDaten(new String[]{"1","1590407917444","200","20","5fd73388c8286759fddc1cf53ca744cb","#4d56d8","7","Mitarbeiter"});

        parkhaus = new Parkhaus();
        kunde = new Kunde(kundenEinfahrDatenIF);
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

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> parkhaus.einfahren(kunde));

        assertEquals("Das Parkhaus ist voll", exception.getMessage());
    }

    @Test
    @DisplayName("Ausfahren mit einem Kunden im Parkhaus ist erfolgreich")
    void ausfahren_mitEinemKundenImParkhaus_istErfolgreich() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        automat.bezahlen(ticket, Optional.empty());
        assertTrue(parkhaus.ausfahren(ticket, kundenAusfahrDatenIF));
    }

    @Test
    @DisplayName("Ausfahren mit keinem Kunden im Parkhaus schlägt fehl")
    void ausfahren_mitKeinemKundenImParkhaus_schlaegtFehl() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);
        automat.bezahlen(ticket, Optional.empty());
        parkhaus.ausfahren(ticket, kundenAusfahrDatenIF); //letzten Kunden ausfahren lassen

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> parkhaus.ausfahren(ticket, kundenAusfahrDatenIF));

        assertEquals("Es gibt keinen Kunden mehr, der ausfahren könnte", exception.getMessage());
    }

    @Test
    @DisplayName("Ausfahren, wenn nicht bezahlt wurde, schlägt fehl")
    void ausfahren_wennNichtBezahltWurde_schlaegtFehl() {
        ParkticketIF ticket = parkhaus.einfahren(kunde);

        RuntimeException exception = assertThrows(RuntimeException.class, ()-> parkhaus.ausfahren(ticket, kundenAusfahrDatenIF));

        assertEquals("Der Kunde hat noch nicht gezahlt.", exception.getMessage());
    }


}