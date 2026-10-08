package model;

import model.annotation.Id;
import model.annotation.Positive;
import model.annotation.MaxLength;

public class Account {
    @Id
    @MaxLength(value = 8, message = "Account number must not exceed 8 characters")
    private final String accountNumber;

    @MaxLength(value = 30, message = "Owner name exceeds max limit")
    private final String ownerName;

    @Positive(message = "Account balance must be positive")
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public long getBalance() { return balance; }

    @Override
    public String toString() {
        return "Account[" + accountNumber + ", " + ownerName + ", ₹" + balance + "]";
    }
}
