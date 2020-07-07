package Einzelaufgaben.a101;

public class HistoryItem {
    private int value;
    private String operation;

    public HistoryItem(int value, String operation) {
        this.value = value;
        this.operation = operation;
    }

    public int getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }
}
