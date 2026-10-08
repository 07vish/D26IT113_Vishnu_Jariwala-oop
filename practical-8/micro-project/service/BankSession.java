package service;

public class BankSession implements AutoCloseable {
    private final String operator;

    public BankSession(String operator) {
        this.operator = operator;
        System.out.println("Session opened for security audit operator: " + operator);
    }

    @Override
    public void close() {
        System.out.println("Session closed and audit trail flushed for: " + operator);
    }
}
