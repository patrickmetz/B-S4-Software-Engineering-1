package PaymentProvider;

import kunde.KundeIF;

/**
 * @author Johannes Kratzsch
 */
public class Paypal implements PaymentProviderIF {
    @Override
    public boolean pay(float amount, KundeIF kunde) {
        System.out.println("Buche " + amount + "€ ab von Kunden " + kunde.getNr());
        return true;
    }
}
