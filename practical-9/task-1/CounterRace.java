public class CounterRace {
    private static int unsafeCount = 0;
    private static int safeCount = 0;

    private static synchronized void incrementSafe() {
        safeCount++;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Multithreaded Counter Race Condition ===");
        final int THREADS = 10;
        final int ITERATIONS = 1000;
        final int EXPECTED = THREADS * ITERATIONS;

        // Run Unsynchronized
        Thread[] unsafeThreads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            unsafeThreads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    unsafeCount++; // Unsafe non-atomic read-modify-write
                }
            });
            unsafeThreads[i].start();
        }
        for (Thread t : unsafeThreads) t.join();

        // Run Synchronized
        Thread[] safeThreads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            safeThreads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    incrementSafe();
                }
            });
            safeThreads[i].start();
        }
        for (Thread t : safeThreads) t.join();

        System.out.println("Expected Count:        " + EXPECTED);
        System.out.println("Unsynchronized Result: " + unsafeCount + " (Lost updates occurred due to race condition)");
        System.out.println("Synchronized Result:   " + safeCount + " (Exact match guaranteed by monitor locks)");
    }
}
