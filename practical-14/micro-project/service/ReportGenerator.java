package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class ReportGenerator {
    public static String generateAuditReport(Path logsDir, Path outputReportFile) throws IOException {
        long totalDeposits = 0;
        long totalWithdrawals = 0;
        int logFileCount = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("           MINIBANK END-OF-DAY AUDIT REPORT       \n");
        sb.append("==================================================\n");

        if (Files.exists(logsDir) && Files.isDirectory(logsDir)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(logsDir, "*.log")) {
                for (Path entry : stream) {
                    logFileCount++;
                    BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                    sb.append(String.format("Log File: %-25s | Size: %d B | Last Modified: %s%n",
                        entry.getFileName(), attrs.size(), attrs.lastModifiedTime()));

                    try (BufferedReader reader = Files.newBufferedReader(entry)) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.trim().split("\\s+");
                            if (parts.length >= 3) {
                                String action = parts[0].toUpperCase();
                                try {
                                    long amt = Long.parseLong(parts[2]);
                                    if (action.contains("DEPOSIT")) {
                                        totalDeposits += amt;
                                    } else if (action.contains("WITHDRAW")) {
                                        totalWithdrawals += amt;
                                    }
                                } catch (NumberFormatException ignored) {}
                            }
                        }
                    }
                }
            }
        }

        long netFlow = totalDeposits - totalWithdrawals;
        sb.append("--------------------------------------------------\n");
        sb.append("Log Files Inspected  : ").append(logFileCount).append("\n");
        sb.append("Total Inward (Credit): ₹").append(totalDeposits).append("\n");
        sb.append("Total Outward (Debit): ₹").append(totalWithdrawals).append("\n");
        sb.append("Net Liquidity Flow   : ₹").append(netFlow).append("\n");
        sb.append("==================================================\n");

        String reportText = sb.toString();
        if (outputReportFile != null) {
            Files.writeString(outputReportFile, reportText);
        }
        return reportText;
    }
}
