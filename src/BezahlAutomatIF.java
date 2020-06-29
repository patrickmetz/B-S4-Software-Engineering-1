import PaymentProvider.PaymentProviderIF;
import preis.PreisVerwaltungControllerIF;

import java.util.Optional;

/**
 * @author Tobias Lohmüller
 */
public interface BezahlAutomatIF {

    boolean bezahlen(ParkticketIF parkTicket, Optional<PaymentProviderIF> paymentProvider);

    float getPreis(ParkticketIF parkTicket);

    PreisVerwaltungControllerIF getPreisVerwaltungController();
}
