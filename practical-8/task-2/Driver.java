public class Driver {
    public static void main(String[] args) {
        System.out.println("=== Warehouse Stock Issue Processing ===");
        Warehouse warehouse = new Warehouse();
        warehouse.addStock("MacBook Pro", 5);
        warehouse.addStock("Dell Monitor", 10);

        String[][] requests = {
            { "MacBook Pro", "2" },
            { "Dell Monitor", "-3" }, // Invalid quantity
            { "MacBook Pro", "5" },  // Shortfall: only 3 left
            { "Dell Monitor", "4" }
        };

        for (String[] req : requests) {
            String item = req[0];
            int qty = Integer.parseInt(req[1]);
            System.out.println("\nProcessing Order: " + qty + " x " + item);
            try {
                warehouse.issue(item, qty);
            } catch (InvalidQuantityException e) {
                System.out.println("  [REJECTED - INVALID QUANTITY] " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("  [REJECTED - OUT OF STOCK] Shortfall: " + e.getShortfall() + " units -> " + e.getMessage());
            }
        }
        System.out.println("\nOrder batch completed. Warehouse operations continued without crash.");
    }
}
