package model;

import java.io.Serial;
import java.io.Serializable;

public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 102L;

    private final String accountNumber;
    private final Customer owner;
    private long balance;

    public Account(String accountNumber, Customer owner, long balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public Customer getOwner() { return owner; }
    public long getBalance() { return balance; }

    public void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account[" + accountNumber + ", " + owner.getName() + ", Balance=₹" + balance + "]";
    }
}
