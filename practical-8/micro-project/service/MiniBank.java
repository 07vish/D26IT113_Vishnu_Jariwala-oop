package service;

import model.Account;
import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 8: Fault-Tolerant Exception Hierarchy");
        System.out.println("==================================================");

        Account a1 = new Account("AC1001", "Rohan Patel", 10000);
        Account a2 = new Account("AC1002", "Diya Shah", 5000);

        System.out.println("Initial Accounts:\n  " + a1 + "\n  " + a2 + "\n");

        // Try-with-resources with AutoCloseable BankSession
        try (BankSession session = new BankSession("BranchManager_Admin")) {
            System.out.println("\n--- Case 1: Negative Amount Deposit ---");
            try {
                a1.deposit(-1500);
            } catch (InvalidAmountException e) {
                System.out.println("Handled Exception: " + e.getMessage());
            }

            System.out.println("\n--- Case 2: Excessive Withdrawal (Insufficient Funds) ---");
            try {
                a1.withdraw(16000);
            } catch (InsufficientFundsException e) {
                System.out.println("Handled Insufficient Funds: Shortfall is ₹" + e.getShortfall());
            } catch (BankException e) {
                System.out.println("General Bank Exception: " + e.getMessage());
            }

            System.out.println("\n--- Case 3: Fund Transfer Operation ---");
            try {
                a1.transfer(a2, 4000);
            } catch (BankException e) {
                System.out.println("Transfer error: " + e.getMessage());
            }

            System.out.println("\nFinal Status:\n  " + a1 + "\n  " + a2);
        }
    }
}
