import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        System.out.println("=== NIO Path & Files Log Analyzer ===");
        Path dir = Files.createTempDirectory("logs_test_");

        Path log1 = dir.resolve("app-auth.log");
        Path log2 = dir.resolve("app-payment.log");

        Files.write(log1, List.of(
            "INFO: User login successful for admin",
            "ERROR: Authentication failed for guest",
            "INFO: Session created for admin"
        ));

        Files.write(log2, List.of(
            "INFO: Payment gateway initiated",
            "ERROR: Transaction timeout occurred",
            "ERROR: Gateway handshake failed",
            "INFO: Retrying transaction"
        ));

        Path[] logFiles = { log1, log2 };
        String keyword = "ERROR";
        int totalLines = 0;
        int keywordCount = 0;

        System.out.println("Scanning log files for keyword: [" + keyword + "]\n");

        for (Path p : logFiles) {
            BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
            List<String> lines = Files.readAllLines(p);
            totalLines += lines.size();

            int matchesInFile = 0;
            for (String line : lines) {
                if (line.contains(keyword)) matchesInFile++;
            }
            keywordCount += matchesInFile;

            System.out.printf("File: %-18s | Size: %3d bytes | Lines: %d | Matches: %d\n",
                p.getFileName(), attrs.size(), lines.size(), matchesInFile);
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Total Lines Analyzed: " + totalLines + " | Total [" + keyword + "] Occurrences: " + keywordCount);

        // Cleanup
        Files.deleteIfExists(log1);
        Files.deleteIfExists(log2);
        Files.deleteIfExists(dir);
    }
}
