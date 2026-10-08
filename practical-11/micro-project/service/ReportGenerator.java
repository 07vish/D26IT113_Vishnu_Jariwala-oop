package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static String generateAuditReport(Path logsFolder, Path reportOutputFile) throws IOException {
        long totalDeposits = 0;
        long totalWithdrawals = 0;
        int transactionCount = 0;
        StringBuilder report = new StringBuilder();

        report.append("==================================================\n");
        report.append("           MINIBANK END-OF-DAY AUDIT REPORT       \n");
        report.append("==================================================\n");

        if (Files.exists(logsFolder)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(logsFolder, "*.log")) {
                for (Path logFile : stream) {
                    BasicFileAttributes attrs = Files.readAttributes(logFile, BasicFileAttributes.class);
                    report.append("Inspecting Log: ").append(logFile.getFileName())
                          .append(" (Size: ").append(attrs.size()).append(" bytes)\n");

                    try (BufferedReader reader = Files.newBufferedReader(logFile)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            transactionCount++;
                            if (line.contains("DEPOSIT")) {
                                String[] parts = line.split("\\s+");
                                if (parts.length > 0) {
                                    try {
                                        long amt = Long.parseLong(parts[parts.length - 1]);
                                        totalDeposits += amt;
                                    } catch (NumberFormatException ignored) {}
                                }
                            } else if (line.contains("WITHDRAW")) {
                                String[] parts = line.split("\\s+");
                                if (parts.length > 0) {
                                    try {
                                        long amt = Long.parseLong(parts[parts.length - 1]);
                                        totalWithdrawals += amt;
                                    } catch (NumberFormatException ignored) {}
                                }
                            }
                        }
                    }
                }
            }
        }

        report.append("--------------------------------------------------\n");
        report.append("Total Transactions Processed : ").append(transactionCount).append("\n");
        report.append("Total Deposits Tally         : ₹").append(totalDeposits).append("\n");
        report.append("Total Withdrawals Tally      : ₹").append(totalWithdrawals).append("\n");
        report.append("Net Liquidity Flow           : ₹").append(totalDeposits - totalWithdrawals).append("\n");
        report.append("==================================================\n");

        Files.writeString(reportOutputFile, report.toString());
        return report.toString();
    }
}
