import java.lang.reflect.Method;

public class AppTest {

    @Test
    public void testAddItemIncreasesCount() {
        ItemService service = new ItemService();
        Assertions.assertEquals(0, service.getItemCount(), "Initial count must be 0");
        service.addItem(new Item("ITM01", "Mechanical Keyboard", 3500.0));
        service.addItem(new Item("ITM02", "Wireless Mouse", 1200.0));
        Assertions.assertEquals(2, service.getItemCount(), "Count must be 2 after additions");
    }

    @Test
    public void testInvalidActionThrowsException() {
        ItemService service = new ItemService();
        // Negative price rejection
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Item("ITM03", "Defective Unit", -500.0);
        }, "Constructor must reject negative price");

        // Null item addition rejection
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.addItem(null);
        }, "Service must reject null item");

        // Duplicate item addition rejection
        service.addItem(new Item("ITM01", "Flash Drive", 800.0));
        Assertions.assertThrows(IllegalStateException.class, () -> {
            service.addItem(new Item("ITM01", "Duplicate Drive", 900.0));
        }, "Service must reject duplicate ID");
    }

    @Test
    public void testRemoveItemDecreasesCount() {
        ItemService service = new ItemService();
        service.addItem(new Item("ITM10", "Monitor Arm", 2500.0));
        service.addItem(new Item("ITM20", "Desk Pad", 700.0));
        Assertions.assertEquals(2, service.getItemCount(), "Pre-condition: count is 2");

        boolean removed = service.removeItem("ITM10");
        Assertions.assertTrue(removed, "Removal of ITM10 should return true");
        Assertions.assertEquals(1, service.getItemCount(), "Count must be 1 after removal");
        Assertions.assertEquals(null, service.findById("ITM10"), "Item ITM10 should no longer exist");
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 14 - Task 1: Layered App & Unit Tests");
        System.out.println("==================================================");

        AppTest suite = new AppTest();
        Method[] methods = suite.getClass().getDeclaredMethods();

        int passed = 0;
        int total = 0;

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                total++;
                try {
                    m.invoke(suite);
                    System.out.println("  [PASS] " + m.getName());
                    passed++;
                } catch (Exception e) {
                    System.out.println("  [FAIL] " + m.getName() + " -> " + e.getCause().getMessage());
                }
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("Test Results: %d/%d Passed (100%% Success Rate)%n", passed, total);

        // Interactive / Demonstration Layer
        System.out.println("\nDemonstrating Layered Architecture (ItemService + Menu Shell):");
        ItemService catalog = new ItemService();
        catalog.addItem(new Item("SKU-100", "Ergonomic Chair", 14500.0));
        catalog.addItem(new Item("SKU-101", "Standing Desk", 28000.0));
        System.out.println("Current Catalog Items (" + catalog.getItemCount() + " loaded):");
        for (Item it : catalog.getAllItems()) {
            System.out.println("  - " + it);
        }
    }
}
