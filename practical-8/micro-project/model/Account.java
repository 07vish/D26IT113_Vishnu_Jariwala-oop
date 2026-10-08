package model;

import exception.InsufficientFundsException;
import exception.InvalidAmountException;

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
    public long getBalance() { return balance; }

    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be strictly greater than 0. Received: ₹" + amount);
        }
        balance += amount;
    }

    public void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be strictly positive. Received: ₹" + amount);
        }
        if (balance < amount) {
            long shortfall = amount - balance;
            throw new InsufficientFundsException(shortfall, "Withdrawal denied on " + accountNumber + ". Shortfall: ₹" + shortfall);
        }
        balance -= amount;
    }

    public void transfer(Account target, long amount) throws InsufficientFundsException, InvalidAmountException {
        System.out.println("Attempting transfer of ₹" + amount + " from " + accountNumber + " to " + target.getAccountNumber());
        try {
            this.withdraw(amount);
            target.deposit(amount);
            System.out.println("Transfer successful!");
        } catch (InsufficientFundsException | InvalidAmountException e) {
            System.out.println("Transfer failed inside transaction: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Transfer audit trail logged for transaction between " + accountNumber + " and " + target.getAccountNumber());
        }
    }

    @Override
    public String toString() {
        return "Account[" + accountNumber + ", " + ownerName + ", Balance=₹" + balance + "]";
    }
}
