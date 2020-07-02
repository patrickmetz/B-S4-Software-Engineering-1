package PaymentProvider;

import kunde.KundeIF;

import java.util.function.ToIntFunction;

/**
 * @author Johannes Kratzsch
 */
public class GirocardAdapter implements PaymentProviderIF {
    Girocard girocard;
    static ToIntFunction<Float> mapAmountToInt = x -> (int) (x * 100);

    GirocardAdapter(Girocard girocard) {
        this.girocard = girocard;
    }

    @Override
    public boolean pay(float amount, KundeIF kunde) {
        return girocard.bezahlen(
                mapAmountToInt.applyAsInt(amount),
                kunde.getNr());
    }
}
