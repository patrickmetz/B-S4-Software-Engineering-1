package Einzelaufgaben.a101;/*
 * Aufgabe 7.2
 */

import java.util.*;
import java.util.function.BiFunction;

public class ExtensibleCalculator implements ExtensibleCalculatorIF {
    Map<String, BiFunction<Integer, Integer, Integer>> operations;
    List<HistoryItem> history;
    int pointer = -1;
    ListIterator<HistoryItem> iterator;


    ExtensibleCalculator() {
        operations = new HashMap<>();
        history = new LinkedList<>();
    }

    @Override
    public String[] operations() {
        String[] keys = operations.keySet().stream().toArray(String[]::new);

        return keys;
    }

    @Override
    public void addOperation(String name, BiFunction<Integer, Integer, Integer> operation) {
        operations.put(name, operation);
        iterator = history.listIterator();
    }

    @Override
    public int calc(String op, int x, int y) {
        if (null == operations.get(op)) {
            throw new RuntimeException("Operation unbekannt!");
        }

        int value = operations.get(op).apply(x, y);

        history.add(new HistoryItem(value, op));
        pointer += 1;

        return value;
    }

    public int getValue() {
        if (pointer == -1) {
            return 0;
        }

        return getItem().getValue();
    }

    public int undo() {
        pointer -= 1;

        return getValue();
    }

    public int redo() {
        pointer += 1;

        return getValue();
    }

    private HistoryItem getItem () {
        return history.get(pointer);
    }

}
