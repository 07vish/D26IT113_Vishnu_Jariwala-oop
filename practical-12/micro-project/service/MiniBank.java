package service;

import model.Account;
import model.Customer;
import java.util.List;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 12: Collections Framework & Store");
        System.out.println("==================================================");

        AccountStore store = new AccountStore();

        // 1. Create Customers and Accounts
        Customer c1 = new Customer("CUST101", "Vishnu Jariwala", "vishnu@charusat.edu.in");
        Customer c2 = new Customer("CUST102", "Diya Shah", "diya@charusat.edu.in");
        Customer c3 = new Customer("CUST103", "Aarav Patel", "aarav@charusat.edu.in");
        Customer c4 = new Customer("CUST104", "Riya Sharma", "riya@charusat.edu.in");

        Account a1 = new Account("AC0001", c1, 5000);
        Account a2 = new Account("AC0002", c2, 2000);
        Account a3 = new Account("AC0003", c3, 9000);
        Account a4 = new Account("AC0004", c4, 7500);

        // Add accounts to AccountStore (HashMap storage)
        store.add(a1);
        store.add(a2);
        store.add(a3);
        store.add(a4);

        System.out.println("\n[1] Stored Accounts in Map (Size = " + store.getAll().size() + "):");
        for (Account a : store.getAll()) {
            System.out.println("  " + a);
        }

        // Fast lookup via get(accountNumber)
        System.out.println("\n[2] Lookup Account 'AC0002' via HashMap Key:");
        Account retrieved = store.get("AC0002");
        System.out.println("  Result: " + retrieved);

        // Perform some transactions and log them
        retrieved.deposit(1500);
        store.logTransaction("DEPOSIT: AC0002 credited ₹1500. New balance: ₹" + retrieved.getBalance());
        a1.withdraw(1000);
        store.logTransaction("WITHDRAW: AC0001 debited ₹1000. New balance: ₹" + a1.getBalance());

        // Sort accounts by balance (Comparator)
        System.out.println("\n[3] Accounts Sorted by Balance (Descending - Comparator):");
        List<Account> byBalance = store.sortedByBalance();
        for (Account a : byBalance) {
            System.out.println("  " + a);
        }

        // Sort accounts by natural order (Comparable - Account Number)
        System.out.println("\n[4] Accounts Sorted by Natural Order (Comparable - Account No):");
        List<Account> byNumber = store.sortedByAccountNumber();
        for (Account a : byNumber) {
            System.out.println("  " + a);
        }

        // Supplementary: Filter accounts with balance above threshold
        long threshold = 5000;
        System.out.println("\n[5] Premium Accounts (Balance >= ₹" + threshold + "):");
        for (Account a : store.getAccountsWithBalanceAbove(threshold)) {
            System.out.println("  " + a);
        }

        // Transaction History (ArrayList)
        System.out.println("\n[6] Transaction Audit Journal (ArrayList History):");
        for (String tx : store.getTransactionHistory()) {
            System.out.println("  " + tx);
        }

        // Multithreaded concurrent testing via ConcurrentHashMap & TransactionProcessor
        System.out.println("\n[7] Concurrent Execution Test via ConcurrentHashMap:");
        TransactionProcessor processor = new TransactionProcessor(4);
        processor.registerAccount(a1);
        processor.registerAccount(a3);

        System.out.println("  Submitting 10 concurrent deposits of ₹100 each to AC0001...");
        for (int i = 0; i < 10; i++) {
            processor.submitDeposit("AC0001", 100);
        }

        processor.shutdown();
        System.out.println("  Final balance for AC0001 after concurrent updates: ₹" + a1.getBalance());
        System.out.println("==================================================");
    }
}
