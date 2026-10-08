import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirWalker {
    public static void main(String[] args) throws IOException {
        System.out.println("=== NIO Directory Tree Traversal & Inspection ===");
        Path tempDir = Files.createTempDirectory("tree_walk_demo_");

        Path subDir1 = Files.createDirectory(tempDir.resolve("moduleA"));
        Path subDir2 = Files.createDirectory(tempDir.resolve("moduleB"));

        Files.writeString(subDir1.resolve("Config.json"), "{\"status\": \"active\"}");
        Files.writeString(subDir2.resolve("Data.csv"), "id,name,role\n1,Vishnu,Lead");
        Files.writeString(tempDir.resolve("Readme.md"), "# Project NIO Inspection");

        StringBuilder report = new StringBuilder();
        report.append("DIRECTORY INSPECTION REPORT\n");
        report.append("Root: ").append(tempDir.toAbsolutePath()).append("\n\n");

        try (var stream = Files.walk(tempDir)) {
            stream.forEach(path -> {
                try {
                    BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
                    String type = attrs.isDirectory() ? "[DIR ]" : "[FILE]";
                    report.append(String.format("%s %-30s | Size: %5d bytes | Modified: %s\n",
                        type, path.getFileName(), attrs.size(), attrs.lastModifiedTime()));
                } catch (IOException e) {
                    report.append("Error inspecting: ").append(path).append("\n");
                }
            });
        }

        System.out.println(report.toString());

        // Cleanup
        try (var stream = Files.walk(tempDir)) {
            stream.sorted((a, b) -> b.compareTo(a)).forEach(p -> {
                try { Files.deleteIfExists(p); } catch (IOException ignored) {}
            });
        }
    }
}
