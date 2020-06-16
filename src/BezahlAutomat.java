import PaymentProvider.CashPayment;
import PaymentProvider.PaymentProviderIF;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author Tobias Lohmüller
 */
public class BezahlAutomat implements BezahlAutomatIF {

    @Override
    public boolean bezahlen(ParkticketIF parkTicket, Optional<PaymentProviderIF> provider) {
        PaymentProviderIF paymentProvider = provider.orElseGet(CashPayment::new);

        if (! paymentProvider.pay(getPreis(parkTicket), parkTicket.getKunde())) {
            return false;
        }

        parkTicket.setBezahlt(true);
        return true;
    }

    @Override
    public float getPreis(ParkticketIF parkTicket) {
        if (parkTicket.getKunde().getDauer() < 0) {
            throw new RuntimeException("Eine negative Parkdauer gibt es nicht");
        }

        return parkTicket.getStundenPreis() * parkTicket.getKunde().getDauer();
    }

}
