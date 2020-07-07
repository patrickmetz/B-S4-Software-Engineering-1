package Einzelaufgaben.a72;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Johannes Kratzsch
 * Version 1.0
 */class ExtensibleCalculatorIFTest {
    ExtensibleCalculatorIF c;

    @BeforeEach
    void setUp() {
        c = new ExtensibleCalculator();
    }

    @Test
    void operations_afterAddingOneOperation_returnsThatOperation() {
        c.addOperation("add", (x, y) -> x+y);

        assertArrayEquals(new String[]{"add"}, c.operations());
    }

    @Test
    void operations_afterAddingOneOperationByMethodReference_returnsThatOperation() {
        c.addOperation("add", Integer::sum);

        assertArrayEquals(new String[]{"add"}, c.operations());
    }

    @Test
    void calc_withNonExistentOperation_throwsException() {
        Throwable exception = assertThrows(RuntimeException.class, () -> c.calc("nope", 1, 1));

        assertEquals("Operation unbekannt!", exception.getMessage());
    }

    @Test
    void calc_add_returnsCorrectResult() {
        c.addOperation("add", Integer::sum);

        assertSame(4+7, c.calc("add", 4, 7));
    }

    @Test
    void calc_multiply_returnsCorrectResult() {
        c.addOperation("multiply", (x, y) -> x*y);

        assertSame(4*7, c.calc("multiply", 4, 7));
    }

    @Test
    void calc_xor_returnsCorrectResult() {
        c.addOperation("xor", (x, y) -> x^y);

        assertSame(4^7, c.calc("xor", 4, 7));
    }
}