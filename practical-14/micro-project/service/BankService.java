package service;

import exception.*;
import model.Account;
import model.Customer;
import model.annotation.Audit;
import repository.Repository;
import util.AuditLogger;
import util.Validator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final Repository<Account> accountRepo;
    private Path transactionLogFile;

    public BankService() {
        this.accountRepo = new Repository<>();
    }

    public BankService(Repository<Account> repo) {
        this.accountRepo = repo;
    }

    public void setTransactionLogFile(Path logFile) {
        this.transactionLogFile = logFile;
    }

    @Audit(action = "OPEN_ACCOUNT")
    public Account openAccount(String accNo, String ownerName, String email, String mobile, long initialBalance) throws BankException {
        AuditLogger.logInvocation(this, "openAccount");

        if (accNo == null || accNo.isBlank()) {
            throw new BankException("Account number cannot be empty.");
        }
        if (accountRepo.findById(accNo) != null) {
            throw new BankException("Account with number " + accNo + " already exists.");
        }
        if (ownerName == null || ownerName.trim().length() < 2) {
            throw new BankException("Invalid owner name: must be at least 2 characters.");
        }
        if (!Validator.isValidEmail(email)) {
            throw new BankException("Invalid email address format: " + email);
        }
        if (!Validator.isValidMobile(mobile)) {
            throw new BankException("Invalid Indian mobile number: " + mobile + " (must be 10 digits starting with 6-9)");
        }
        if (initialBalance < 1000) {
            throw new InvalidAmountException("Minimum initial deposit of ₹1000 required to open an account.");
        }

        Customer customer = new Customer("CUST-" + accNo, ownerName.trim(), email.trim(), mobile.trim());
        Account newAccount = new Account(accNo.trim(), customer, initialBalance);
        accountRepo.save(newAccount);

        logAction("OPEN " + accNo + " " + initialBalance);
        return newAccount;
    }

    @Audit(action = "DEPOSIT_FUNDS")
    public void deposit(String accNo, long amount) throws BankException {
        AuditLogger.logInvocation(this, "deposit");
        Account acc = getAccountOrThrow(accNo);
        acc.deposit(amount);
        logAction("DEPOSIT " + accNo + " " + amount);
    }

    @Audit(action = "WITHDRAW_FUNDS")
    public void withdraw(String accNo, long amount) throws BankException {
        AuditLogger.logInvocation(this, "withdraw");
        Account acc = getAccountOrThrow(accNo);
        acc.withdraw(amount);
        logAction("WITHDRAW " + accNo + " " + amount);
    }

    @Audit(action = "TRANSFER_FUNDS")
    public void transfer(String srcAccNo, String dstAccNo, long amount) throws BankException {
        AuditLogger.logInvocation(this, "transfer");
        if (srcAccNo.equals(dstAccNo)) {
            throw new BankException("Source and destination accounts cannot be identical.");
        }

        Account src = getAccountOrThrow(srcAccNo);
        Account dst = getAccountOrThrow(dstAccNo);

        Account first = src.compareTo(dst) < 0 ? src : dst;
        Account second = src.compareTo(dst) < 0 ? dst : src;

        synchronized (first) {
            synchronized (second) {
                src.withdraw(amount);
                dst.deposit(amount);
            }
        }

        logAction("WITHDRAW " + srcAccNo + " " + amount);
        logAction("DEPOSIT " + dstAccNo + " " + amount);
    }

    public List<String> statement(String accNo) throws BankException {
        Account acc = getAccountOrThrow(accNo);
        return acc.getStatement();
    }

    public ArrayList<Account> listAccounts() {
        return accountRepo.findAll();
    }

    public List<Account> searchByOwner(String nameQuery) {
        if (nameQuery == null || nameQuery.isBlank()) return List.of();
        String q = nameQuery.toLowerCase();
        List<Account> matches = new ArrayList<>();
        for (Account acc : accountRepo.findAll()) {
            if (acc.getOwner() != null && acc.getOwner().getName().toLowerCase().contains(q)) {
                matches.add(acc);
            }
        }
        return matches;
    }

    public Account getAccount(String accNo) {
        return accountRepo.findById(accNo);
    }

    public void saveAccounts(Path file) throws IOException {
        ArrayList<Account> list = accountRepo.findAll();
        StatePersister.save(list.toArray(new Account[0]), file);
    }

    public void loadAccounts(Path file) throws IOException, ClassNotFoundException {
        Account[] loaded = StatePersister.load(file);
        for (Account a : loaded) {
            accountRepo.save(a);
        }
    }

    private Account getAccountOrThrow(String accNo) throws AccountNotFoundException {
        Account acc = accountRepo.findById(accNo);
        if (acc == null) {
            throw new AccountNotFoundException(accNo);
        }
        return acc;
    }

    private void logAction(String logLine) {
        if (transactionLogFile != null) {
            try {
                TransactionLog.append(transactionLogFile, logLine);
            } catch (IOException ignored) {}
        }
    }
}
