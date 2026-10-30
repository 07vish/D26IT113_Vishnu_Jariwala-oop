import java.util.Objects;

public class Assertions {
    public static void assertEquals(Object expected, Object actual, String message) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(message + " [Expected: " + expected + ", Actual: " + actual + "]");
        }
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message + " [Expected: true, Actual: false]");
        }
    }

    public interface Executable {
        void execute() throws Throwable;
    }

    public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable, String message) {
        try {
            executable.execute();
        } catch (Throwable actual) {
            if (expectedType.isInstance(actual)) {
                return expectedType.cast(actual);
            }
            throw new AssertionError(message + " [Expected exception: " + expectedType.getName()
                + ", but caught: " + actual.getClass().getName() + "]");
        }
        throw new AssertionError(message + " [Expected exception: " + expectedType.getName() + " was not thrown]");
    }
}
