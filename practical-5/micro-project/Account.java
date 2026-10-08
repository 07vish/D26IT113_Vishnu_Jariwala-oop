public abstract class Account {
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
    public long getBalance() { return balance; }

    public void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[No=" + accountNumber + ", Owner=" + ownerName + 
               ", Balance=₹" + balance + ", Interest=" + interestRate() + "%]";
    }
}
