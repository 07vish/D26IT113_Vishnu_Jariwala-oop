public class ChatFilter {
    public static String filterLogs(String[] logs, String keyword) {
        StringBuilder report = new StringBuilder();
        int matches = 0;
        String lowerKeyword = keyword.toLowerCase();

        for (String line : logs) {
            if (line == null) continue;
            String[] parts = line.split(" ", 3);
            if (parts.length < 3) {
                // Skip malformed log entry
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(lowerKeyword)) {
                matches++;
                report.append("  [").append(time).append("] ")
                      .append(user).append(": ")
                      .append(message).append("\n");
            }
        }

        return "Matches: " + matches + "\n" + report.toString();
    }
}
