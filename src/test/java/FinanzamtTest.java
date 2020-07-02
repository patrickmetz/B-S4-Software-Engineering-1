import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Johannes Kratzsch
 */
class FinanzamtTest {
    static float gross = 2.2f;
    static float net = 1.85f;
    static float tax = 0.35f;
    static float allowedDelta = 0.004f;

    @Test
    @DisplayName("Die Berechung von Brutto- zu Nettopreis erfolgt fehlerfrei")
    void getNetFromGross_returnsCorrectValue() {
        assertEquals(net, Finanzamt.getNetFromGross(gross), allowedDelta);
    }

    @Test
    @DisplayName("Die Berechung von Bruttopreis zu den Steuern")
    void getTaxFromGross_returnsCorrectValue() {
        assertEquals(tax, Finanzamt.getTaxFromGross(gross), allowedDelta);
    }

    @Test
    @DisplayName("Der errechnete Nettopreis und die errechneten Steuern ergeben den Bruttopreis")
    void sumOfNetAndTax_yieldsGross() {
        assertEquals(gross, Finanzamt.getNetFromGross(gross)
                + Finanzamt.getTaxFromGross(gross), allowedDelta);
    }
}