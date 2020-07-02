import PaymentProvider.CashPayment;
import kunde.Kunde;
import kunde.KundenDaten;
import kunde.KundenDatenIF;
import kunde.KundenTyp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import preis.PreisVerwaltungController;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tobias Lohmüller
 */
class KundenDatenProcessorIFTest {

    ParkhausIF parkhausIF;
    KundenDatenProcessorIF kundenDatenProcessorIF;

    KundenDatenIF einfahrtDaten1;
    KundenDatenIF einfahrtDaten2;

    KundenDatenIF ausFahrtDaten1;
    KundenDatenIF ausFahrtDaten2;

    @BeforeEach
    void setUp() {
        einfahrtDaten1 = new KundenDaten(new String[]{"1","1590408991325","_","_","c0e8c5f157d07e3f8658367b37baeac8","#c6c804","6","PersonMitBehinderung"}); // 1,1590408991325,_,_,c0e8c5f157d07e3f8658367b37baeac8,#c6c804,6,PersonMitBehinderung
        einfahrtDaten2 = new KundenDaten(new String[]{"2","1590408991524","_","_","8c56d532c5f318b73fa90a86016f3065","#959d6c","2","PersonMitBehinderung"}); // 2,1590408991524,_,_,8c56d532c5f318b73fa90a86016f3065,#959d6c,2,PersonMitBehinderung
        ausFahrtDaten1 = new KundenDaten(new String[]{"1","1590408991325","3015","302","c0e8c5f157d07e3f8658367b37baeac8","#c6c804","7","PersonMitBehinderung"}); // 1,1590408991325,3015,302,c0e8c5f157d07e3f8658367b37baeac8,#c6c804,7,PersonMitBehinderung
        ausFahrtDaten2 = new KundenDaten(new String[]{"2","1590408991524","201","20","8c56d532c5f318b73fa90a86016f3065","#959d6c","5","PersonMitBehinderung"}); // 2,1590408991524,201,20,8c56d532c5f318b73fa90a86016f3065,#959d6c,5,PersonMitBehinderung

        parkhausIF = new Parkhaus();

        ParkticketIF parkticketIF = parkhausIF.einfahren(new Kunde(einfahrtDaten1));
        ParkticketIF parkticketIF2 = parkhausIF.einfahren(new Kunde(einfahrtDaten2));

        //Dann vergeht Zeit

        BezahlAutomatIF bezahlAutomatIF = parkhausIF.getBezahlAutomat();
        bezahlAutomatIF.bezahlen(parkticketIF, Optional.empty());
        bezahlAutomatIF.bezahlen(parkticketIF2, Optional.empty());

        parkhausIF.ausfahren(parkticketIF, ausFahrtDaten1);
        parkhausIF.ausfahren(parkticketIF2, ausFahrtDaten2);

        kundenDatenProcessorIF = new KundenDatenProcessor(parkhausIF);
    }

    @Test
    @DisplayName("Das erhalten der Preisdaten aus dem Datensatz")
    void getSumme() {
        assertEquals(6.7, kundenDatenProcessorIF.getSumme(), 0.00001);
    }

    @Test
    @DisplayName("Das erhalten der Durschnittsparkdauer aus dem Datensatz")
    void getDurchschnittsDauer() {
        assertEquals(1608 , kundenDatenProcessorIF.getDurchschnittsDauer());
    }

    @Test
    @DisplayName("Das erhalten des Durchschnittspreis aus dem Datensatz")
    void getDurschnittsPreis() {
        assertEquals(3.35f, kundenDatenProcessorIF.getDurschnittsPreis());
    }

    @Test
    @DisplayName("Das erhalten der Umsatzsteuer aus dem Datensatz")
    void getUmsatzSteuer() {
        assertEquals(1.07f, (float) Math.round(100 * kundenDatenProcessorIF.getUmsatzSteuer())/100);
    }
}