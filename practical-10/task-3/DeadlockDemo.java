public class DeadlockDemo {
    private static final Object ResourceA = "ResourceA_Lock";
    private static final Object ResourceB = "ResourceB_Lock";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Deadlock Prevention via Global Lock Ordering ===");

        // In a vulnerable scenario:
        // Thread 1 locks A then B
        // Thread 2 locks B then A -> creates circular dependency

        // Fixed scenario: Both threads always acquire ResourceA before ResourceB
        Thread t1 = new Thread(() -> {
            synchronized (ResourceA) {
                System.out.println("Thread 1: Holding ResourceA...");
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
                System.out.println("Thread 1: Waiting for ResourceB...");
                synchronized (ResourceB) {
                    System.out.println("Thread 1: Acquired both ResourceA and ResourceB successfully!");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            // FIXED LOCK ORDER: Lock A first, then B (matches Thread 1)
            synchronized (ResourceA) {
                System.out.println("Thread 2: Holding ResourceA...");
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
                System.out.println("Thread 2: Waiting for ResourceB...");
                synchronized (ResourceB) {
                    System.out.println("Thread 2: Acquired both ResourceA and ResourceB successfully!");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("----------------------------------------");
        System.out.println("Deadlock prevented: Ordered lock acquisition guarantees circular wait cannot occur.");
    }
}
