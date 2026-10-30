package service;

import model.Account;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor {
    private final ConcurrentHashMap<String, Account> sharedAccounts;
    private final ExecutorService executor;

    public TransactionProcessor(int poolSize) {
        this.sharedAccounts = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(poolSize);
    }

    public void registerAccount(Account account) {
        if (account != null) {
            sharedAccounts.put(account.getAccountNumber(), account);
        }
    }

    public Account getAccount(String accNo) {
        return sharedAccounts.get(accNo);
    }

    public ConcurrentHashMap<String, Account> getSharedAccounts() {
        return sharedAccounts;
    }

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void submitDeposit(String accountNumber, long amount) {
        executor.execute(() -> {
            Account acc = sharedAccounts.get(accountNumber);
            if (acc != null) {
                try {
                    acc.deposit(amount);
                } catch (Exception ignored) {}
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
