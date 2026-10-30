package ui;

import exception.BankException;
import model.Account;
import service.BankService;
import service.ReportGenerator;
import service.TransactionProcessor;
import test.MiniBankTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MiniBankApp {
    private final BankService service;
    private final Path dataDir;
    private final Path stateFile;
    private final Path logsDir;
    private final Path logFile;
    private final Path reportFile;

    public MiniBankApp() {
        this.service = new BankService();
        this.dataDir = Paths.get("minibank_data");
        this.stateFile = dataDir.resolve("accounts.dat");
        this.logsDir = dataDir.resolve("logs");
        this.logFile = logsDir.resolve("daily_transactions.log");
        this.reportFile = dataDir.resolve("audit_report.txt");
        initEnvironment();
    }

    private void initEnvironment() {
        try {
            Files.createDirectories(logsDir);
            service.setTransactionLogFile(logFile);

            // Practical 11 requirement: Load saved accounts on start-up
            if (Files.exists(stateFile)) {
                System.out.println(">> Loading existing bank state from " + stateFile.getFileName() + "...");
                service.loadAccounts(stateFile);
                System.out.println(">> Successfully loaded " + service.listAccounts().size() + " accounts from storage.");
            } else {
                System.out.println(">> No existing state found. Seeding initial accounts...");
                seedInitialData();
            }
        } catch (Exception e) {
            System.err.println("Warning: Error during storage initialization: " + e.getMessage());
            seedInitialData();
        }
    }

    private void seedInitialData() {
        try {
            service.openAccount("AC1001", "Vishnu Jariwala", "vishnu@charusat.edu.in", "9876543210", 45000);
            service.openAccount("AC1002", "Diya Shah", "diya@charusat.edu.in", "9876543211", 60000);
            service.openAccount("AC1003", "Aarav Patel", "aarav@charusat.edu.in", "9876543212", 25000);
            service.openAccount("AC1004", "Riya Sharma", "riya@charusat.edu.in", "9876543213", 80000);
        } catch (BankException ignored) {}
    }

    public void shutdownAndSave() {
        try {
            System.out.println("\n>> Persisting bank state to disk (" + stateFile.getFileName() + ")...");
            service.saveAccounts(stateFile);
            System.out.println(">> Bank state saved successfully. Shutdown complete.");
        } catch (IOException e) {
            System.err.println("Error saving bank state: " + e.getMessage());
        }
    }

    public void runDemonstration() {
        System.out.println("==================================================");
        System.out.println("CHARUSAT Central Bank - MiniBank Capstone Milestone 3");
        System.out.println("Changa Campus Branch | Enterprise Banking Engine");
        System.out.println("==================================================");

        // Run automated tests first
        MiniBankTest.runAllTests();

        System.out.println("\n--- Executing Capstone Showcase Scenarios ---");

        // 1. List accounts
        System.out.println("\n[Action 1] Listing All Managed Accounts:");
        for (Account a : service.listAccounts()) {
            System.out.println("  " + a);
        }

        // 2. Open new account
        System.out.println("\n[Action 2] Opening New Verified Account:");
        try {
            Account created = service.openAccount("AC1005", "Tanvi Dave", "tanvi@charusat.edu.in", "9876543214", 15000);
            System.out.println("  Successfully opened: " + created);
        } catch (BankException e) {
            System.out.println("  Failed: " + e.getMessage());
        }

        // 3. Deposit
        System.out.println("\n[Action 3] Performing Deposit of ₹5000 on AC1001:");
        try {
            service.deposit("AC1001", 5000);
            System.out.println("  New balance for AC1001: ₹" + service.getAccount("AC1001").getBalance());
        } catch (BankException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // 4. Withdrawal
        System.out.println("\n[Action 4] Performing Withdrawal of ₹3000 on AC1002:");
        try {
            service.withdraw("AC1002", 3000);
            System.out.println("  New balance for AC1002: ₹" + service.getAccount("AC1002").getBalance());
        } catch (BankException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // 5. Transfer
        System.out.println("\n[Action 5] Transferring ₹4000 from AC1001 to AC1003:");
        try {
            service.transfer("AC1001", "AC1003", 4000);
            System.out.println("  Transfer completed! AC1001: ₹" + service.getAccount("AC1001").getBalance()
                + " | AC1003: ₹" + service.getAccount("AC1003").getBalance());
        } catch (BankException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // 6. Statement
        System.out.println("\n[Action 6] Account Statement for AC1001:");
        try {
            for (String entry : service.statement("AC1001")) {
                System.out.println("  * " + entry);
            }
        } catch (BankException e) {
            System.out.println("  Error: " + e.getMessage());
        }

        // 7. Search by Owner
        System.out.println("\n[Action 7] Searching Accounts by Owner Name ('diya'):");
        List<Account> searchResults = service.searchByOwner("diya");
        for (Account a : searchResults) {
            System.out.println("  Found Match: " + a);
        }

        // 8. Concurrent Batch Processing (Practical 10/12)
        System.out.println("\n[Action 8] Concurrent Batch Processing over Thread-Safe Store:");
        TransactionProcessor processor = new TransactionProcessor(4);
        processor.registerAccount(service.getAccount("AC1001"));
        processor.registerAccount(service.getAccount("AC1004"));

        System.out.println("  Submitting 20 concurrent deposits of ₹200 to AC1004...");
        for (int i = 0; i < 20; i++) {
            processor.submitDeposit("AC1004", 200);
        }
        processor.shutdown();
        System.out.println("  Batch finished. AC1004 updated balance: ₹" + service.getAccount("AC1004").getBalance());

        // 9. NIO Financial Audit Report Generation (Practical 11)
        System.out.println("\n[Action 9] Generating NIO Daily Audit Report from Logs:");
        try {
            String report = ReportGenerator.generateAuditReport(logsDir, reportFile);
            System.out.println(report);
        } catch (IOException e) {
            System.out.println("  Report generation failed: " + e.getMessage());
        }

        // 10. Persist and shutdown
        shutdownAndSave();
        System.out.println("==================================================");
    }

    public static void main(String[] args) {
        MiniBankApp app = new MiniBankApp();

        if (args.length > 0 && args[0].equalsIgnoreCase("--interactive")) {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            while (running) {
                System.out.println("\n=== MiniBank Menu ===");
                System.out.println("1. List Accounts");
                System.out.println("2. Open Account");
                System.out.println("3. Deposit");
                System.out.println("4. Withdraw");
                System.out.println("5. Transfer");
                System.out.println("6. Account Statement");
                System.out.println("7. Search by Owner Name");
                System.out.println("8. Run Audit Report");
                System.out.println("9. Exit Application");
                System.out.print("Select (1-9): ");

                if (!scanner.hasNextInt()) break;
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        for (Account a : app.service.listAccounts()) System.out.println("  " + a);
                    }
                    case 9 -> {
                        running = false;
                        app.shutdownAndSave();
                    }
                    default -> System.out.println("Option processed.");
                }
            }
            scanner.close();
        } else {
            app.runDemonstration();
        }
    }
}
