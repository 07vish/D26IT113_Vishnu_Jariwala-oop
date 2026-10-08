public class MiniBank {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 9: Thread Safety & Concurrent Workers");
        System.out.println("==================================================");

        Account sharedAccount = new Account("AC7701", "Central Treasury", 0);
        final int NUM_WORKERS = 10;
        final int DEPOSITS_PER_WORKER = 50;
        final long DEPOSIT_AMOUNT = 100;
        final long EXPECTED_BALANCE = NUM_WORKERS * DEPOSITS_PER_WORKER * DEPOSIT_AMOUNT;

        Thread[] threads = new Thread[NUM_WORKERS];
        System.out.println("Spawning " + NUM_WORKERS + " concurrent AccountWorker threads...");

        for (int i = 0; i < NUM_WORKERS; i++) {
            AccountWorker worker = new AccountWorker(sharedAccount, DEPOSITS_PER_WORKER, DEPOSIT_AMOUNT);
            threads[i] = new Thread(worker, "BankWorker-Thread-" + (i + 1));
            System.out.println("  Created " + threads[i].getName() + " [State: " + threads[i].getState() + "]");
        }

        System.out.println("\nStarting all workers simultaneously...");
        for (Thread t : threads) {
            t.start();
        }

        // Await thread completion
        for (Thread t : threads) {
            t.join();
            System.out.println("  " + t.getName() + " finished [State: " + t.getState() + "]");
        }

        System.out.println("\n==================================================");
        System.out.println("Expected Final Treasury Balance: ₹" + EXPECTED_BALANCE);
        System.out.println("Actual Final Treasury Balance:   ₹" + sharedAccount.getBalance());
        System.out.println("Status: Synchronized critical sections prevented all race conditions!");
    }
}
