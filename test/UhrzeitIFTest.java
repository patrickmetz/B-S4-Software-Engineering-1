
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class UhrzeitIFTest {
    private final int STUNDE = 9;
    private final int MINUTE = 5;

    private UhrzeitIF zeit;

    @BeforeEach
    void setUp() {
        zeit = new Uhrzeit(STUNDE, MINUTE);
    }

    @Test
    @DisplayName("Das Setzen der Stunde soll ohne Fehler funktionieren")
    void setStunden_ThrowsNoError() {
        zeit.setStunden(STUNDE);
    }

    @Test
    @DisplayName("Das Setzen der Minuten soll ohne Fehler funktionieren")
    void setMinuten_ThrowsNoError() {
        zeit.setMinuten(MINUTE);
    }

    @Test
    @DisplayName("Das Abfragen der Stunde soll den richtigen Wert liefern")
    void getStunden_ReturnsRightValue() {
        assertSame(STUNDE, zeit.getStunden());
    }

    @Test
    @DisplayName("Das Setzen der Minuten soll den richtigen Wert liefern")
    void getMinuten_ReturnsRightValue() {
        assertSame(MINUTE, zeit.getMinuten());
    }

    @Test
    @DisplayName("Nach Hinzufügen der Stunden soll die Zeit den richtigen Wert liefern")
    void addStunden_ReturnsRightValue() {
        zeit.addStunden(1);
        assertSame(STUNDE + 1, zeit.getStunden());

        zeit.addStunden(24);
        assertSame(STUNDE + 1, zeit.getStunden());

        zeit.addStunden(-25);
        assertSame(STUNDE, zeit.getStunden());
    }

    @Test
    @DisplayName("Das Hinzufügen der Minuten soll die Zeit den richtigen Wert liefern")
    void addMinuten_ReturnsRightValue() {
        zeit.addMinuten(1);
        assertSame(MINUTE + 1, zeit.getMinuten());

        zeit.addMinuten(60);
        assertSame(STUNDE + 1, zeit.getStunden());
        assertSame(MINUTE +1, zeit.getMinuten());

        zeit.addMinuten(-1);
        assertSame(MINUTE, zeit.getMinuten());

        zeit.addMinuten(-60);
        assertSame(MINUTE, zeit.getMinuten());
        assertSame(STUNDE, zeit.getStunden());

        zeit.addMinuten(-120);
        assertSame(MINUTE, zeit.getMinuten());
        assertSame(STUNDE -2, zeit.getStunden());
    }
}