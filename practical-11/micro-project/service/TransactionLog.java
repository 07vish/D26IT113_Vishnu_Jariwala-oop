package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class TransactionLog {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void append(Path file, String line) throws IOException {
        String timestamped = "[" + LocalDateTime.now().format(FMT) + "] " + line;
        Files.write(
            file,
            Collections.singletonList(timestamped),
            StandardOpenOption.CREATE,
            StandardOpenOption.APPEND
        );
    }
}
