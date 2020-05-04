import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkhausIFTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void einfahren() {
    }

    @Test
    void ausfahren() {
    }

    @BeforeAll
    static void setup() {
        ParkhausIF parkHaus = new Parkhaus();
    }

    @Test
    @DisplayName("Einfahrt mit leerem Parkhaus ist erfolgreich")
    void einfahrt_mitLeeremParkhaus_istErfolgreich() {
        fail();
    }
}