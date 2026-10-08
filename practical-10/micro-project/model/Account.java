package model;

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

    public synchronized void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    public synchronized boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // Deadlock-free safe transfer enforcing consistent lock ordering based on accountNumber
    public static boolean safeTransfer(Account from, Account to, long amount) {
        if (from == null || to == null || from.equals(to)) return false;

        // Determine deterministic lock order
        Account firstLock = from.accountNumber.compareTo(to.accountNumber) < 0 ? from : to;
        Account secondLock = from.accountNumber.compareTo(to.accountNumber) < 0 ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    return true;
                }
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "Account[" + accountNumber + ", " + ownerName + ", Balance=₹" + getBalance() + "]";
    }
}
