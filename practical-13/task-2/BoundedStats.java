public class BoundedStats {

    // Generic method with upper bounded type parameter <N extends Number>
    public static <N extends Number> double average(N[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty for statistical calculation.");
        }

        double sum = 0.0;
        for (N val : values) {
            if (val != null) {
                // The bounded type parameter guarantees access to java.lang.Number methods like doubleValue()
                sum += val.doubleValue();
            }
        }
        return sum / values.length;
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 13 - Task 2: Bounded Type Parameters");
        System.out.println("==================================================");

        Integer[] intScores = {85, 92, 78, 95, 88};
        Double[] tempReadings = {36.5, 37.2, 38.1, 36.9, 37.5};
        Long[] serverLatencies = {120L, 145L, 110L, 135L, 160L};
        Float[] weights = {55.4f, 62.1f, 70.8f};

        System.out.println("1. Computing averages across numeric types:");
        System.out.printf("   Integer Array Average:         %.2f%n", average(intScores));
        System.out.printf("   Double Array Average:          %.2f%n", average(tempReadings));
        System.out.printf("   Long Array Average:            %.2f ms%n", average(serverLatencies));
        System.out.printf("   Float Array Average:           %.2f kg%n", average(weights));

        System.out.println("\n2. Type Boundary Enforcement Analysis:");
        System.out.println("   - The bounded parameter '<N extends Number>' ensures that only subclasses of");
        System.out.println("     java.lang.Number (e.g., Integer, Double, Long, Float, Short, Byte) are valid.");
        System.out.println("   - If one attempts to pass a non-Number array (such as String[] or Customer[]):");
        System.out.println("     e.g., String[] strArr = {\"A\", \"B\"}; average(strArr);");
        System.out.println("     The javac compiler rejects this with a compile-time type mismatch error:");
        System.out.println("     'inferred type does not conform to upper bound(s) of type variable N'");
        System.out.println("   - This guarantees compile-time type safety without runtime ClassCastExceptions.");
    }
}
