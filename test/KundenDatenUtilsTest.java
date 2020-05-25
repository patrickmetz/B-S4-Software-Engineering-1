import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KundenDatenUtilsTest {

    @Test
    void formatDauer() {
        assertEquals("01:23:456", KundenDatenUtils.formatDauer(123456));
    }

    @Test
    void floatToEuro() {
        assertEquals("1,23 €", KundenDatenUtils.floatToEuro(1.23f));
    }
}