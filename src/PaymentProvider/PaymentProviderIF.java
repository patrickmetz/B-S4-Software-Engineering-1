package PaymentProvider;

import kunde.KundeIF;

/**
 * @author Johannes Kratzsch
 */
public interface PaymentProviderIF {
    public boolean pay(float amount, KundeIF kunde);
}
