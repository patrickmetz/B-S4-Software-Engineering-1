package Einzelaufgaben.a81;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;


class SpecialIteratorTest {
    SpecialIterator<Integer> iter;

    @BeforeEach
    void SetUp() {
        List<Integer> l = new LinkedList<>();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        iter = new SpecialIterator<>(l);
    }


    @Test
    void next_inEvenMode_returnsRightValue() {
        assertSame(0, iter.next());
        assertSame(2, iter.next());
        assertSame(4, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    void next_inOddMode_returnsRightValue() {
        iter.switchMode();
        assertSame(1, iter.next());
        assertSame(3, iter.next());
        assertSame(5, iter.next());
        assertFalse(iter.hasNext());
    }

}