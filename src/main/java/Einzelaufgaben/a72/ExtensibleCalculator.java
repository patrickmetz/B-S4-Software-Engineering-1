package Einzelaufgaben.a72;/*
 * Aufgabe 7.2
 */

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ExtensibleCalculator implements ExtensibleCalculatorIF {
    Map<String, BiFunction<Integer, Integer, Integer>> operations;

    ExtensibleCalculator() {
        operations = new HashMap<>();
    }

    @Override
    public String[] operations() {
        String[] keys = operations.keySet().stream().toArray(String[]::new);

        return keys;
    }

    @Override
    public void addOperation(String name, BiFunction<Integer, Integer, Integer> operation) {
        operations.put(name, operation);
    }

    @Override
    public int calc(String op, int x, int y) {
        if (null == operations.get(op)) {
            throw new RuntimeException("Operation unbekannt!");
        }

        return operations.get(op).apply(x, y);
    }
}
