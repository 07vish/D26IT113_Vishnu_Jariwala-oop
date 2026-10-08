public class Account {
    private final String accountNumber;
    private final String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public synchronized long getBalance() { return balance; }

    // Synchronized deposit guaranteeing mutual exclusion
    public synchronized void deposit(long amount) {
        long current = balance;
        // simulate micro processing latency to expose concurrency
        try { Thread.sleep(1); } catch (InterruptedException ignored) {}
        balance = current + amount;
    }

    // Synchronized withdraw
    public synchronized boolean withdraw(long amount) {
        if (balance >= amount) {
            long current = balance;
            try { Thread.sleep(1); } catch (InterruptedException ignored) {}
            balance = current - amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account[" + accountNumber + ", " + ownerName + ", Balance=₹" + getBalance() + "]";
    }
}
