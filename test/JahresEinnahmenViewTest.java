import kunde.Kunde;
import kunde.KundenDaten;
import kunde.KundenTyp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import preis.PreisVerwaltungController;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JahresEinnahmenViewTest {
    Parkhaus parkhaus;

    @BeforeEach
    void setUp() {
        parkhaus = new Parkhaus();
    }

    @Test
    void view_whenParkhausIsEmpty_ReturnsZeroEuros() {
        JahresEinnahmenView view = new JahresEinnahmenView(parkhaus.getParkhausStatistics());
        view.aktualisieren();
        assertEquals("0,00 €", view.view());
    }

    @Test
    void view_whenParkhausHadVisitors_ReturnsCorrectValue(){
        parkhaus = new Parkhaus();
        BezahlAutomatIF automat = parkhaus.getBezahlAutomat();

        KundenDaten kd = new KundenDaten(new String[]{
                "123456",           //Nr
                "1590408991325",    //Beginn
                "998443",               //Dauer
                "5000",             //Preis
                "jrjhekjdlfjdsfk",  //Tickethash
                "ff00ff",           //Farbe
                "7",                //Slot
                "Familie"           //Kundengruppe

        });
        Kunde k = new Kunde(kd);
        ParkticketIF t = parkhaus.einfahren(k);
        automat.bezahlen(t, Optional.empty());
        parkhaus.ausfahren(t, kd);

        JahresEinnahmenView view = new JahresEinnahmenView(parkhaus.getParkhausStatistics());
        view.aktualisieren();
        assertEquals("4.160,18 €", view.view());
    }
}