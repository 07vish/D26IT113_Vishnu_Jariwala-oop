import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final Map<String, Integer> stock = new HashMap<>();

    public void addStock(String item, int qty) {
        stock.put(item, stock.getOrDefault(item, 0) + qty);
    }

    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Requested quantity must be positive. Given: " + qty);
        }
        int available = stock.getOrDefault(item, 0);
        if (available < qty) {
            throw new OutOfStockException(item, qty, available);
        }
        stock.put(item, available - qty);
        System.out.println("  Successfully dispatched " + qty + " units of '" + item + "'. Remaining stock: " + (available - qty));
    }
}
