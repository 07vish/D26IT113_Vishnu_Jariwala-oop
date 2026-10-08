package service;

import model.Account;

public class MiniBank {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 10: Managed Thread Pool & Safe Transfers");
        System.out.println("==================================================");

        Account a1 = new Account("AC1001", "Rohan Patel", 50000);
        Account a2 = new Account("AC2002", "Diya Shah", 30000);

        System.out.println("Initial Accounts:\n  " + a1 + "\n  " + a2);

        // 1. TransactionProcessor Thread Pool
        System.out.println("\n--- Batch Processing on Fixed Thread Pool (4 Workers) ---");
        TransactionProcessor processor = new TransactionProcessor(4);

        for (int i = 1; i <= 6; i++) {
            final int id = i;
            processor.submit(() -> {
                a1.deposit(500);
                System.out.println("Batch Deposit #" + id + " executed on: " + Thread.currentThread().getName());
            });
        }
        processor.stop();
        System.out.println("Batch transaction engine stopped. A1 Balance: ₹" + a1.getBalance());

        // 2. Deadlock-free concurrent bi-directional transfers
        System.out.println("\n--- Concurrent Bi-Directional Transfers (Safe Lock Ordering) ---");
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                boolean ok = Account.safeTransfer(a1, a2, 1000);
                System.out.println("Transfer A1 -> A2 (₹1000): " + ok);
            }
        }, "Transfer-Thread-1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                boolean ok = Account.safeTransfer(a2, a1, 1000);
                System.out.println("Transfer A2 -> A1 (₹1000): " + ok);
            }
        }, "Transfer-Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nFinal Accounts after Transfers:\n  " + a1 + "\n  " + a2);
        System.out.println("No deadlocks occurred despite opposing transfer directions!");
    }
}
