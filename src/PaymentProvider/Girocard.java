package PaymentProvider;

/**
 * @author Johannes Kratzsch
 */
public class Girocard {
    public boolean bezahlen(int betragInCent, int kundennummer) {
        System.out.println("Buche " + betragInCent + " Cent ab von Kunden " + kundennummer);
        return true;
    }
}
