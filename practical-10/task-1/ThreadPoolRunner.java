import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Fixed Thread Pool Task Execution ===");
        final int POOL_SIZE = 3;
        final int TOTAL_TASKS = 10;
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

        System.out.println("Initialized thread pool of fixed size: " + POOL_SIZE);

        for (int i = 1; i <= TOTAL_TASKS; i++) {
            final int taskId = i;
            pool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task #" + taskId + " running on worker: " + threadName);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ignored) {}
                System.out.println("Task #" + taskId + " completed by:  " + threadName);
            });
        }

        pool.shutdown();
        boolean finished = pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("----------------------------------------");
        System.out.println("All tasks finished successfully? " + finished);
        System.out.println("Observed: Only 3 worker threads efficiently executed and recycled all 10 tasks!");
    }
}
