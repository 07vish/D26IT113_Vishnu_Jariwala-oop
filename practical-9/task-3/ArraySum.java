import java.util.concurrent.atomic.AtomicLong;

public class ArraySum {
    private static long unsafeTotal = 0;
    private static final AtomicLong atomicTotal = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Parallel Array Summation Comparison ===");
        final int SIZE = 100_000;
        int[] data = new int[SIZE];
        long expectedSum = 0;
        for (int i = 0; i < SIZE; i++) {
            data[i] = 1;
            expectedSum += 1;
        }

        final int NUM_THREADS = 4;
        final int CHUNK = SIZE / NUM_THREADS;

        Thread[] workers = new Thread[NUM_THREADS];
        for (int t = 0; t < NUM_THREADS; t++) {
            final int start = t * CHUNK;
            final int end = (t == NUM_THREADS - 1) ? SIZE : start + CHUNK;
            workers[t] = new Thread(() -> {
                for (int i = start; i < end; i++) {
                    unsafeTotal += data[i];
                    atomicTotal.addAndGet(data[i]);
                }
            });
            workers[t].start();
        }
        for (Thread t : workers) t.join();

        System.out.println("Expected Sum:     " + expectedSum);
        System.out.println("Unsynchronized:   " + unsafeTotal + " (Under-counted due to data races)");
        System.out.println("AtomicLong Sum:   " + atomicTotal.get() + " (Strict consistency via atomic CAS)");
    }
}
