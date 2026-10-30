package service;

import model.Account;
import java.util.*;

public class AccountStore {
    private final Map<String, Account> accounts;
    private final List<String> transactionHistory;

    public AccountStore() {
        this.accounts = new HashMap<>();
        this.transactionHistory = new ArrayList<>();
    }

    public void add(Account account) {
        if (account != null) {
            accounts.put(account.getAccountNumber(), account);
            logTransaction("ADDED_ACCOUNT: " + account.getAccountNumber() + " initialized with balance ₹" + account.getBalance());
        }
    }

    public Account get(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public List<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    public void logTransaction(String entry) {
        transactionHistory.add(String.format("[%tF %<tT] %s", new Date(), entry));
    }

    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public List<Account> sortedByBalance() {
        List<Account> sortedList = new ArrayList<>(accounts.values());
        // Sort descending: highest balance first
        sortedList.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return sortedList;
    }

    public List<Account> sortedByAccountNumber() {
        List<Account> sortedList = new ArrayList<>(accounts.values());
        Collections.sort(sortedList); // Natural order using Comparable
        return sortedList;
    }

    public List<Account> getAccountsWithBalanceAbove(long threshold) {
        List<Account> result = new ArrayList<>();
        for (Account a : accounts.values()) {
            if (a.getBalance() >= threshold) {
                result.add(a);
            }
        }
        result.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return result;
    }
}
