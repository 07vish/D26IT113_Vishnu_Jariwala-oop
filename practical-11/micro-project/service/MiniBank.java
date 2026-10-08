package service;

import model.Account;
import model.Customer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MiniBank {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("==================================================");
        System.out.println("MiniBank Milestone 11: Persistence & Daily Audit Report");
        System.out.println("==================================================");

        Path workDir = Files.createTempDirectory("minibank_p11_");
        Path stateFile = workDir.resolve("bank_state.dat");
        Path logsDir = workDir.resolve("logs");
        Files.createDirectories(logsDir);
        Path logFile = logsDir.resolve("daily_transactions.log");
        Path reportFile = workDir.resolve("daily_audit_report.txt");

        // 1. Create Model Entities
        Customer c1 = new Customer("CUST101", "Vishnu Jariwala", "vishnu@charusat.edu.in");
        Customer c2 = new Customer("CUST102", "Diya Shah", "diya@charusat.edu.in");

        Account[] liveAccounts = {
            new Account("AC1001", c1, 45000),
            new Account("AC1002", c2, 60000)
        };

        // 2. State Serialization
        System.out.println("Persisting live accounts to binary disk via StatePersister...");
        StatePersister.save(liveAccounts, stateFile);

        // 3. State Deserialization Verification
        Account[] reloadedAccounts = StatePersister.load(stateFile);
        System.out.println("Reloaded accounts from disk successfully:");
        for (Account a : reloadedAccounts) {
            System.out.println("  " + a);
        }

        // 4. Append Transaction Logs using NIO Files.write
        System.out.println("\nAppending transactions to append-only log: " + logFile.getFileName());
        TransactionLog.append(logFile, "DEPOSIT AC1001 5000");
        TransactionLog.append(logFile, "WITHDRAW AC1002 2000");
        TransactionLog.append(logFile, "DEPOSIT AC1002 10000");

        // 5. Generate End-of-Day NIO Report
        System.out.println("\nGenerating comprehensive audit report from logs folder...");
        String reportText = ReportGenerator.generateAuditReport(logsDir, reportFile);
        System.out.println(reportText);

        // Cleanup
        Files.deleteIfExists(logFile);
        Files.deleteIfExists(reportFile);
        Files.deleteIfExists(logsDir);
        Files.deleteIfExists(stateFile);
        Files.deleteIfExists(workDir);
    }
}
