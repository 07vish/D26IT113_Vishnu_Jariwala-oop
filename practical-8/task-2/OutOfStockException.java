public class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(String item, int requested, int available) {
        super("Insufficient inventory for '" + item + "'. Requested: " + requested + ", Available: " + available);
        this.shortfall = requested - available;
    }

    public int getShortfall() {
        return shortfall;
    }
}
