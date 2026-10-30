package model;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.annotation.Id;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account implements Comparable<Account>, Serializable {
    @Serial
    private static final long serialVersionUID = 102L;

    @Id
    private final String accountNumber;
    private final Customer owner;
    private long balance;
    private final List<String> transactionHistory;

    public Account(String accountNumber, Customer owner, long balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("INITIAL_DEPOSIT: ₹" + balance);
    }

    public String getAccountNumber() { return accountNumber; }
    public Customer getOwner() { return owner; }
    public synchronized long getBalance() { return balance; }

    public synchronized void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be strictly positive. Received: ₹" + amount);
        }
        balance += amount;
        transactionHistory.add("DEPOSIT: +₹" + amount + " | Balance: ₹" + balance);
    }

    public synchronized void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be strictly positive. Received: ₹" + amount);
        }
        if (balance < amount) {
            long shortfall = amount - balance;
            throw new InsufficientFundsException(shortfall, "Withdrawal denied on " + accountNumber + ". Shortfall: ₹" + shortfall);
        }
        balance -= amount;
        transactionHistory.add("WITHDRAW: -₹" + amount + " | Balance: ₹" + balance);
    }

    public synchronized List<String> getStatement() {
        return new ArrayList<>(transactionHistory);
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
