import Fahrzeuge.FahrzeugTyp;
import Fahrzeuge.MultitonFahrzeugTyp;
import PaymentProvider.CashPayment;
import PaymentProvider.PaymentProviderIF;
import preis.PreisIF;
import preis.PreisVerwaltungControllerIF;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * @author Tobias Lohmüller
 * @author Johannes Kratzsch
 */
public class BezahlAutomat implements BezahlAutomatIF {
    PreisVerwaltungControllerIF controller;

    public BezahlAutomat(PreisVerwaltungControllerIF controller) {
        this.controller = controller;
    }

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

        PreisIF preis = controller.getPreis(parkTicket.getKunde().getKundenTyp());
        float stunden = parkTicket.getKunde().getDauer() / (60f * 60f);
        float aufschlag = MultitonFahrzeugTyp.getInstanz(parkTicket.getKunde().getFahrzeugTyp()).getPreis().getBetrag();

        float betrag = preis.getBetrag() * stunden + aufschlag;

        if(stunden > 0)
            System.out.println("Berechne für Kunden " + parkTicket.getKunde().getKundenTyp().getBezeichnung() + " mit dem Fahrzeug " + parkTicket.getKunde().getFahrzeugTyp() + ", den Stundensatz " + KundenDatenUtils.floatToEuro(preis.getBetrag())
                    + " und den Fahrzeug-Aufschlag von " + KundenDatenUtils.floatToEuro(aufschlag) + " -> Summe " + KundenDatenUtils.floatToEuro(betrag));

        return betrag;
    }

    @Override
    public PreisVerwaltungControllerIF getPreisVerwaltungController() {
        return controller;
    }
}
