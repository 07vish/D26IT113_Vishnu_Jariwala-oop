package service;

import model.Account;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor {
    private final ConcurrentHashMap<String, Account> sharedAccounts;
    private final ExecutorService executor;

    public TransactionProcessor(int threadPoolSize) {
        this.sharedAccounts = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    public void registerAccount(Account account) {
        sharedAccounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return sharedAccounts.get(accountNumber);
    }

    public ConcurrentHashMap<String, Account> getSharedAccounts() {
        return sharedAccounts;
    }

    public void submitDeposit(String accountNumber, long amount) {
        executor.execute(() -> {
            Account acc = sharedAccounts.get(accountNumber);
            if (acc != null) {
                acc.deposit(amount);
            }
        });
    }

    public void submitWithdrawal(String accountNumber, long amount) {
        executor.execute(() -> {
            Account acc = sharedAccounts.get(accountNumber);
            if (acc != null) {
                acc.withdraw(amount);
            }
        });
    }

    public void submitTransfer(String sourceAcc, String destAcc, long amount) {
        executor.execute(() -> {
            Account src = sharedAccounts.get(sourceAcc);
            Account dst = sharedAccounts.get(destAcc);
            if (src != null && dst != null) {
                // Prevent deadlock by locking accounts in fixed natural ordering
                Account first = src.compareTo(dst) < 0 ? src : dst;
                Account second = src.compareTo(dst) < 0 ? dst : src;

                synchronized (first) {
                    synchronized (second) {
                        if (src.withdraw(amount)) {
                            dst.deposit(amount);
                        }
                    }
                }
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
