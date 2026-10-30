public class GenericContainers {
    // Generic Box holding a single value of type T
    public static class Box<T> {
        private T value;

        public Box() {}
        public Box(T value) { this.value = value; }

        public T get() { return value; }
        public void set(T value) { this.value = value; }

        @Override
        public String toString() {
            return "Box[" + (value != null ? value.toString() : "empty") + "]";
        }
    }

    // Generic Pair holding two values of potentially different types
    public static class Pair<A, B> {
        private final A first;
        private final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() { return first; }
        public B getSecond() { return second; }

        @Override
        public String toString() {
            return "Pair(" + first + ", " + second + ")";
        }
    }

    // Generic static method returning the first element of any array
    public static <T> T getFirstElement(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Practical 13 - Task 1: Generic Containers & Methods");
        System.out.println("==================================================");

        // 1. Box<T> demonstrations with different types (compile-time type safety)
        Box<String> strBox = new Box<>("CHARUSAT University");
        Box<Integer> intBox = new Box<>(2026);
        Box<Double> dblBox = new Box<>(98.75);

        System.out.println("1. Generic Box instances (no casting required):");
        System.out.println("   String Box: " + strBox.get() + " (Length: " + strBox.get().length() + ")");
        System.out.println("   Integer Box: " + intBox.get() + " (Square: " + (intBox.get() * intBox.get()) + ")");
        System.out.println("   Double Box: " + dblBox.get());

        // 2. Pair<A, B> demonstrations with heterogeneous type parameters
        Pair<String, Integer> studentRank = new Pair<>("Vishnu", 1);
        Pair<Integer, String> httpStatus = new Pair<>(200, "OK");
        Pair<String, Double> stockPrice = new Pair<>("TCS", 3840.50);

        System.out.println("\n2. Generic Pair instances:");
        System.out.println("   Student Rank: " + studentRank.getFirst() + " -> #" + studentRank.getSecond());
        System.out.println("   HTTP Status:  " + httpStatus.getFirst() + " -> " + httpStatus.getSecond());
        System.out.println("   Stock Quote:  " + stockPrice.getFirst() + " -> ₹" + stockPrice.getSecond());

        // 3. Generic method getFirstElement on diverse array types
        String[] languages = {"Java", "Python", "Rust", "Go"};
        Integer[] primes = {2, 3, 5, 7, 11};
        Double[] sensorReadings = {36.6, 37.1, 36.8};

        System.out.println("\n3. Generic Method getFirstElement():");
        System.out.println("   First language: " + getFirstElement(languages));
        System.out.println("   First prime:    " + getFirstElement(primes));
        System.out.println("   First reading:  " + getFirstElement(sensorReadings));
    }
}
