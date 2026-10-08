import java.lang.reflect.Method;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("=== Mini Reflection Test Runner (JUnit Simulation) ===");
        TestSuite suite = new TestSuite();
        Method[] methods = suite.getClass().getDeclaredMethods();

        int executedCount = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Run.class)) {
                try {
                    m.invoke(suite);
                    executedCount++;
                } catch (Exception e) {
                    System.out.println("Error executing " + m.getName() + ": " + e.getMessage());
                }
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Test Run Complete. Successfully executed " + executedCount + " annotated @Run tests.");
    }
}
