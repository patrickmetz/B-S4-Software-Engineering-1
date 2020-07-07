package Einzelaufgaben.a72;

import java.util.function.BiFunction;

public interface ExtensibleCalculatorIF {
    String[] operations();

    void addOperation(String name, BiFunction<Integer, Integer, Integer> operation);

    int calc(String op, int x, int y);
}

