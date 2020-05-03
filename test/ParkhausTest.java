import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ParkhausTest {

    static ParkhausIF parkhaus;

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
