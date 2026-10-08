import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final int CAPACITY = 3;
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Producer-Consumer via wait() and notify() ===");
        final int TOTAL_ITEMS = 6;

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= TOTAL_ITEMS; i++) {
                synchronized (lock) {
                    while (buffer.size() == CAPACITY) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {}
                    }
                    buffer.add(i);
                    System.out.println("[PRODUCER] Produced item: " + i + " | Buffer size: " + buffer.size());
                    lock.notify();
                }
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= TOTAL_ITEMS; i++) {
                synchronized (lock) {
                    while (buffer.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {}
                    }
                    int item = buffer.poll();
                    System.out.println("  [CONSUMER] Consumed item: " + item + " | Buffer size: " + buffer.size());
                    lock.notify();
                }
                try { Thread.sleep(15); } catch (InterruptedException ignored) {}
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("----------------------------------------");
        System.out.println("All " + TOTAL_ITEMS + " items produced and consumed in strict order without data loss.");
    }
}
