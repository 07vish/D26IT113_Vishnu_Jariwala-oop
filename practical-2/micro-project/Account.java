public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;
    private static long accountCounter = 0;

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = Math.max(0, openingBalance);
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    private static synchronized String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public void deposit(long amount) {
        if (amount > 0 && active) {
            balance += amount;
            System.out.println("Deposited ₹" + amount + " to " + accountNumber + " | New Balance: ₹" + balance);
        } else {
            System.out.println("Deposit failed: invalid amount or account inactive.");
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && active && balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew ₹" + amount + " from " + accountNumber + " | Remaining Balance: ₹" + balance);
            return true;
        } else {
            System.out.println("Withdrawal of ₹" + amount + " failed from " + accountNumber + " (Insufficient balance: ₹" + balance + ")");
            return false;
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }
    public boolean isActive() { return active; }
}
