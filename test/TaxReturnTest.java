import Einzelaufgaben.ParkhausStatistics;
import Einzelaufgaben.TaxReturn;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Johannes Kratzsch
 */
class TaxReturnTest {
    //Credits for Output Stream manipulation: https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    private ParkhausStatistics statistics;
    private TaxReturn taxReturn;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    void setUp() {
        statistics = new ParkhausStatistics();
        statistics.addEinahme(5.5f);
        statistics.addEinahme(67f);
        statistics.addEinahme(.4f);
        statistics.addEinahme(88f);

        taxReturn = new TaxReturn();
    }

    @Test
    @DisplayName("Das TaxReturnCommand übermittelt die korrekte Steuererklärung.")
    void taxReturnCommand_submitsTheCorrectData() {
        taxReturn.addCommand(() -> TaxReturn.taxReturnCommand(statistics));
        taxReturn.executeCommands();

        assertEquals("{\"Steuererklärung\":{\"Summe der Monatseinnahmen (brutto)\":\"160,90 €\",\"Summe der Monatseinnahmen (netto)\":\"135,21 €\",\"Summe der Steuern\":\"25,69 €\",\"Monatlicher Freibetrag\":\"2.000,00 €\"}}\n",
                outContent.toString());
    }
}