import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tobias Lohmüller
 */
class KundenDatenUtilsTest {

    @Test
    @DisplayName("Die Konvertierung einer Zahl zu der entsprechenden Zeit.")
    void formatDauer() {
        assertEquals("01:23:456", KundenDatenUtils.formatDauer(123456));
    }

    @Test
    @DisplayName("Die Konvertierung einer Zahl zum entsprechenden Preisformat.")
    void floatToEuro() {
        assertEquals("1,23 €", KundenDatenUtils.floatToEuro(1.23f));
    }
}