package model;

public abstract class Account implements Transactable, InterestBearing {
    private final String accountNumber;
    private final String ownerName;
    protected long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }

    @Override
    public long getBalance() { return balance; }

    @Override
    public void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    @Override
    public boolean withdraw(long amount) {
        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public abstract boolean canWithdraw(long amount);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[No=" + accountNumber + ", Owner=" + ownerName + ", Balance=₹" + balance + "]";
    }
}
