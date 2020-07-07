package Einzelaufgaben.a81;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

// E = Element
public class SpecialIterator<E> implements Iterator<E> {
    private boolean mode = true; //true:even, false:odd
    List<E> list;
    private int cursor = 0;

    SpecialIterator(List<E> l) {
        list = l;
    }

    @Override
    public void remove() {

    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {

    }

    @Override
    public boolean hasNext() {
        return cursor < list.size();
    }

    @Override
    public E next() {
        E element = list.get(cursor);
        cursor += 2;
        return element;
    }

    public void switchMode() {
        mode = !mode;
        cursor++;
    }
}
