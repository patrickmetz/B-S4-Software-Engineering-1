package PaymentProvider;

import kunde.Kunde;
import kunde.KundeIF;

import java.util.function.Function;

/**
 * @author Johannes Kratzsch
 */
public class GirocardAdapter implements PaymentProviderIF {
    Girocard girocard;
    static Function<Float, Integer> mapAmountToInt = x -> Float.valueOf(x * 100).intValue();

    GirocardAdapter(Girocard girocard) {
        this.girocard = girocard;
    }

    @Override
    public boolean pay(float amount, KundeIF kunde) {
        return girocard.bezahlen(
                mapAmountToInt.apply(amount),
                kunde.getNr());
    }
}
