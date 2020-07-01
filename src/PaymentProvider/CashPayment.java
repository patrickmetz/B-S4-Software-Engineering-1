package PaymentProvider;

import kunde.KundeIF;

/**
 * @author Johannes Kratzsch
 */
public class CashPayment implements PaymentProviderIF{
    @Override
    public boolean pay(float amount, KundeIF kunde) {
        System.out.println("Kassiere " + amount + "€ in bar");
        return true;
    }
}
