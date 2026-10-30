package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Account implements Comparable<Account>, Serializable {
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
    public synchronized long getBalance() { return balance; }

    public synchronized void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public synchronized boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Account other) {
        if (other == null) return 1;
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    @Override
    public String toString() {
        return String.format("Account[No: %s, Owner: %-16s, Balance: ₹%d]",
            accountNumber, (owner != null ? owner.getName() : "N/A"), balance);
    }
}
