package PaymentProvider;

import kunde.Kunde;
import kunde.KundenDaten;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Johannes Kratzsch
 */
class PaymentProviderIFTest {
    final KundenDaten kd = new KundenDaten(new String[]{
            "123456",           //Nr
            "1590408991325",    //Beginn
            "20",               //Dauer
            "5000",             //Preis
            "jrjhekjdlfjdsfk",  //Tickethash
            "ff00ff",           //Farbe
            "7",                //Slot
            "Familie"           //Kundengruppe

    });

    public static PaymentProviderIF[] provider() {
        return new PaymentProviderIF[]{
                new Paypal(),
                new GirocardAdapter(new Girocard())
        };
    }

    @ParameterizedTest
    @MethodSource("provider")
    void pay_returnsTrue(PaymentProviderIF provider) {
        assertTrue(provider.pay(1.5f, new Kunde(kd)));
    }
}